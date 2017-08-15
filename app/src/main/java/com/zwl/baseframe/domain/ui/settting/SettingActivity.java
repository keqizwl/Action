package com.zwl.baseframe.domain.ui.settting;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TimePicker;

import com.zwl.base_lib.view.toolbar.ToolbarHelper;
import com.zwl.baseframe.BaseApplication;
import com.zwl.baseframe.R;
import com.zwl.baseframe.databinding.ActivitySettingBinding;
import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.domain.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * A login screen that offers login via email/password.
 */
public class SettingActivity extends BaseActivity implements SettingContract.ISettingView, View.OnClickListener, TimePickerDialog.OnTimeSetListener, CompoundButton.OnCheckedChangeListener {

    private ActivitySettingBinding binding;
    private TimePickerDialog selectTimeDialog;

    @Inject
    SettingContract.ISettingPresenter mISettingPresenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);

        initView();

        mISettingPresenter.setView(this);

        mISettingPresenter.start();
    }

    @Override
    protected void injectActivity() {
        BaseApplication.getInstance().getActivityComponent(this).inject(this);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        ToolbarHelper.setPrimaryToolbar(binding.toolbar, R.mipmap.ic_back, getString(R.string.action_settings), R.color.white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.cbAlarm.setOnCheckedChangeListener(this);
        binding.tvSoftDesc.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.fl_alarm:
                showCalenda(mISettingPresenter.getAlarmModel());
                break;
            case R.id.tv_soft_desc:
                break;
        }
    }

    private void showCalenda(AlarmModel alarmModel) {
        if (selectTimeDialog == null) {
            selectTimeDialog = new TimePickerDialog(this, this, alarmModel.getHour(), alarmModel.getMinute(), true);
        }
        selectTimeDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mISettingPresenter.setAlarmTime(hourOfDay, minute, binding.cbAlarm.isChecked());
    }

    @Override
    public void showAlarm(AlarmModel alarmModel) {
        binding.tvTime.setText(String.format("%02d:%02d", alarmModel.getHour(), alarmModel.getMinute()));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mISettingPresenter.setAlarmTime(-1, -1, binding.cbAlarm.isChecked());
    }
}

