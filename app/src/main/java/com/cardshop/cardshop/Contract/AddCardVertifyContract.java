package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface AddCardVertifyContract {
    abstract class Presenter<V> extends BasePresenter<V> {
    }

    interface IView extends IBaseView<AddCardVertifyContract.Presenter> {
        void showCountDown(String count);

        void showPhoneNum(String phone);
    }
}
