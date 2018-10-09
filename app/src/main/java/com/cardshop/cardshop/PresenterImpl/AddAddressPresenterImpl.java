package com.cardshop.cardshop.PresenterImpl;

import android.text.TextUtils;

import com.cardshop.cardshop.Contract.AddAddressContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.AddressModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressPresenterImpl extends AddAddressContract.Presenter {
    private AddAddressContract.IView mView;
    private AddressModule addressModule;

    public AddAddressPresenterImpl(AddAddressContract.IView mView, AddressModule addressModule) {
        this.mView = mView;
        this.addressModule = addressModule;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        if (null != addressModule)
            mView.init(addressModule);
    }

    @Override
    public void checkInput(String name, String phone, String address, String area, boolean isDefault) {
        if (TextUtils.isEmpty(name)) {
            mView.showToast("收货人不能为空");
            return;
        } else if (TextUtils.isEmpty(phone)) {
            mView.showToast("手机号不能为空");
            return;
        } else if (TextUtils.isEmpty(address)) {
            mView.showToast("详细地址不能为空");
            return;
        } else if (TextUtils.isEmpty(area)) {
            mView.showToast("所在区域不能为空");
            return;
        }
        if (null == addressModule) {
            addAddress(name, phone, address, area, isDefault);
        } else {
            changeAddress(addressModule.getAddressId(), name, phone, address, area, isDefault);
        }
    }

    private void addAddress(String name, String phone, String address, String area, boolean isDefault) {
        AddressModule.addAddress(name, phone, area, address, isDefault, new Callback<ResponseData<AddressModule>>() {
            @Override
            public void onResponse(Call<ResponseData<AddressModule>> call, Response<ResponseData<AddressModule>> response) {
                if (response.body().isSuccess()) {
                    mView.showToast(response.body().getMsg());
                    mView.goBack();
                } else {
                    mView.showSnackerToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseData<AddressModule>> call, Throwable t) {
            }
        });
    }

    private void changeAddress(String addressId, String name, String phone, String address, String area, boolean isDefault) {
        AddressModule.changeAddress(addressId, name, phone, area, address, isDefault, new Callback<ResponseData<AddressModule>>() {
            @Override
            public void onResponse(Call<ResponseData<AddressModule>> call, Response<ResponseData<AddressModule>> response) {
                if (response.body().isSuccess()) {
                    mView.showToast(response.body().getMsg());
                    mView.goBack();
                } else {
                    mView.showSnackerToast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseData<AddressModule>> call, Throwable t) {
            }
        });
    }
}
