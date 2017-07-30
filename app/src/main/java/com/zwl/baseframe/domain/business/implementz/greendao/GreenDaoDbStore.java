package com.zwl.baseframe.domain.business.implementz.greendao;


import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.domain.business.module.alarm.IAlarmStorer;
import com.zwl.baseframe.domain.business.module.word.storer.IWordStore;
import com.zwl.baseframe.implementz.CommonCallback;
import com.zwl.baseframe.implementz.di.scope.AppScope;
import com.zwl.greendao.Alarm;
import com.zwl.greendao.AlarmDao;
import com.zwl.greendao.Word;
import com.zwl.greendao.WordDao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by hasee on 2017/6/26.
 */

@AppScope
public class GreenDaoDbStore implements IWordStore, IAlarmStorer {
    @Inject
    WordDao wordDao;

    @Inject
    AlarmDao alarmDao;

    @Inject
    public GreenDaoDbStore() {
    }

    @Override
    public void getWordList(CommonCallback<List<WordModel>> commonCallback) {
        if (commonCallback == null) {
            return;
        }

        List<Word> words = wordDao.queryBuilder().build().list();
        List<WordModel> wordModels = new ArrayList<>();
        if (words != null) {
            for (Word word : words) {
                wordModels.add(DbConverter.convertToWordModel(word));
            }
        }

        commonCallback.onSuccess(wordModels);
    }

    @Override
    public void saveWord(WordModel wordModel) {
        wordDao.insertOrReplaceInTx(DbConverter.convertWordModel(wordModel));
    }

    @Override
    public void getAlarmList(CommonCallback<List<AlarmModel>> commonCallback) {
        if (commonCallback == null) {
            return;
        }

        List<Alarm> alarms = alarmDao.queryBuilder().build().list();
        List<AlarmModel> alarmModels = new ArrayList<>();
        if (alarms != null) {
            for (Alarm alarm : alarms) {
                alarmModels.add(DbConverter.convertToAlarmModel(alarm));
            }
        }

        commonCallback.onSuccess(alarmModels);
    }

    @Override
    public void saveAlarm(AlarmModel alarmModel) {
        alarmDao.insertOrReplaceInTx(DbConverter.convertAlarmModel(alarmModel));
    }
}
