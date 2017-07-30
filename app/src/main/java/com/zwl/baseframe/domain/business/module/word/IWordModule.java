package com.zwl.baseframe.domain.business.module.word;


import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.implementz.CommonCallback;

/**
 * Created by hasee on 2017/6/26.
 */

public interface IWordModule {
    void searchWord(String wordName, CommonCallback<WordModel> commonCallback);
}
