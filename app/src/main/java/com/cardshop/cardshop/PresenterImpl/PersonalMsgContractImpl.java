package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.PersonalContract;

public class PersonalMsgContractImpl extends PersonalContract.IPresenter<PersonalContract.IView> {
    private PersonalContract.IView mView;

    public PersonalMsgContractImpl(PersonalContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }
}
