package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.WithdrawContract;

public class WithdrawPresenterImpl extends WithdrawContract.IPresenter {
    private WithdrawContract.IView mView;

    public WithdrawPresenterImpl(WithdrawContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }
}
