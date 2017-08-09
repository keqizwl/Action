package com.zwl.baseframe.domain.business.module.alarm;

import com.zwl.baseframe.domain.business.model.AlarmModel;

/**
 * Created by hasee on 2017/8/9.
 */

public class AlarmSettingParams {
    private AlarmModel alarmModel;
    private int hour = -1;
    private int minute = -1;
    private boolean open = false;

    public AlarmSettingParams(AlarmModel alarmModel, int hour, int minute, boolean open) {
        this.alarmModel = alarmModel;
        this.hour = hour;
        this.minute = minute;
        this.open = open;
    }

    public AlarmModel getAlarmModel() {
        return alarmModel;
    }

    public void setAlarmModel(AlarmModel alarmModel) {
        this.alarmModel = alarmModel;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
