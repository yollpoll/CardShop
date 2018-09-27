package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.RechargeContract;

public class RechargePresenterImpl extends RechargeContract.IPresenter {
    private RechargeContract.IView mView;

    public RechargePresenterImpl(RechargeContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }
}
