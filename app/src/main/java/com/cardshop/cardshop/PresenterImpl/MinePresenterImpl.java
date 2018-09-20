package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.MineContract;
import com.cardshop.cardshop.Module.UserModule;

public class MinePresenterImpl extends MineContract.IPresenter<MineContract.IView> {
    private MineContract.IView mView;
    private UserModule userModule;

    public MinePresenterImpl(MineContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        userModule = UserModule.getCurrentUser();
        mView.setUserData(userModule);
    }
}
