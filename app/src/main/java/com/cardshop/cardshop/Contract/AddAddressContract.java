package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.AddressModule;

public interface AddAddressContract {
    abstract class Presenter<V> extends BasePresenter<V> {
        public abstract void checkInput(String name, String phone, String address, String area, boolean isDefault);
    }

    interface IView extends IBaseView<AddAddressContract.Presenter> {
        void init(AddressModule addressModule);
    }
}
