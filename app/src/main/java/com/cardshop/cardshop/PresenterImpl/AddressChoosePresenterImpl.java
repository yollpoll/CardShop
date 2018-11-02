package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Contract.AddressChooseContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.AddressModule;
import com.cardshop.cardshop.View.Fragment.AddressChooseFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressChoosePresenterImpl extends AddressChooseContract.Presenter<AddressChooseContract.IView> {
    private AddressChooseContract.IView mView;
    private List<AddressModule> list = new ArrayList<>();
    private int actionMode;

    public AddressChoosePresenterImpl(AddressChooseContract.IView mView, int actionMode) {
        this.mView = mView;
        this.actionMode = actionMode;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        refreshData();
        mView.initActionMode(actionMode);
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
                    list.clear();
                    list.addAll(response.body().getData());
                    mView.refreshRecyclerView();
                    if (list.size() == 0) {
                        mView.showNoData();
                    } else {
                        mView.hideNoData();
                    }
                    mView.hideError();
                }
            }

            @Override
            public void onFailure(Call<ResponseData<List<AddressModule>>> call, Throwable t) {
                mView.showSnackerToast("加载出错");
                mView.showError();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        switch (actionMode) {
            case AddressChooseFragment.MODE_CHOOSE:
                //choose
                mView.onChooseAddress(list.get(position));
                break;
            default:
                //edit
                mView.gotoEditAddress(list.get(position));
                break;
        }
    }

    @Override
    public void delAddress(int position) {
        mView.showLoading("删除中", "正在删除,请稍等");
        AddressModule.delAddress(list.get(position).getAddressId(), new Callback<ResponseData<BaseModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BaseModule>> call, Response<ResponseData<BaseModule>> response) {
                mView.hideLoading();
                if (response.body().isSuccess()) {
                    refreshData();
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
}
