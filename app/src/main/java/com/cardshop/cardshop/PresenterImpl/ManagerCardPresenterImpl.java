package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.ManagerCardContract;
import com.cardshop.cardshop.Module.CardModule;

public class ManagerCardPresenterImpl extends ManagerCardContract.IPresenter {

    private ManagerCardContract.IView mView;
    private CardModule cardModule;

    public ManagerCardPresenterImpl(ManagerCardContract.IView mView, CardModule cardModule) {
        this.mView = mView;
        this.cardModule = cardModule;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.setCard(cardModule);
    }

    @Override
    public void delCard() {

    }
}
