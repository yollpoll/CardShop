package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface AddAddressContract {
    abstract class Presenter<V> extends BasePresenter<V> {
    }

    interface IView extends IBaseView<AddAddressContract.Presenter> {
    }
}
