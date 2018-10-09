package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.PersonalContract;
import com.cardshop.cardshop.Module.UserModule;

public class PersonalMsgContractImpl extends PersonalContract.IPresenter<PersonalContract.IView> {
    private PersonalContract.IView mView;

    public PersonalMsgContractImpl(PersonalContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.setAvatar(UserModule.getCurrentUser().getMember().getMemberAvatar());
        mView.setPhone(UserModule.getCurrentUser().getMember().getMemberMobile());
        mView.setUserName(UserModule.getCurrentUser().getMember().getMemberName());
        mView.setWx(UserModule.getCurrentUser().getMember().getWeixinInfo());
        mView.setQQ(UserModule.getCurrentUser().getMember().getMemberQq());
    }

    @Override
    public void vertifyPayPassword(String psw) {
        mView.getVertifyPswResult(true);
    }
}
