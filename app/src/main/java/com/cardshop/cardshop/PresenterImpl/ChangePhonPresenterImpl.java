package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.ChangePhonContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Listener.CountDownListener;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Utils.RxUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePhonPresenterImpl extends ChangePhonContract.IPresenter {
    private static final int COUNT_TIME = 60;
    private ChangePhonContract.IView mView;
    private String newPhone;

    public ChangePhonPresenterImpl(ChangePhonContract.IView mView, String newPhone) {
        this.mView = mView;
        this.newPhone = newPhone;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        sendSms();
        setNewPhone();
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


    private void setNewPhone() {
        String content = "请输入" + newPhone + "收到的验证码";
        mView.setNewPhone(content);
    }

    @Override
    public void stop() {
        super.stop();
        RxUtils.isStopCountDown = true;
    }

    @Override
    public void sendSms() {
        UserModule.getMsgCode(newPhone, new Callback<ResponseData<BaseModule>>() {
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
        UserModule.vertifySms(newPhone, code, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                if (!response.body().isSuccess()) {
                    mView.hideLoading();
                    mView.showSnackerToast(response.body().getMsg());
                } else {
                    changePhone();
//                    mView.onVertifySuccess();
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {
                mView.hideLoading();
            }
        });
    }

    private void changePhone() {
        UserModule.changePhone(newPhone, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                if (response.body().isSuccess()) {
                    mView.onVertifySuccess(newPhone);
                } else {
                    mView.showSnackerToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {

            }
        });
    }

    @Override
    public void startCountDown() {
        RxUtils.startCountDown(60 * 1000, 1000, new CountDownListener() {
            @Override
            public void onCountDown(int count) {
                if (0 == count) {
                    mView.onCountDownFinsh();
                } else {
                    mView.showCountDown(count + "秒后重发");
                }
            }
        });
    }
}
