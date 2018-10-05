package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.RealMsgContract;
import com.cardshop.cardshop.Module.UserModule;

public class RealMsgPresenterImpl extends RealMsgContract.IPresenter {
    private RealMsgContract.IView mView;

    public RealMsgPresenterImpl(RealMsgContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        setData();
    }

    private void setData() {
        String name = UserModule.getCurrentUser().getMember().getMemberTruename();
        String code = UserModule.getCurrentUser().getMember().getMemberIdCard();
        mView.setData(name, code);
    }
}
