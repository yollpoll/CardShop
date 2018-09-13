package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Base.BaseApplication;
import com.cardshop.cardshop.Contract.RegisterContract;
import com.cardshop.cardshop.Module.FGMsgVertifyModule;
import com.cardshop.cardshop.Utils.VertifyUtils;
import com.cardshop.framework.Utils.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenterImpl extends RegisterContract.IPresenter<RegisterContract.IView> {
    private RegisterContract.IView mView;

    public RegisterPresenterImpl(RegisterContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.showBack();
    }

    @Override
    public void getVertifyCode(String phone) {
        FGMsgVertifyModule.getModule(BaseApplication.FG_ACCOUNT, BaseApplication.FG_PSD, VertifyUtils.createVertifyCode(),
                phone, BaseApplication.FG_TemplateId, BaseApplication.FG_SignId, new Callback<FGMsgVertifyModule>() {
                    @Override
                    public void onResponse(Call<FGMsgVertifyModule> call, Response<FGMsgVertifyModule> response) {
                        if (FGMsgVertifyModule.ifSuccess(response.body())) {
                            mView.showSendVertifyCode("发送成功");
                        } else {
                            mView.showSendVertifyCode(response.body().getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<FGMsgVertifyModule> call, Throwable t) {

                    }
                });
    }

    @Override
    public void register(String phone, String password, String confirmPassword, String vertifyCode) {
        if (checkVertifyCode(vertifyCode)) {
            ToastUtils.showShort("注册" + password + " " + confirmPassword + " " + vertifyCode);

        } else {
            mView.showToast("验证码错误");
        }
    }

    @Override
    public boolean checkPhone(String phone) {
        return !TextUtils.isEmpty(phone);
    }

    @Override
    public void checkRegisterInput(String phone, String password, String confirmPassword, String vertifyCode) {
        if (TextUtils.isEmpty(phone)) {
            mView.showToast("请输入手机号");
        } else if (TextUtils.isEmpty(password)) {
            mView.showToast("请输入密码");
        } else if (TextUtils.isEmpty(confirmPassword)) {
            mView.showToast("请输入确认密码");
        } else if (TextUtils.isEmpty(vertifyCode)) {
            mView.showToast("请输入验证码");
        } else if (!vertifyPsd(password, confirmPassword)) {
            mView.showToast("两次输入的密码不同");
        } else {
            this.register(phone, password, confirmPassword, vertifyCode);
        }
    }

    public boolean checkVertifyCode(String vertifyCode) {
        return vertifyCode.equals(VertifyUtils.vertifyCode);
    }

    public boolean vertifyPsd(String psd, String confirmPsd) {
        return psd.equals(confirmPsd) ? true : false;
    }
}
