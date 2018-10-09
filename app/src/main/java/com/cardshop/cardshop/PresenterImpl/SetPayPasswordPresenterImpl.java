package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.PayPasswordContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Listener.CountDownListener;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Utils.RxUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetPayPasswordPresenterImpl extends PayPasswordContract.IPresenter {
    private PayPasswordContract.IView mView;

    public SetPayPasswordPresenterImpl(PayPasswordContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void attach(Object view) {
        super.attach(view);
        RxUtils.isStopCountDown = false;
    }

    @Override
    public void detach() {
        super.detach();
        RxUtils.isStopCountDown = true;
    }

    @Override
    public void sendSms() {
        UserModule.getMsgCode(UserModule.getCurrentUser().getMember().getMemberMobile(), new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                mView.hideLoading();
                if (response.body().isSuccess()) {
                    startCountDown();
                } else {
                    mView.showSnackerToast("验证码发送错误");
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {
                mView.hideLoading();
                mView.showSnackerToast("网络错误");
            }
        });

    }

    @Override
    public void vertifySms(String code) {
        if (TextUtils.isEmpty(code)) {
            mView.showSnackerToast("请输入验证码");
            return;
        }
        mView.showLoading("验证中", "正在验证验证码");
        UserModule.vertifySms(UserModule.getCurrentUser().getMember().getMemberMobile(), code, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                if (!response.body().isSuccess()) {
                    mView.hideLoading();
                    mView.showSnackerToast(response.body().getMsg());
                } else {
                    mView.onVertifySuccess();
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {
                mView.hideLoading();
            }
        });
    }

    private void startCountDown() {
        RxUtils.startCountDown(60 * 1000, 1000, new CountDownListener() {
            @Override
            public void onCountDown(int count) {
                if (0 == count) {
                    mView.onCountDownFinish();
                } else {
                    mView.showCountDown(count + "秒后重发");
                }
            }
        });
    }
}
