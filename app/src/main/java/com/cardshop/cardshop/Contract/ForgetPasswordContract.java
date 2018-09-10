package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface ForgetPasswordContract {
    abstract class Presenter<V> extends BasePresenter<V> {
        public abstract void getVertifyCode(String phone);

        public abstract void checkVertifyCode(String code);

        public abstract boolean checkPhone(String phone);

        public abstract boolean checkVertifyCodeNull(String code);

    }

    interface IView extends IBaseView<Presenter> {
        public void gotoNext();
    }
}
