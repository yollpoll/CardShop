package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface ChangePhonContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
    }

    interface IView extends IBaseView<ChangePhonContract.IPresenter> {
        void showCountDown(String count);

        void setNewPhone(String phone);
    }
}
