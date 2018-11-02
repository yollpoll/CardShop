package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.AliPay.AliPayActivity;
import com.cardshop.cardshop.Contract.RechargeContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.OrderModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RechargePresenterImpl extends RechargeContract.IPresenter {
    private RechargeContract.IView mView;

    public RechargePresenterImpl(RechargeContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void recharge(String money) {
        if (Double.parseDouble(money) <= 0) {
            mView.showToast("充值金额不能为0");
            return;
        }
        OrderModule.getAliInfo("1", "充值", "充值" + money + "元", money, "", new Callback<ResponseData<String>>() {
            @Override
            public void onResponse(Call<ResponseData<String>> call, Response<ResponseData<String>> response) {
                if (response.isSuccessful() && response.body().isSuccess()) {

                    AliPayActivity.gotoPay(mView.getmContext(), response.body().getData(), new AliPayActivity.AliPayCallbackInterface() {
                        @Override
                        public void paySuccess() {
                            mView.showToast("支付成功");
                            mView.goBack();
                        }

                        @Override
                        public void payFail() {

                        }

                        @Override
                        public void payWaitting() {

                        }
                    });
                } else {
                    mView.showSnackerToast("充值失败");
                }

            }

            @Override
            public void onFailure(Call<ResponseData<String>> call, Throwable t) {

            }
        });
    }
}
