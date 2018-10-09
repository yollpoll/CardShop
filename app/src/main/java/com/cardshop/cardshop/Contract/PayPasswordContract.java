package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface PayPasswordContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void sendSms();

        public abstract void vertifySms(String code);
    }

    interface IView extends IBaseView<PayPasswordContract.IPresenter> {
        void showCountDown(String count);

        void onCountDownFinish();

        void onVertifySuccess();
    }
}
