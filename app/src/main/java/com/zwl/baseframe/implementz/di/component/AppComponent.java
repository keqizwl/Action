package com.zwl.baseframe.implementz.di.component;

import android.app.Application;

import com.zwl.baseframe.BaseApplication;
import com.zwl.baseframe.domain.business.implementz.di.module.BusinessModule;
import com.zwl.baseframe.domain.ui.implementz.di.component.ActivityCompontent;
import com.zwl.baseframe.domain.ui.implementz.di.module.ActivityModule;
import com.zwl.baseframe.implementz.di.module.AppModule;
import com.zwl.baseframe.implementz.di.scope.AppScope;

import dagger.Component;

/**
 * @author velen
 * @date 2017/3/23
 */
@AppScope
@Component(modules = {AppModule.class, BusinessModule.class})
public interface AppComponent {
    void inject(BaseApplication application);

    Application context();

    ActivityCompontent newActivityCompontent(ActivityModule activityModule);
}
