package com.zwl.baseframe.domain.ui.main;


import com.zwl.baseframe.domain.business.interfacez.IWordBusiness;
import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.domain.ui.implementz.di.scope.ActivityScope;
import com.zwl.baseframe.implementz.CommonCallback;

import javax.inject.Inject;

/**
 * @author velen
 * @date 2017/3/23
 */

@ActivityScope
public class MainPresenter implements MainContract.IMainPresenter {
    private MainContract.IMainView iMainView;

    @Inject
    IWordBusiness iWordBusiness;

    @Inject
    public MainPresenter() {

    }

    @Override
    public void start() {
        iMainView.showToast("hello world");
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
}
