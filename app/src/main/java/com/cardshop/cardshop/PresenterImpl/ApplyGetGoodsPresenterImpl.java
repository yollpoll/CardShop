package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.ApplyGetGoodsContact;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.AddressModule;
import com.cardshop.cardshop.Module.OrderTihuoModule;
import com.cardshop.cardshop.Module.OrderYigouModule;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplyGetGoodsPresenterImpl extends ApplyGetGoodsContact.Presenter {
    private ApplyGetGoodsContact.IView mView;
    private OrderYigouModule orderYigouModule;
    private AddressModule addressModule;
    private int count = 1;
    private boolean isRecharge = false;

    public ApplyGetGoodsPresenterImpl(ApplyGetGoodsContact.IView mView, OrderYigouModule orderYigouModule) {
        this.mView = mView;
        this.orderYigouModule = orderYigouModule;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        addressModule = new AddressModule();
        addressModule.setTrueName("");
        addressModule.setAreaInfo("");

        mView.initModule(orderYigouModule);
        if (orderYigouModule.getGoodsName().contains("充值")) {
            mView.initCardType(true);
            isRecharge = true;
        } else {
            mView.initCardType(false);
            isRecharge = false;
        }
    }

    @Override
    public void addCount() {
        if (count + 1 > Integer.parseInt(orderYigouModule.getGoodsNum())) {
            mView.showSnackerToast("最多" + orderYigouModule.getGoodsNum() + "件");
        } else {
            count++;
            mView.onSetCount(count + "", count * Double.parseDouble(orderYigouModule.getAllPrice()) + "");
        }
    }

    @Override
    public void reduceCount() {
        if (count - 1 <= 0) {
            mView.showSnackerToast("至少提货1件");
        } else {
            count--;
            mView.onSetCount(count + "", count * Double.parseDouble(orderYigouModule.getAllPrice()) + "");
        }
    }

    @Override
    public void chooseAddress(AddressModule addressModule) {
        this.addressModule = addressModule;
    }

    @Override
    public void applyGetGoods(String phone) {
        mView.showLoading("提货中", "正在提货请稍等");
        LogUtil.Log("spq" + UserModule.getAvatarName());
        OrderTihuoModule.getGoods(orderYigouModule, count + "", phone, addressModule.getTrueName(), addressModule.getAreaInfo()
                , new Callback<ResponseData<BaseModule>>() {
                    @Override
                    public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                        mView.hideLoading();
                        if (response.body().isSuccess()) {
                            mView.goBack();
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
    public void checkPayPassword(final String phone, String password) {
        UserModule.vertifyPayPassword(password, new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                if (response.body().isSuccess()) {
                    applyGetGoods(phone);
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
    public void checkInput(String phone) {
        if (phone.length()<11 && isRecharge) {
            mView.showSnackerToast("请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(addressModule.getAreaInfo()) && !isRecharge) {
            mView.showSnackerToast("请选择收货地址");
            return;
        }
        mView.showPassword();
    }
}
