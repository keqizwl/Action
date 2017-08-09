package com.zwl.baseframe.domain.business.implementz.android;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;

import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.domain.business.module.alarm.AlarmSettingParams;
import com.zwl.baseframe.domain.business.module.alarm.IAlarmModule;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

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
    public Function<AlarmSettingParams, Flowable<AlarmModel>> setAlarm() {
        return new Function<AlarmSettingParams, Flowable<AlarmModel>>() {
            @Override
            public Flowable<AlarmModel> apply(AlarmSettingParams alarmSettingParams) {
                AlarmModel alarmModel = alarmSettingParams.getAlarmModel();

                AlarmManager am = (AlarmManager) application.getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(application,
                        AlaramReceiver.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        application, 0, intent, 0);
                if (!alarmSettingParams.isOpen()) {
                    am.cancel(sender);
                } else {
                    // We want the alarm to go off 10 seconds from now.
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, alarmSettingParams.getHour() != -1 ? alarmSettingParams.getHour() : alarmModel.getHour());
                    calendar.set(Calendar.MINUTE, alarmSettingParams.getMinute() != -1 ? alarmSettingParams.getMinute() : alarmModel.getMinute());
                    calendar.set(Calendar.SECOND, 0);
                    // Schedule the alarm!
                    am.setRepeating(AlarmManager.RTC_WAKEUP,
                            calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, sender);
                }

                alarmModel.setHour(alarmSettingParams.getHour());
                alarmModel.setMinute(alarmSettingParams.getMinute());
                alarmModel.setOpen(alarmSettingParams.isOpen());
                return Flowable.just(alarmModel);
            }
        };

    }
}
