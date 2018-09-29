package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface InputNewPhoneContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void checkInput(String input);
    }

    interface IView extends IBaseView<InputNewPhoneContract.IPresenter> {
        void setCurrentPhone(String content, int start);

        void onCheckInput(boolean result, String message);
    }
}
