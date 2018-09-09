package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface LoginContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void login(String userName, String password);

        public abstract void checkNull(String userName, String password);
    }

    interface IView extends IBaseView<IPresenter> {
        void showPassword(boolean isShow);

        void onLoginResult(boolean result, String message);

        void setBtnLoginEnable(boolean enable);
    }
}
