package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.PayPasswordContract;

public class SetPayPasswordPresenterImpl extends PayPasswordContract.IPresenter {
    private PayPasswordContract.IView mView;

    public SetPayPasswordPresenterImpl(PayPasswordContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }
}
