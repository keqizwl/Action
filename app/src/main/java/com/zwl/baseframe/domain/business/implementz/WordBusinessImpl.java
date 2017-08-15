package com.zwl.baseframe.domain.business.implementz;


import com.zwl.baseframe.domain.business.interfacez.IWordBusiness;
import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.domain.business.module.alarm.AlarmSettingParams;
import com.zwl.baseframe.domain.business.module.alarm.IAlarmModule;
import com.zwl.baseframe.domain.business.module.alarm.IAlarmStorer;
import com.zwl.baseframe.domain.business.module.word.IWordModule;
import com.zwl.baseframe.domain.business.module.word.storer.IWordStore;
import com.zwl.baseframe.implementz.CommonCallback;
import com.zwl.baseframe.implementz.di.scope.AppScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hasee on 2017/6/26.
 */

@AppScope
public class WordBusinessImpl implements IWordBusiness {
    private static final String TAG = WordBusinessImpl.class.getSimpleName();
    @Inject
    IWordModule wordModule;
    @Inject
    IWordStore wordStorer;
    @Inject
    IAlarmModule alarmModule;
    @Inject
    IAlarmStorer alarmStorer;

    @Inject
    public WordBusinessImpl() {

    }

    /*主页数据内存缓存*/
    private final List<WordModel> homeWordModels = Collections.synchronizedList(new ArrayList<WordModel>());
    private HomeWordListChangedListener homeWordListChangedListener;

    /*设置缓存*/
    private final List<AlarmModel> setAlarmModels = Collections.synchronizedList(new ArrayList<AlarmModel>());

    @Override
    public void getRecentlyWordList(final CommonCallback<List<WordModel>> commonCallback) {
        if (commonCallback == null) {
            return;
        }
        if (homeWordModels.size() != 0) {
            commonCallback.onSuccess(homeWordModels);
            return;
        }
        Flowable.just(new Object())
                .subscribeOn(Schedulers.io())
                .flatMap(wordStorer.getWordList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    homeWordModels.addAll(result);
                    commonCallback.onSuccess(homeWordModels);
                }, (e) -> {
                    commonCallback.onError(0, e.getMessage());
                });
    }

    @Override
    public void searchWord(final String wordName, final CommonCallback<WordModel> commonCallback) {
        Flowable.just(wordName)
                .subscribeOn(Schedulers.io())
                .flatMap(wordModule.searchWord())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    wordStorer.saveWord(result);
                    updateViewCache(result);
                    commonCallback.onSuccess(result);
                }, (e) -> {
                    commonCallback.onError(0, e.getMessage());
                });
    }

    private void updateViewCache(WordModel wordModel) {
        //home 数据更新
        homeWordModels.add(0, wordModel);
        if (homeWordListChangedListener != null) {
            homeWordListChangedListener.onWordListChanged();
        }
    }

    @Override
    public void getAlarmList(final CommonCallback<List<AlarmModel>> commonCallback) {
        if (commonCallback == null) {
            return;
        }
        if (setAlarmModels.size() != 0) {
            commonCallback.onSuccess(setAlarmModels);
            return;
        }

        Flowable.just(new Object())
                .subscribeOn(Schedulers.io())
                .flatMap(alarmStorer.getAlarmList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    if (result != null && result.size() != 0) {
                        setAlarmModels.addAll(result);
                        commonCallback.onSuccess(setAlarmModels);
                    } else {
                        initAlarm(commonCallback);
                    }
                }, (e) -> {
                    commonCallback.onError(0, e.getMessage());
                });
    }

    private void initAlarm(CommonCallback<List<AlarmModel>> commonCallback) {
        AlarmModel alarmModel = new AlarmModel(0, 0, 0, true);
        setAlarmModels.add(alarmModel);
        setAlarm(alarmModel, 8, 0, true, new CommonCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                commonCallback.onSuccess(setAlarmModels);
            }

            @Override
            public void onError(int code, String message) {
                commonCallback.onError(code, message);
            }
        });
    }

    @Override
    public AlarmModel getCacheAlarmList() {
        if (setAlarmModels.size() != 0) {
            return setAlarmModels.get(0);
        }
        return null;
    }

    @Override
    public void setAlarm(final AlarmModel alarmModel, int hour, int minute, final boolean open, final CommonCallback<Void> commonCallback) {
        if (commonCallback == null) {
            return;
        }

        Flowable.just(new AlarmSettingParams(alarmModel, hour, minute, open))
                .subscribeOn(Schedulers.io())
                .flatMap(alarmModule.setAlarm())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    alarmModel.setOpen(open);
                    alarmStorer.saveAlarm(alarmModel);
                    commonCallback.onSuccess(null);
                }, (e) -> {
                    commonCallback.onError(0, e.getMessage());
                });
    }

    @Override
    public void setHomeWordListChangedListener(HomeWordListChangedListener homeWordListChangedListener) {
        this.homeWordListChangedListener = homeWordListChangedListener;
    }
}
