package com.zwl.baseframe.domain.ui.settting;


import com.zwl.baseframe.domain.ui.base.IBasePresenter;
import com.zwl.baseframe.domain.ui.base.IBaseView;

/**
 * @author velen
 * @date 2017/3/23
 */
public class SettingContract {
    public interface ISettingView extends IBaseView {
    }

    public interface ISettingPresenter extends IBasePresenter<ISettingView> {
    }
}
