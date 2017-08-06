package com.zwl.baseframe.domain.ui.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zwl.baseframe.R;
import com.zwl.baseframe.BaseApplication;
import com.zwl.baseframe.databinding.ActivitySampleBinding;
import com.zwl.baseframe.domain.business.model.SampleModel2;
import com.zwl.baseframe.domain.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * A login screen that offers login via email/password.
 */
public class SampleActivity extends BaseActivity implements SampleContract.ILoginView, View.OnClickListener {

    private ActivitySampleBinding binding;

    @Inject
    SampleContract.ILoginPresenter mILoginPresenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample);

        initView();

        mILoginPresenter.setView(this);

        mILoginPresenter.start();
    }

    @Override
    protected void injectActivity() {
        BaseApplication.getInstance().getActivityComponent(this).inject(this);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        binding.email.setHint(R.string.login_email_promt);
    }

    @Override
    public void setUser(final SampleModel2 sampleModel2) {
        binding.setSampleModel2(sampleModel2);
    }

    @Override
    public void onClick(final View v) {
        int i = v.getId();
        if (i == R.id.email_sign_in_button) {
            mILoginPresenter.handleLogin(binding.email.getText().toString(), binding.password.getText().toString());

        }
    }
}

