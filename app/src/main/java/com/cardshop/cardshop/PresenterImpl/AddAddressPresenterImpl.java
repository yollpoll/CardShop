package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.AddAddressContract;

public class AddAddressPresenterImpl extends AddAddressContract.Presenter {
    private AddAddressContract.IView mView;

    public AddAddressPresenterImpl(AddAddressContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }
}
