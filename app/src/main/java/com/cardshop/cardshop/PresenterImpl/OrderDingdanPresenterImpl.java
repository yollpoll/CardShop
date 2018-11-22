package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.OrderDingdanContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.OrderDingdanModule;
import com.cardshop.cardshop.Module.UserModule;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDingdanPresenterImpl extends OrderDingdanContract.IPresenter<OrderDingdanContract.IView> {
    private OrderDingdanContract.IView mView;
    private String pageSize = "20";
    private int pageNum = 1;
    private boolean isRefrsh = false;
    private boolean isLoadMore = false;
    private List<OrderDingdanModule> list = new ArrayList<>();

    public OrderDingdanPresenterImpl(OrderDingdanContract.IView mView) {
        this.mView = mView;
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

    private void getData() {
        mView.showProgressbar();
        OrderDingdanModule.getOrderDingDan(UserModule.getCurrentUser().getMember().getMemberId(),
                pageNum + "", pageSize, new Callback<ResponseData<List<OrderDingdanModule>>>() {
                    @Override
                    public void onResponse(Call<ResponseData<List<OrderDingdanModule>>> call
                            , Response<ResponseData<List<OrderDingdanModule>>> response) {
                        mView.hideProgressbar();
                        if (isRefrsh) {
                            list.clear();
                            list.addAll(response.body().getData());
                            mView.refresh();
                        } else {
                            int loadMorePosition = list.size();
                            list.addAll(response.body().getData());
                            mView.loadMore(loadMorePosition, response.body().getData().size());
                        }
                        if (0 == list.size()&&isRefrsh) {
                            mView.showNoData();
                        } else {
                            mView.hideNoData();
                        }
                        mView.hideError();
                        isRefrsh = false;
                    }

                    @Override
                    public void onFailure(Call<ResponseData<List<OrderDingdanModule>>> call, Throwable t) {
                        mView.hideProgressbar();
                        isRefrsh = false;
                        mView.showError();
                        mView.showSnackerToast(t.getMessage());
                    }
                });
    }

    @Override
    public void loadMore() {
        isLoadMore = true;
        pageNum++;
        getData();
    }

    @Override
    public void refresh() {
        pageNum = 1;
        isRefrsh = true;
        getData();
    }
}
