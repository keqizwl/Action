package com.zwl.baseframe.domain.business.implementz.di.module;

import com.zwl.baseframe.domain.business.implementz.android.AlarmModuleImpl;
import com.zwl.baseframe.domain.business.implementz.baidu.BaiDuTranslater;
import com.zwl.baseframe.domain.business.implementz.greendao.GreenDaoDbStore;
import com.zwl.baseframe.domain.business.module.alarm.IAlarmModule;
import com.zwl.baseframe.domain.business.module.alarm.IAlarmStorer;
import com.zwl.baseframe.domain.business.module.word.IWordModule;
import com.zwl.baseframe.domain.business.module.word.storer.IWordStore;
import com.zwl.greendao.AlarmDao;
import com.zwl.greendao.DaoSession;
import com.zwl.greendao.WordDao;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hasee on 2017/7/30.
 */
@Module
public class BusinessModule {
    private DaoSession daoSession;

    public BusinessModule(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Provides
    public WordDao provideWordDao() {
        return daoSession.getWordDao();
    }

    @Provides
    public AlarmDao provideAlarmDao() {
        return daoSession.getAlarmDao();
    }

    @Provides
    public IWordModule provideWordModule(BaiDuTranslater baiDuTranslater) {
        return baiDuTranslater;
    }

    @Provides
    public IAlarmModule provideAlarmModule(AlarmModuleImpl alarmModuleImple) {
        return alarmModuleImple;
    }

    @Provides
    public IWordStore provideWordStore(GreenDaoDbStore greenDaoDbStore) {
        return greenDaoDbStore;
    }
    @Provides
    public IAlarmStorer provideAlarmStorer(GreenDaoDbStore greenDaoDbStore) {
        return greenDaoDbStore;
    }
}
