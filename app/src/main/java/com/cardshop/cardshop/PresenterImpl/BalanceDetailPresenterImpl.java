package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.BalanceDetailContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.BalanceModule;
import com.cardshop.cardshop.Utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceDetailPresenterImpl extends BalanceDetailContract.IPresenter {
    private BalanceDetailContract.IView mView;
    private int pageNum = 1, pageSize = 20;
    private boolean isRefresh = false;
    private List<BalanceModule> list = new ArrayList<>();

    public BalanceDetailPresenterImpl(BalanceDetailContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.initRv(list);
        refresh();
    }

    @Override
    public void load() {
        pageNum++;
        getData();
    }

    @Override
    public void refresh() {
        isRefresh = true;
        pageNum = 1;
        getData();
    }

    private void getData() {
        BalanceModule.getBalanceDetailList(pageNum + "", pageSize + "", new Callback<ResponseData<List<BalanceModule>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<BalanceModule>>> call, Response<ResponseData<List<BalanceModule>>> response) {
                if (response.body().isSuccess()) {
                    if (isRefresh) {
                        list.clear();
                        list.addAll(response.body().getData());
//                                initData();
                        mView.onRefreshData();
                    } else {
                        int loadMorePosition = list.size();
                        list.addAll(response.body().getData());
                        mView.onLoad(loadMorePosition, response.body().getData().size());
                    }
                    if (list.size() == 0) {
                        LogUtil.Log("no data");
                        mView.showNoData();
                    } else {
                        mView.hideNoData();
                    }
                    mView.hideError();
                } else {
                    mView.showSnackerToast(response.body().getMsg());
                }
                mView.hideProgressbar();
                isRefresh = false;
            }

            @Override
            public void onFailure(Call<ResponseData<List<BalanceModule>>> call, Throwable t) {
                mView.showError();
                isRefresh = false;
                mView.hideProgressbar();
            }
        });
    }
}
