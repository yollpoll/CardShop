package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.UserModule;

public interface MineContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
    }

    interface IView extends IBaseView<MineContract.IPresenter> {
        void setUserData(UserModule userData);
    }
}
