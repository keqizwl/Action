package com.zwl.baseframe.domain.business.implementz;


import com.zwl.baseframe.domain.business.interfacez.IWordBusiness;
import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.domain.business.model.WordModel;
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
    @Inject
    IWordModule wordModule;
    @Inject
    IWordStore wordStorer;
    @Inject
    IAlarmModule alarmModule;
    @Inject
    IAlarmStorer alarmStorer;

    @Inject
    public WordBusinessImpl(){

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

        wordStorer.getWordList(new CommonCallback<List<WordModel>>() {
            @Override
            public void onSuccess(List<WordModel> wordModels) {
                homeWordModels.addAll(wordModels);
                commonCallback.onSuccess(homeWordModels);
            }

            @Override
            public void onError(int code, String message) {
                commonCallback.onError(code, message);
            }
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
        homeWordModels.add(wordModel);
        if (homeWordListChangedListener != null) {
            homeWordListChangedListener.onWordListChanged();
        }
    }

    @Override
    public void getAlarmList(final CommonCallback<List<AlarmModel>> alarmModelCommonCallback) {
        if (setAlarmModels.size() != 0) {
            alarmModelCommonCallback.onSuccess(setAlarmModels);
            return;
        }

        alarmStorer.getAlarmList(new CommonCallback<List<AlarmModel>>() {
            @Override
            public void onSuccess(List<AlarmModel> alarmModels) {
                setAlarmModels.addAll(alarmModels);
                alarmModelCommonCallback.onSuccess(setAlarmModels);
            }

            @Override
            public void onError(int code, String message) {
                alarmModelCommonCallback.onError(code, message);
            }
        });
    }

    @Override
    public void setAlarm(final AlarmModel alarmModel, final boolean open, final CommonCallback<Void> commonCallback) {
        alarmModule.setAlarm(alarmModel, open, new CommonCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                alarmModel.setOpen(open);
                alarmStorer.saveAlarm(alarmModel);
                commonCallback.onSuccess(null);
            }

            @Override
            public void onError(int code, String message) {
                commonCallback.onError(code, message);
            }
        });
    }

    @Override
    public void setHomeWordListChangedListener(HomeWordListChangedListener homeWordListChangedListener) {
        this.homeWordListChangedListener = homeWordListChangedListener;
    }
}
