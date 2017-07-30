package com.zwl.baseframe.domain.business.module.alarm;


import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.implementz.CommonCallback;

/**
 * Created by hasee on 2017/6/26.
 */

public interface IAlarmModule {

    void setAlarm(AlarmModel alarmModel, boolean open, CommonCallback<Void> commonCallback);
}
