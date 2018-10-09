package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface PersonalContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void vertifyPayPassword(String psw);
    }

    interface IView extends IBaseView<PersonalContract.IPresenter> {
        void setAvatar(String url);

        void setPhone(String phone);

        void getVertifyPswResult(boolean result);

        void setUserName(String userName);

        void setWx(String wx);

        void setQQ(String qq);
    }
}
