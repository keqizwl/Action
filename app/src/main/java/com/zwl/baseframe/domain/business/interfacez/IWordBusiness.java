package com.zwl.baseframe.domain.business.interfacez;


import com.zwl.baseframe.domain.business.model.AlarmModel;
import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.implementz.CommonCallback;

import java.util.List;

/**
 * Created by hasee on 2017/6/26.
 */

public interface IWordBusiness {
    void getRecentlyWordList(CommonCallback<List<WordModel>> commonCallback);

    void searchWord(String wordName, CommonCallback<WordModel> commonCallback);

    void getAlarmList(CommonCallback<List<AlarmModel>> alarmModelCommonCallback);

    AlarmModel getCacheAlarmList();

    void setAlarm(AlarmModel alarmModel, int hour, int minute, boolean open, CommonCallback<Void> commonCallback);

    void setHomeWordListChangedListener(HomeWordListChangedListener homeWordListChangedListener);

    interface HomeWordListChangedListener {
        void onWordListChanged();
    }
}
