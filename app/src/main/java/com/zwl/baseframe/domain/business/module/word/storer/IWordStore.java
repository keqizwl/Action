package com.zwl.baseframe.domain.business.module.word.storer;


import com.zwl.baseframe.domain.business.model.WordModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * Created by hasee on 2017/6/26.
 */

public interface IWordStore {
    Function<Object, Flowable<List<WordModel>>> getWordList();

    void saveWord(WordModel wordModel);
}
