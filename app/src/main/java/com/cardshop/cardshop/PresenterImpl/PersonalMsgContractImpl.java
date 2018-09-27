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
        mView.setAvatar(UserModule.getCurrentUser().getUser_pic());
        mView.setPhone(UserModule.getCurrentUser().getUser_phone());
    }

    @Override
    public void vertifyPayPassword(String psw) {
        mView.getVertifyPswResult(true);
    }
}
