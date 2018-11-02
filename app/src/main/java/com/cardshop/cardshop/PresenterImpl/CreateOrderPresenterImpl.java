package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.AliPay.AliPayActivity;
import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.CreateOrderContact;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.Module.OrderModule;
import com.cardshop.cardshop.Module.UserModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateOrderPresenterImpl extends CreateOrderContact.Presenter {
    private CreateOrderContact.IView mView;
    private GoodsModule goodsModule;
    private int count = 1;
    private OrderModule orderModule;

    public CreateOrderPresenterImpl(CreateOrderContact.IView mView, GoodsModule goodsModule) {
        this.mView = mView;
        this.goodsModule = goodsModule;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.initGoods(goodsModule);
        mView.initBalance(UserModule.getCurrentUser().getMember().getAvailableRcBalance() + "");
    }

    @Override
    public void addCount() {
        if (count + 1 > goodsModule.getGoodsNum()) {
            mView.showSnackerToast("最多购买" + goodsModule.getGoodsNum() + "件");
        } else {
            count++;
            mView.onSetCount(count, count * goodsModule.getAllPrice());
        }

    }

    @Override
    public void reduceCount() {
        if (count <= 1) {
            mView.showSnackerToast("至少购买一件");
        } else {
            count--;
            mView.onSetCount(count, count * goodsModule.getAllPrice());
        }
    }

    @Override
    public void createOrder() {
        mView.showLoading("订单生成中", "请稍等");
        OrderModule.createOrder(goodsModule.getId() + "", count, count * goodsModule.getAllPrice() + "", new Callback<ResponseData<OrderModule>>() {
            @Override
            public void onResponse(Call<ResponseData<OrderModule>> call, Response<ResponseData<OrderModule>> response) {
                if (response.body().isSuccess()) {
                    orderModule = response.body().getData();
                    mView.onCreateOrder();
                } else {
                    mView.showSnackerToast(response.body().getMsg());
                }
                mView.hideLoading();
            }

            @Override
            public void onFailure(Call<ResponseData<OrderModule>> call, Throwable t) {
                mView.hideLoading();
            }
        });
    }

    @Override
    public void payByBalance(String psw) {
        mView.showLoading("支付中", "请稍等");
        OrderModule.payByBalance(orderModule.getOrderSn(), psw, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                mView.hideLoading();
                if (response.body().isSuccess()) {
                    mView.goBack();
                    mView.showToast("支付成功");
                } else {
                    mView.showSnackerToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BaseModule>> call, Throwable t) {
                mView.hideLoading();
            }
        });
    }

    @Override
    public void payAli() {
        if (null == orderModule) {
            mView.showSnackerToast("创建订单失败");
            return;
        }
        OrderModule.getAliInfo("2", "购买" + goodsModule.getGoodsName() + count + "张", "购买" + goodsModule.getGoodsName() + count + "张", orderModule,
                new Callback<ResponseData<String>>() {
                    @Override
                    public void onResponse(Call<ResponseData<String>> call, Response<ResponseData<String>> response) {
                        if (response.isSuccessful() && response.body().isSuccess()) {
                            AliPayActivity.gotoPay(mView.getmContext(), response.body().getData(), new AliPayActivity.AliPayCallbackInterface() {
                                @Override
                                public void paySuccess() {
                                    mView.goBack();
                                }

                                @Override
                                public void payFail() {
                                    mView.showSnackerToast("支付失败");
                                }

                                @Override
                                public void payWaitting() {

                                }
                            });
                        } else {
                            mView.showSnackerToast("获取订单失败");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseData<String>> call, Throwable t) {
                    }
                });
    }
}
