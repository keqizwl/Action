package com.zwl.baseframe.domain.business.module.word;


import com.zwl.baseframe.domain.business.model.WordModel;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * Created by hasee on 2017/6/26.
 */

public interface IWordModule {
    Function<String, Flowable<WordModel>> searchWord();
}
