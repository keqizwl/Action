package com.zwl.baseframe.domain.business.module.alarm;


import com.zwl.baseframe.domain.business.model.AlarmModel;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * Created by hasee on 2017/6/26.
 */

public interface IAlarmModule {
    Function<AlarmSettingParams, Flowable<AlarmModel>> setAlarm();
}
