package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.OrderTihuoPagerContact;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.OrderTihuoModule;
import com.cardshop.cardshop.Utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderTihuoPagerPresenterImpl extends OrderTihuoPagerContact.IPresenter {
    public static final String TYPE_RECHARGE = "recharge";
    public static final String TYPE_SHOP = "shop";
    private OrderTihuoPagerContact.IView mView;
    private String type;
    private int pageNum = 1, pageSize = 20;
    private boolean isRefresh = false;
    private List<OrderTihuoModule> list = new ArrayList<>();

    public OrderTihuoPagerPresenterImpl(OrderTihuoPagerContact.IView mView, String type) {
        this.mView = mView;
        this.type = type;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.initRv(list, type.equals(TYPE_SHOP));
        switch (type) {
            case TYPE_RECHARGE:
                type = "充值卡";
                break;
            case TYPE_SHOP:
                type = "购物卡";
                break;
        }
//        refreshData();
//        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    private void getData() {
        OrderTihuoModule.getDataList(type, pageNum + "", pageSize + "",
                new Callback<ResponseData<List<OrderTihuoModule>>>() {
                    @Override
                    public void onResponse(Call<ResponseData<List<OrderTihuoModule>>> call, Response<ResponseData<List<OrderTihuoModule>>> response) {
                        if (response.body().isSuccess()) {
                            if (isRefresh) {
                                list.clear();
                                list.addAll(response.body().getData());
//                                initData();
                                mView.refreshRv();
                            } else {
                                int loadMorePosition = list.size();
                                list.addAll(response.body().getData());
                                mView.onLoadMore(loadMorePosition, response.body().getData().size());
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
                    public void onFailure(Call<ResponseData<List<OrderTihuoModule>>> call, Throwable t) {
                        mView.showError();
                        isRefresh = false;
                        mView.hideProgressbar();
                    }
                });
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            OrderTihuoModule orderTihuoModule = new OrderTihuoModule();
            orderTihuoModule.setAddTime("121212121");
            orderTihuoModule.setCardStatus("20");
            orderTihuoModule.setGoodsName("移动充值卡");
            orderTihuoModule.setGoodsPrice("123");
            orderTihuoModule.setGoodsNum("1");
            orderTihuoModule.setPhone("123445");
            orderTihuoModule.setShoppingCard(false);
            list.add(orderTihuoModule);
        }
    }

    @Override
    public void loadMore() {
        pageNum++;
        getData();
    }

    @Override
    public void refreshData() {
        isRefresh = true;
        pageNum = 1;
        getData();
    }
}
