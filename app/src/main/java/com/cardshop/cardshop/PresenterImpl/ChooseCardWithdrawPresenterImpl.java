package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.ChooseCardWidthdrawContract;

public class ChooseCardWithdrawPresenterImpl extends ChooseCardWidthdrawContract.IPresenter {
    private ChooseCardWidthdrawContract.IView mView;

    public ChooseCardWithdrawPresenterImpl(ChooseCardWidthdrawContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }
}
