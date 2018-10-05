package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface InputRealNameContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void checkInput(String name, String code);
    }

    interface IView extends IBaseView<InputRealNameContract.IPresenter> {
    }
}
