package com.zwl.baseframe.domain.ui.settting;


import com.zwl.baseframe.BaseApplication;
import com.zwl.baseframe.R;
import com.zwl.baseframe.domain.business.interfacez.IWordBusiness;
import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.domain.ui.implementz.di.scope.ActivityScope;
import com.zwl.baseframe.implementz.CommonCallback;

import java.util.List;

import javax.inject.Inject;

/**
 * @author velen
 * @date 2017/3/23
 */

@ActivityScope
public class SettingPresenter implements SettingContract.ISettingPresenter {
    private SettingContract.ISettingView mISettingView;

    @Inject
    IWordBusiness wordBusiness;

    @Inject
    public SettingPresenter() {

    }

    @Override
    public void start() {
        wordBusiness.getAlarmList(new CommonCallback<List<AlarmModel>>() {
            @Override
            public void onSuccess(List<AlarmModel> alarmModels) {
                mISettingView.showAlarm(alarmModels.get(0));
            }

            @Override
            public void onError(int code, String message) {
                mISettingView.showToast(message);
            }
        });
    }

    @Override
    public void setView(final SettingContract.ISettingView iSettingView) {
        mISettingView = iSettingView;
    }

    @Override
    public AlarmModel getAlarmModel() {
        return wordBusiness.getCacheAlarmList();
    }

    @Override
    public void setAlarmTime(int hourOfDay, int minute, boolean checked) {
        wordBusiness.setAlarm(getAlarmModel(), hourOfDay, minute, checked, new CommonCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mISettingView.showAlarm(getAlarmModel());
                mISettingView.showToast(BaseApplication.getInstance().getString(R.string.success));
            }

            @Override
            public void onError(int code, String message) {
                mISettingView.showToast(message);
            }
        });
    }
}
