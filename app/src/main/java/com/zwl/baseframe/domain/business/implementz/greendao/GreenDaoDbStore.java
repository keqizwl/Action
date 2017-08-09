package com.zwl.baseframe.domain.business.implementz.greendao;


import android.util.Log;

import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.domain.business.module.alarm.IAlarmStorer;
import com.zwl.baseframe.domain.business.module.word.storer.IWordStore;
import com.zwl.baseframe.implementz.di.scope.AppScope;
import com.zwl.greendao.Alarm;
import com.zwl.greendao.AlarmDao;
import com.zwl.greendao.Word;
import com.zwl.greendao.WordDao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * Created by hasee on 2017/6/26.
 */

@AppScope
public class GreenDaoDbStore implements IWordStore, IAlarmStorer {
    private static final String TAG = GreenDaoDbStore.class.getSimpleName();
    @Inject
    WordDao wordDao;

    @Inject
    AlarmDao alarmDao;

    @Inject
    public GreenDaoDbStore() {
    }

    @Override
    public Function<Object, Flowable<List<WordModel>>> getWordList() {
        return new Function<Object, Flowable<List<WordModel>>>() {
            @Override
            public Flowable<List<WordModel>> apply(Object obj) throws Exception {
                long latestTime = System.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000;
                List<Word> words = wordDao
                        .queryBuilder()
                        .where(WordDao.Properties.SaveTime.ge(latestTime))
                        .orderDesc(WordDao.Properties.SaveTime)
                        .build()
                        .list();
                List<WordModel> wordModels = new ArrayList<>();
                if (words != null && words.size() != 0) {
                    for (Word word : words) {
                        wordModels.add(DbConverter.convertToWordModel(word));
                    }
                }
                Log.d(TAG, "words size = " + wordModels.size());
                return Flowable.just(wordModels);
            }
        };
    }

    @Override
    public void saveWord(WordModel wordModel) {
        Word word = wordDao.queryBuilder().where(WordDao.Properties.Name.eq(wordModel.getName())).build().unique();
        if (word == null) {
            word = DbConverter.convertWordModel(wordModel);
        } else {
            word.setSaveTime(System.currentTimeMillis());
        }
        wordDao.insertOrReplace(word);
    }

    @Override
    public Function<Object, Flowable<List<AlarmModel>>> getAlarmList() {
        return new Function<Object, Flowable<List<AlarmModel>>>() {
            @Override
            public Flowable<List<AlarmModel>> apply(Object obj) throws Exception {
                List<Alarm> alarms = alarmDao
                        .queryBuilder()
                        .orderDesc(AlarmDao.Properties.Hour)
                        .build()
                        .list();
                List<AlarmModel> alarmModels = new ArrayList<>();
                if (alarms != null && alarms.size() != 0) {
                    for (Alarm alarm : alarms) {
                        alarmModels.add(DbConverter.convertToAlarmModel(alarm));
                    }
                }
                Log.d(TAG, "alarmModels size = " + alarmModels.size());
                return Flowable.just(alarmModels);
            }
        };
    }

    @Override
    public void saveAlarm(AlarmModel alarmModel) {
        alarmDao.insertOrReplaceInTx(DbConverter.convertAlarmModel(alarmModel));
    }
}
