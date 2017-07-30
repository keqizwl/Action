package com.zwl.baseframe.domain.business.module.alarm;


import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.implementz.CommonCallback;

import java.util.List;

/**
 * Created by hasee on 2017/6/26.
 */

public interface IAlarmStorer {
    void getAlarmList(CommonCallback<List<AlarmModel>> commonCallback);

    void saveAlarm(AlarmModel alarmModel);
}
