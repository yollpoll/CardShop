package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.RegisterContract;
import com.cardshop.framework.Utils.ToastUtils;

public class RegisterPresenterImpl extends RegisterContract.IPresenter<RegisterContract.IView> {
    private RegisterContract.IView mView;

    public RegisterPresenterImpl(RegisterContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void getVertifyCode(String phone) {
        ToastUtils.showShort("获取验证码");
    }

    @Override
    public void register(String phone, String password, String confirmPassword, String vertifyCode) {
        ToastUtils.showShort("注册" + password + " " + confirmPassword + " " + vertifyCode);
    }
}
