package com.zwl.baseframe.domain.ui.settting;


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

            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }

    @Override
    public void setView(final SettingContract.ISettingView iSettingView) {
        mISettingView = iSettingView;
    }
}
