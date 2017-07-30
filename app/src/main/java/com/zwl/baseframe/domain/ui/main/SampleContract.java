package com.zwl.baseframe.domain.ui.main;


import com.zwl.baseframe.domain.business.model.SampleModel2;
import com.zwl.baseframe.domain.ui.base.IBasePresenter;
import com.zwl.baseframe.domain.ui.base.IBaseView;

/**
 * @author velen
 * @date 2017/3/23
 */
public class SampleContract {
    public interface ILoginView extends IBaseView {
        void setUser(SampleModel2 sampleModel2);
    }

    public interface ILoginPresenter extends IBasePresenter<ILoginView> {
        void handleLogin(String email, String pwd);
    }
}
