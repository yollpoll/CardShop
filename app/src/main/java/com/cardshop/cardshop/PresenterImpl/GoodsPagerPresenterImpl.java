package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.GoodsContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.View.Activity.CreateOrderActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoodsPagerPresenterImpl extends GoodsContract.IPresenter<GoodsContract.IView> {
    public static final int STATUS_MOBILE = 0;
    public static final int STATUS_UNICOM = 1;
    public static final int STATUS_TELCOM = 2;
    public static final int STATUS_JD = 3;

    private GoodsContract.IView mView;
    private int pageNum = 1;

    private List<GoodsModule> list = new ArrayList<>();
    private String gcName;
    private boolean isRefresh = true;

    @Override
    public void start() {
        super.start();
        mView.initGoods(list);
        mView.showProgressbar();
        refreshData();
    }

    public GoodsPagerPresenterImpl(GoodsContract.IView mView, String gcName) {
        this.mView = mView;
        this.gcName = gcName;

        mView.setPresenter(this);
    }

    @Override
    public void refreshData() {
        isRefresh = true;
        pageNum = 1;
        getData();
    }

    @Override
    public void loadMore() {
        pageNum++;
        getData();
    }

    @Override
    public void onClick(int position) {
        mView.onClick(list.get(position));
    }

    @Override
    public void onBuyClick(int position) {
        CreateOrderActivity.gotoCreateOrderActivity(mContext, list.get(position));
    }

    private void getData() {
        GoodsModule.getGoods(gcName, pageNum + "", new Callback<ResponseData<List<GoodsModule>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<GoodsModule>>> call, Response<ResponseData<List<GoodsModule>>> response) {
                mView.hideProgressbar();
                mView.hideError();
                int loadMorePosition = list.size();
                if (null != response.body()) {
                    if (response.body().isSuccess()) {
                        if (isRefresh)
                            list.clear();
                        list.addAll(response.body().getData());
                        if (isRefresh) {
                            mView.refresh();
                        } else {
                            mView.loadMore(loadMorePosition, response.body().getData().size());
                        }
                        if (0 == list.size()) {
                            mView.showNoData();
                        } else {
                            mView.hideNoData();
                        }
                        isRefresh = false;
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseData<List<GoodsModule>>> call, Throwable t) {
                isRefresh = false;
                mView.showError();
            }
        });
    }

//    private void setData(GoodsModule goodsModule) {
//        switch (status) {
//            case STATUS_MOBILE:
//                list.addAll(goodsModule.getMobile());
//                break;
//            case STATUS_UNICOM:
//                list.addAll(goodsModule.getUnicom());
//                break;
//            case STATUS_TELCOM:
//                list.addAll(goodsModule.getTelcom());
//                break;
//            case STATUS_JD:
//                list.addAll(goodsModule.getJd());
//                break;
//        }
//    }
}
