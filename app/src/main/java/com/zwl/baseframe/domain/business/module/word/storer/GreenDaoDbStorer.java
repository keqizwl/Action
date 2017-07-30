package com.zwl.baseframe.domain.business.module.word.storer;


import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.domain.business.module.alarm.IAlarmStorer;
import com.zwl.baseframe.implementz.CommonCallback;

import java.util.List;

/**
 * Created by hasee on 2017/6/26.
 */

public class GreenDaoDbStorer implements IWordStorer, IAlarmStorer {
    @Override
    public void getWordList(CommonCallback<List<WordModel>> commonCallback) {

    }

    @Override
    public void saveWord(WordModel wordModel) {

    }

    @Override
    public void getAlarmList(CommonCallback<List<AlarmModel>> commonCallback) {

    }

    @Override
    public void saveAlarm(AlarmModel alarmModel) {

    }
}
