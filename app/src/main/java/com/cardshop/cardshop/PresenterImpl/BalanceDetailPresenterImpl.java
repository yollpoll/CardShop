package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.BalanceDetailContract;

public class BalanceDetailPresenterImpl extends BalanceDetailContract.IPresenter {
    private BalanceDetailContract.IView mView;

    public BalanceDetailPresenterImpl(BalanceDetailContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }
}
