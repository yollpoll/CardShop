package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface AddCardContract {
    abstract class Presenter<V> extends BasePresenter<V> {
        public abstract void checkInput(String name, String code, String identity, String phone, String bank);

        public abstract void findBankName(String code);
    }

    interface IView extends IBaseView<AddCardContract.Presenter> {
        void gotoNext(String name, String code, String identity, String phone, String bank);

        void setBankName(String name);
    }
}
