package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.OrderZhuanmaiPagerContact;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.OrderZhuanmaiModule;
import com.cardshop.cardshop.Utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderZhuanmaiPagerPresenterImpl extends OrderZhuanmaiPagerContact.IPresenter {
    public static final String TYPE_ZHUANMAIZHONG = "20";
    public static final String TYPE_YIZHUANMAI = "30";
    private OrderZhuanmaiPagerContact.IView mView;
    private String type;
    private int pageNum = 1, pageSize = 20;
    private boolean isRefresh = false;
    private List<OrderZhuanmaiModule> list = new ArrayList<>();


    public OrderZhuanmaiPagerPresenterImpl(OrderZhuanmaiPagerContact.IView mView, String type) {
        this.mView = mView;
        this.type = type;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.initRv(list);
//        refreshData();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    private void getData() {
        OrderZhuanmaiModule.getZhuanmaiList(pageNum + "", pageSize + "", type + "",
                new Callback<ResponseData<List<OrderZhuanmaiModule>>>() {
                    @Override
                    public void onResponse(Call<ResponseData<List<OrderZhuanmaiModule>>> call, Response<ResponseData<List<OrderZhuanmaiModule>>> response) {
                        mView.hideProgressbar();
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
                        isRefresh = false;
                    }

                    @Override
                    public void onFailure(Call<ResponseData<List<OrderZhuanmaiModule>>> call, Throwable t) {
                        mView.showError();
                        isRefresh = false;
                        mView.hideProgressbar();
                    }
                });
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            OrderZhuanmaiModule orderTihuoModule = new OrderZhuanmaiModule();
            orderTihuoModule.setAddTime("121212121");
            orderTihuoModule.setCardStatus("20");
            orderTihuoModule.setGoodsName("移动充值卡");
            orderTihuoModule.setGoodsPrice("123");
            orderTihuoModule.setGoodsNum("1");
            orderTihuoModule.setAfterNum("1");
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
