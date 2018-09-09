package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface RegisterContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void getVertifyCode(String phone);

        public abstract void register(String phone, String password,
                                      String confirmPassword, String vertifyCode);

    }

    interface IView extends IBaseView<IPresenter> {

    }
}
