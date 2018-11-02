package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.BalanceContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.UserModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalancePresenterImpl extends BalanceContract.IPresenter<BalanceContract.IView> {
    private BalanceContract.IView mView;

    public BalancePresenterImpl(BalanceContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        UserModule.getUserInfo(new Callback<ResponseData<UserModule>>() {
            @Override
            public void onResponse(Call<ResponseData<UserModule>> call, Response<ResponseData<UserModule>> response) {
                if (response.body().isSuccess()) {
                    UserModule.saveToLocal(response.body().getData());
                    mView.setBalance(response.body().getData().getMember().getAvailableRcBalance()+"");
                }
            }

            @Override
            public void onFailure(Call<ResponseData<UserModule>> call, Throwable t) {

            }
        });
    }

    @Override
    public void start() {
        super.start();
        mView.setBalance(UserModule.getCurrentUser().getMember().getAvailableRcBalance() + "");
    }
}
