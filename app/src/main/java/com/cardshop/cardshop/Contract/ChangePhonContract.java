package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface ChangePhonContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void startCountDown();
        public abstract void sendSms();

        public abstract void vertifySms(String code);
    }

    interface IView extends IBaseView<ChangePhonContract.IPresenter> {
        void showCountDown(String count);

        void setNewPhone(String phone);

        void onCountDownFinsh();
        void onVertifySuccess(String phone);
    }
}
