package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface SetPayPassword2Contract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void setPsw(String psw);

        public abstract void confrimPsw(String psw);
    }

    interface IView extends IBaseView<SetPayPassword2Contract.IPresenter> {
        void onFirstInput(boolean result, String tip);

        void onConfirmInput(boolean result, String tip);

        void onSuccess();
    }
}
