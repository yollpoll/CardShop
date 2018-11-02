package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.OrderYigouPagerContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.OrderYigouModule;
import com.cardshop.cardshop.Utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderYigouPagerPresenterImpl extends OrderYigouPagerContract.IPresenter {
    private OrderYigouPagerContract.IView mView;
    private String gcName;
    private List<OrderYigouModule> list = new ArrayList<>();
    private int pageNum = 1;
    private int pageSize = 20;
    private boolean isRefresh = false;


    public OrderYigouPagerPresenterImpl(OrderYigouPagerContract.IView mView, String gcName) {
        this.mView = mView;
        this.gcName = gcName;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.initRv(list);
//        refresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void load() {
        pageNum++;
        getData();
    }

    @Override
    public void refresh() {
        pageNum = 1;
        isRefresh = true;
        getData();
    }

    @Override
    public void applyGetGoods(int position) {

        mView.onApplyGetGoods(list.get(position));
    }

    private void getData() {
        OrderYigouModule.getOrderYigou("全部".equals(gcName) ? "" : gcName, pageNum + "", pageSize + "", new Callback<ResponseData<List<OrderYigouModule>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<OrderYigouModule>>> call, Response<ResponseData<List<OrderYigouModule>>> response) {
                if (response.body().isSuccess()) {
                    if (isRefresh) {
                        list.clear();
                        list.addAll(response.body().getData());
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
                isRefresh = false;
            }

            @Override
            public void onFailure(Call<ResponseData<List<OrderYigouModule>>> call, Throwable t) {
                mView.showError();
                isRefresh = false;
            }
        });
    }
}
