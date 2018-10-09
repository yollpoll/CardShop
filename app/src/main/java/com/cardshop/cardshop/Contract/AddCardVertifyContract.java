package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface AddCardVertifyContract {
    abstract class Presenter<V> extends BasePresenter<V> {
        public abstract void vertifySms(String code);
        public abstract void sendSms();
    }

    interface IView extends IBaseView<AddCardVertifyContract.Presenter> {
        void showPhoneNum(String phone);

        void onAddResulte(boolean result, String message);

        void showCountDown(String count);

        void onCountDownFinish();
    }
}
