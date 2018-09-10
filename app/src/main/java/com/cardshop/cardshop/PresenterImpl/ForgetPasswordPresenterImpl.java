package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.ForgetPasswordContract;

public class ForgetPasswordPresenterImpl extends ForgetPasswordContract.Presenter {
    private ForgetPasswordContract.IView mView;

    public ForgetPasswordPresenterImpl(ForgetPasswordContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void getVertifyCode(String phone) {

    }

    @Override
    public void checkVertifyCode(String code) {

    }

    @Override
    public boolean checkPhone(String phone) {
        return false;
    }

    @Override
    public boolean checkVertifyCodeNull(String code) {
        return false;
    }
}
