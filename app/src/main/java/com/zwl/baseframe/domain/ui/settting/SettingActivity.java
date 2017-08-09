package com.zwl.baseframe.domain.ui.settting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zwl.baseframe.BaseApplication;
import com.zwl.baseframe.R;
import com.zwl.baseframe.databinding.ActivitySettingBinding;
import com.zwl.baseframe.domain.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * A login screen that offers login via email/password.
 */
public class SettingActivity extends BaseActivity implements SettingContract.ISettingView, View.OnClickListener {

    private ActivitySettingBinding binding;

    @Inject
    SettingContract.ISettingPresenter mISettingPresenter;

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

    }

    @Override
    public void onClick(final View v) {
    }
}

