package com.zwl.baseframe.domain.business.implementz.baidu;


import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.domain.business.module.Constants;
import com.zwl.baseframe.domain.business.module.word.IWordModule;
import com.zwl.baseframe.implementz.CommonCallback;
import com.zwl.baseframe.implementz.di.scope.AppScope;

import javax.inject.Inject;

/**
 * Created by hasee on 2017/6/27.
 */

@AppScope
public class BaiDuTranslater implements IWordModule {

    @Inject
    public BaiDuTranslater(){

    }

    @Override
    public void searchWord(String wordName, CommonCallback<WordModel> commonCallback) {
        TransApi api = new TransApi(Constants.BAIDU_TRANSLATE_APP_ID, Constants.BAIDU_TRANSLATE_SECURITY_KEY);

        api.getTransResult(wordName, "auto", "en");
    }
}
