package com.zwl.baseframe.domain.ui.main;


import com.zwl.baseframe.domain.business.model.WordModel;
import com.zwl.baseframe.domain.ui.base.IBasePresenter;
import com.zwl.baseframe.domain.ui.base.IBaseView;

import java.util.List;

/**
 * @author velen
 * @date 2017/3/23
 */
public class MainContract {
    public interface IMainView extends IBaseView {
        void showSearchResult(WordModel wordModel);

        void showSavedWords(List<WordModel> wordModels);

        void notifyWordListChange();

        void clearSearch();
    }

    public interface IMainPresenter extends IBasePresenter<IMainView> {
        void searchWord(String query);
    }
}
