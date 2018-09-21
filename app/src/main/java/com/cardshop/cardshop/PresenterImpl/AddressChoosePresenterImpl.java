package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.AddressChooseContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.AddressModule;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressChoosePresenterImpl extends AddressChooseContract.Presenter<AddressChooseContract.IView> {
    private AddressChooseContract.IView mView;
    private List<AddressModule> list = new ArrayList<>();

    public AddressChoosePresenterImpl(AddressChooseContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        refreshData();
        mView.initRecyclerView(list);
    }

    @Override
    public void refreshData() {
        mView.showProgressbar();
        AddressModule.getAddressList(new Callback<ResponseData<List<AddressModule>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<AddressModule>>> call, Response<ResponseData<List<AddressModule>>> response) {
                mView.hideProgressbar();
                if (response.body().isSuccess()) {
                    mView.hideProgressbar();
                    mView.refreshRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<ResponseData<List<AddressModule>>> call, Throwable t) {
                mView.showSnackerToast("加载出错");
            }
        });
    }
}
