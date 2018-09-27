package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.ChangeUserNameContract;

public class ChangeUserNamePresenterImpl extends ChangeUserNameContract.IPresenter {
    private ChangeUserNameContract.IView mView;

    public ChangeUserNamePresenterImpl(ChangeUserNameContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }
}
