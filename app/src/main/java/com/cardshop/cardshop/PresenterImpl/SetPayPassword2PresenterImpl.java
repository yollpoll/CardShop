package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Contract.SetPayPassword2Contract;

public class SetPayPassword2PresenterImpl extends SetPayPassword2Contract.IPresenter {
    private SetPayPassword2Contract.IView mView;
    private String password = "";

    public SetPayPassword2PresenterImpl(SetPayPassword2Contract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void setPsw(String psw) {
        if (TextUtils.isEmpty(password)) {
            //第一次输入
            password = psw;
            mView.onFirstInput(true, "请再次输入");
        }
    }

    @Override
    public void confrimPsw(String psw) {
        if (psw.equals(password)) {
            //输入正确
            mView.showLoading("设置支付密码", "支付密码设置中");
            mView.onConfirmInput(true, "请再次输入");
            setPayPassword();
        } else {
            //输入不同
            mView.onConfirmInput(false, "请设置6位数字支付密码");
            mView.showSnackerToast("两次输入密码不同，请输入输入.");
            password = "";
        }
    }

    private void setPayPassword() {
        mView.hideLoading();
    }
}