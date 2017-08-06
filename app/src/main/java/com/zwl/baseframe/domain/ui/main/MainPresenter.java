package com.zwl.baseframe.domain.ui.main;


import android.util.Log;

import com.zwl.baseframe.domain.business.interfacez.IWordBusiness;
import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.domain.ui.implementz.di.scope.ActivityScope;
import com.zwl.baseframe.implementz.CommonCallback;

import java.util.List;

import javax.inject.Inject;

/**
 * @author velen
 * @date 2017/3/23
 */

@ActivityScope
public class MainPresenter implements MainContract.IMainPresenter, IWordBusiness.HomeWordListChangedListener {
    private static final String TAG = MainPresenter.class.getSimpleName();
    private MainContract.IMainView iMainView;

    @Inject
    IWordBusiness iWordBusiness;

    @Inject
    public MainPresenter() {
        iWordBusiness.setHomeWordListChangedListener(this);
    }

    @Override
    public void start() {
        Log.d(TAG, "start");
        iWordBusiness.getRecentlyWordList(new CommonCallback<List<WordModel>>() {
            @Override
            public void onSuccess(List<WordModel> wordModels) {
                iMainView.showSavedWords(wordModels);
            }

            @Override
            public void onError(int code, String message) {
                iMainView.showToast("failed code = " + code + " " + message);
            }
        });
    }

    @Override
    public void setView(final MainContract.IMainView iLoginView) {
        iMainView = iLoginView;
    }

    @Override
    public void searchWord(String query) {
        iWordBusiness.searchWord(query, new CommonCallback<WordModel>() {
            @Override
            public void onSuccess(WordModel wordModel) {
                iMainView.showSearchResult(wordModel);
            }

            @Override
            public void onError(int code, String message) {
                iMainView.showToast(message);
            }
        });
    }

    @Override
    public void onWordListChanged() {
        iMainView.notifyWordListChange();
    }
}
