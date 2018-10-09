package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.ChangeUserNameContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.UserModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeUserNamePresenterImpl extends ChangeUserNameContract.IPresenter {
    private ChangeUserNameContract.IView mView;

    public ChangeUserNamePresenterImpl(ChangeUserNameContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void change(String userName) {
        if (TextUtils.isEmpty(userName)) {
            mView.showSnackerToast("用户名不能为空");
            return;
        }
        changeName(userName);

    }

    private void changeName(final String name) {
        mView.showLoading("修改中", "修改中,请稍等");
        UserModule.changeUserName(name, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                mView.hideLoading();
                if (response.body().isSuccess()) {
                    mView.onResult(name);
                } else {
                    mView.showSnackerToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {
                mView.showSnackerToast(t.getMessage());
            }
        });
    }
}
