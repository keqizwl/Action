package com.zwl.baseframe.domain.business.implementz.android;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;

import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.domain.business.module.alarm.IAlarmModule;
import com.zwl.baseframe.domain.ui.main.MainActivity;
import com.zwl.baseframe.implementz.CommonCallback;

import java.util.Calendar;

import javax.inject.Inject;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by hasee on 2017/7/30.
 */

public class AlarmModuleImpl implements IAlarmModule {

    @Inject
    Application application;

    @Inject
    public AlarmModuleImpl() {
    }

    @Override
    public void setAlarm(AlarmModel alarmModel, boolean open, CommonCallback<Void> commonCallback) {
        Intent intent = new Intent(application,
                MainActivity.class);
        PendingIntent sender = PendingIntent.getBroadcast(
                application, 0, intent, 0);

        // We want the alarm to go off 10 seconds from now.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, alarmModel.getHour());
        calendar.set(Calendar.MINUTE, alarmModel.getMinute());
        calendar.set(Calendar.SECOND, 0);
        // Schedule the alarm!
        AlarmManager am = (AlarmManager) application.getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, sender);

        if (commonCallback != null) {
            commonCallback.onSuccess(null);
        }
    }
}
