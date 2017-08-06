package com.zwl.baseframe.domain.business.implementz.baidu;


import com.google.gson.internal.LinkedTreeMap;
import com.zwl.baseframe.domain.business.implementz.util.JsonUtils;
import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.domain.business.module.Constants;
import com.zwl.baseframe.domain.business.module.word.IWordModule;
import com.zwl.baseframe.implementz.di.scope.AppScope;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * Created by hasee on 2017/6/27.
 */

@AppScope
public class BaiDuTranslater implements IWordModule {

    @Inject
    public BaiDuTranslater() {

    }

    @Override
    public Function<String, Flowable<WordModel>> searchWord() {
        return new Function<String, Flowable<WordModel>>() {
            @Override
            public Flowable<WordModel> apply(String wordName) throws Exception {
                TransApi api = new TransApi(Constants.BAIDU_TRANSLATE_APP_ID, Constants.BAIDU_TRANSLATE_SECURITY_KEY);
                String result = api.getTransResult(wordName, "auto", "zh");
                LinkedTreeMap<String, Object> transResult = JsonUtils.fromJson(result);
                ArrayList<LinkedTreeMap<String, String>> trans = (ArrayList<LinkedTreeMap<String, String>>) transResult.get("trans_result");
                return Flowable.just(new WordModel(0, wordName, trans.get(0).get("dst"), System.currentTimeMillis()));
            }
        };
    }
}
