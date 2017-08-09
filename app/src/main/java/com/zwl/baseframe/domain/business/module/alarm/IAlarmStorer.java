package com.zwl.baseframe.domain.business.module.alarm;


import com.zwl.baseframe.domain.business.model.AlarmModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * Created by hasee on 2017/6/26.
 */

public interface IAlarmStorer {
    Function<Object, Flowable<List<AlarmModel>>> getAlarmList();

    void saveAlarm(AlarmModel alarmModel);
}
