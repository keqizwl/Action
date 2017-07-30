package com.zwl.baseframe.domain.business.module.word.storer;


import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.implementz.CommonCallback;

import java.util.List;

/**
 * Created by hasee on 2017/6/26.
 */

public interface IWordStorer {
    void getWordList(CommonCallback<List<WordModel>> commonCallback);

    void saveWord(WordModel wordModel);
}
