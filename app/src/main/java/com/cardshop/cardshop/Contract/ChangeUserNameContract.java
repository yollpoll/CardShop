package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface ChangeUserNameContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void change(String userName);
    }

    interface IView extends IBaseView<ChangeUserNameContract.IPresenter> {
        void onResult(String userName);
    }
}
