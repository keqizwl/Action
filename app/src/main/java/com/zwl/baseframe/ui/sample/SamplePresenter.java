package com.zwl.baseframe.ui.sample;


import com.zwl.baseframe.business.CommonCallback;
import com.zwl.baseframe.business.sample.SampleBusinessImpl;
import com.zwl.baseframe.di.scope.ActivityScope;
import com.zwl.baseframe.model.SampleModel;

import javax.inject.Inject;

/**
 * @author velen
 * @date 2017/3/23
 */

@ActivityScope
public class SamplePresenter implements SampleContract.ILoginPresenter {
    private SampleContract.ILoginView mILoginView;

    @Inject
    SampleBusinessImpl mAccountBusiness;

    @Inject
    public SamplePresenter() {

    }

    @Override
    public void start() {
        mILoginView.showToast("hello world");
    }

    @Override
    public void setView(final SampleContract.ILoginView iLoginView) {
        mILoginView = iLoginView;
    }

    @Override
    public void handleLogin(String email, String pwd) {
        mAccountBusiness.login(email, pwd, new CommonCallback<SampleModel>() {
            @Override
            public void onSuccess(final SampleModel sampleModel) {
                mILoginView.showToast(sampleModel.getMessage());
            }

            @Override
            public void onError(final int code, final String message) {
                mILoginView.showToast(code + message);
            }
        });
    }
}
