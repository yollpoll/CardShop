package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.AddCardVertifyContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Listener.CountDownListener;
import com.cardshop.cardshop.Module.AddCardModule;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Utils.RxUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCardVertifyPresenterImpl extends AddCardVertifyContract.Presenter {
    private AddCardVertifyContract.IView mView;
    private AddCardModule addCardModule;

    public AddCardVertifyPresenterImpl(AddCardVertifyContract.IView mView, AddCardModule addCardModule) {
        this.mView = mView;
        mView.setPresenter(this);
        this.addCardModule = addCardModule;
    }

    @Override
    public void start() {
        super.start();
        sendSms();
        mView.showPhoneNum("请输入" + addCardModule.getPhone() + "收到的验证码");
    }

    private void sendSms() {
        mView.showLoading("发送验证码", "正在发送验证码");
        UserModule.getMsgCode(addCardModule.getPhone(), new Callback<ResponseData<BaseModule>>() {
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

    private void startCountDown() {
        RxUtils.startCountDown(60 * 1000, 1000, new CountDownListener() {
            @Override
            public void onCountDown(int count) {
                mView.showCountDown(count + "秒后重发");
            }
        });
    }

    @Override
    public void detach() {
        super.detach();
        RxUtils.isStopCountDown = true;
    }

    @Override
    public void vertifySms(String code) {
        mView.showLoading("验证中", "正在验证验证码");
        UserModule.vertifySms(addCardModule.getPhone(), code, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                mView.hideLoading();
                mView.onAddResulte(response.body().isSuccess(), response.body().getMsg());
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {
                mView.hideLoading();
            }
        });
    }
}
