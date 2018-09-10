package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

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

    @Override
    public boolean checkPhone(String phone) {
        return !TextUtils.isEmpty(phone);
    }

    @Override
    public boolean checkPsd(String psd) {
        return !TextUtils.isEmpty(psd);
    }

    @Override
    public boolean checkConfirmPsd(String psd) {
        return !TextUtils.isEmpty(psd);
    }

    @Override
    public boolean checkVertifyCode(String vertifyCode) {
        return !TextUtils.isEmpty(vertifyCode);
    }

    @Override
    public boolean vertifyPsd(String psd, String confirmPsd) {
        return psd.equals(confirmPsd) ? true : false;
    }
}
