package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.CardContract;

public class CardPresenterImpl extends CardContract.IPresenter<CardContract.IView> {
    private CardContract.IView mView;

    public CardPresenterImpl(CardContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }
}
