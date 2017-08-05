package com.zwl.baseframe.domain.ui.implementz.di.module;

import android.app.Activity;

import com.zwl.baseframe.domain.business.implementz.WordBusinessImpl;
import com.zwl.baseframe.domain.business.interfacez.IWordBusiness;
import com.zwl.baseframe.domain.ui.main.MainContract;
import com.zwl.baseframe.domain.ui.main.MainPresenter;
import com.zwl.baseframe.domain.ui.sample.SampleContract;
import com.zwl.baseframe.domain.ui.sample.SamplePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author velen
 * @date 2017/3/23
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides
    public SampleContract.ILoginPresenter provideLoginPresent(SamplePresenter samplePresenter) {
        return samplePresenter;
    }

    @Provides
    public MainContract.IMainPresenter provideMainPresenter(MainPresenter mainPresenter) {
        return mainPresenter;
    }

    @Provides
    public IWordBusiness provideWordBusiness(WordBusinessImpl wordBusiness) {
        return wordBusiness;
    }
}
