package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.UserModule;
import com.qiyukf.unicorn.api.ConsultSource;

public interface MineContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void gotoCustomerService();
        public abstract void gotoRealName();
        public abstract void updateUserInfo();
    }

    interface IView extends IBaseView<MineContract.IPresenter> {
        void setUserData(UserModule userData);

        void gotoService(String title, ConsultSource source);
    }
}
