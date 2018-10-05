package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.ManagerCardContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.CardModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerCardPresenterImpl extends ManagerCardContract.IPresenter {

    private ManagerCardContract.IView mView;
    private CardModule cardModule;

    public ManagerCardPresenterImpl(ManagerCardContract.IView mView, CardModule cardModule) {
        this.mView = mView;
        this.cardModule = cardModule;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.setCard(cardModule);
    }

    @Override
    public void delCard() {
        mView.showLoading("解绑银行卡", "正在解绑...");
        CardModule.delCard(cardModule.getPdcId() + "", new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                mView.hideLoading();
                if (response.body().isSuccess()) {
                    mView.showToast(response.body().getMsg());
                    mView.goBack();
                } else {
                    mView.showSnackerToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {
                mView.hideLoading();
                mView.showSnackerToast(t.getMessage());
            }
        });
    }
}
