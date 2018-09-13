package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.MainContract;

public class MainPresenterImpl extends MainContract.MainPresenter {
    private MainContract.MainView mView;
    private String initContent = "";

    public MainPresenterImpl(MainContract.MainView mView, String initContent) {
        this.mView = mView;
        this.initContent = initContent;
        mView.setPresenter(this);
    }


    @Override
    public void start() {
    }
}
