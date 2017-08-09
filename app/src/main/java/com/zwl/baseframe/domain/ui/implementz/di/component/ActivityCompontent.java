package com.zwl.baseframe.domain.ui.implementz.di.component;

import android.app.Activity;

import com.zwl.baseframe.domain.ui.implementz.di.module.ActivityModule;
import com.zwl.baseframe.domain.ui.implementz.di.scope.ActivityScope;
import com.zwl.baseframe.domain.ui.main.MainActivity;
import com.zwl.baseframe.domain.ui.sample.SampleActivity;
import com.zwl.baseframe.domain.ui.settting.SettingActivity;

import dagger.Subcomponent;

/**
 * @author velen
 * @date 2017/3/23
 */
@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityCompontent {
    Activity context();

    void inject(SampleActivity sampleActivity);

    void inject(MainActivity mainActivity);

    void inject(SettingActivity settingActivity);
}
