package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface ForgetPasswordContract {
    abstract class Presenter<V> extends BasePresenter<V> {
        public abstract void getVertifyCode(String phone);

        public abstract boolean checkVertifyCode(String code);

        public abstract void checkInput(String phone, String vertifyCode, String psw, String cinfirmPsw);
        public abstract void forgetPassword(String phone,String psw);
    }

    interface IView extends IBaseView<Presenter> {
    }
}
