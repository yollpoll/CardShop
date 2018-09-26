package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.BalanceContract;

public class BalancePresenterImpl extends BalanceContract.IPresenter<BalanceContract.IView> {
    private BalanceContract.IView mView;

    public BalancePresenterImpl(BalanceContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }
}
