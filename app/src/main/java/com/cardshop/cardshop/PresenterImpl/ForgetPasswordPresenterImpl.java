package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.ForgetPasswordContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.UserModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordPresenterImpl extends ForgetPasswordContract.Presenter {
    private ForgetPasswordContract.IView mView;
    private String title;

    public ForgetPasswordPresenterImpl(ForgetPasswordContract.IView mView, String title) {
        this.mView = mView;
        this.title = title;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.showBack();
        mView.setTitle(title);
    }

    @Override
    public void getVertifyCode(String phone) {
        UserModule.getMsgCode(phone, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                mView.hideLoading();
                mView.showSnackerToast(response.body().getMsg());
//                if (response.body().isSuccess()) {
//                    mView.showSnackerToast("发送成功");
//                } else {
//                    mView.showSnackerToast("发送失败");
//                }
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {
                mView.hideLoading();
                mView.showSnackerToast("发送失败");
            }
        });
    }


    @Override
    public void checkInput(String phone, String vertifyCode, String psw, String confirmPsw) {
        if (TextUtils.isEmpty(phone)) {
            mView.showSnackerToast("请输入手机号");
        } else if (TextUtils.isEmpty(vertifyCode)) {
            mView.showSnackerToast("请输入验证码");
        } else if (TextUtils.isEmpty(psw)) {
            mView.showSnackerToast("请输入新密码");
        } else if (TextUtils.isEmpty(confirmPsw)) {
            mView.showSnackerToast("请输入确认密码");
        } else if (!psw.equals(confirmPsw)) {
            mView.showSnackerToast("两次输入密码不同");
        } else {
            forgetPassword(phone, vertifyCode, psw);
        }

    }

    @Override
    public void forgetPassword(String phone, String vertifyCode, String psw) {
        mView.showLoading("修改密码", "修改密码中，请稍等");
        UserModule.changePassword(phone, vertifyCode, psw, new Callback<ResponseData<UserModule>>() {
            @Override
            public void onResponse(Call<ResponseData<UserModule>> call, Response<ResponseData<UserModule>> response) {
                mView.hideLoading();
                if (null != response.body()) {
                    mView.showToast(response.body().getMsg());
                    if (response.body().isSuccess()) {
                        mView.goBack();
                    }
                } else {
                    mView.showSnackerToast("修改失败");
                }
//                if (null != response.body().getData()) {
//                    if ("0".equals(response.body().getCode())) {
//                        mView.showSnackerToast("验证码超时");
//                    } else if ("1".equals(response.body().getCode())) {
//                        mView.showSnackerToast("验证码不正确");
//                    } else if ("2".equals(response.body().getCode())) {
//                        mView.showSnackerToast("密码修改成功");
//                        mView.goBack();
//                    }
//                } else {
//                    mView.showSnackerToast("修改失败");
//                }

            }

            @Override
            public void onFailure(Call<ResponseData<UserModule>> call, Throwable t) {
                mView.hideLoading();
                mView.showSnackerToast("修改失败");
            }
        });
    }
}
