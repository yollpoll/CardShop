package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface RegisterContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void getVertifyCode(String phone);

        public abstract void register(String phone, String password,
                                      String confirmPassword, String vertifyCode);

        public abstract boolean checkPhone(String phone);

        public abstract void checkRegisterInput(String phone, String password,
                                           String confirmPassword, String vertifyCode);
//        public abstract boolean checkPsd(String psd);
//
//        public abstract boolean checkConfirmPsd(String psd);
//
//        public abstract boolean checkVertifyCode(String vertifyCode);
//
//        public abstract boolean vertifyPsd(String psd, String confirmPsd);

    }

    interface IView extends IBaseView<IPresenter> {
        void showSendVertifyCode(String result);
    }
}
