package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.GoodsContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.GoodsModule;

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

    private List<GoodsModule.Entity> list = new ArrayList<>();
    private int status;

    @Override
    public void start() {
        super.start();
        mView.initGoods(list);
        refreshData();
    }

    public GoodsPagerPresenterImpl(GoodsContract.IView mView, int code) {
        this.mView = mView;
        this.status = code;
        mView.setPresenter(this);
    }

    @Override
    public void refreshData() {
        GoodsModule.getGoods(status, new Callback<ResponseData<GoodsModule>>() {
            @Override
            public void onResponse(Call<ResponseData<GoodsModule>> call, Response<ResponseData<GoodsModule>> response) {
                if (response.body().isSuccess()) {
                    //操作成功
                    list.clear();
                    setData(response.body().getDatas());
                    mView.refresh();
                } else {
                    mView.showSnackerToast("获取商品失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseData<GoodsModule>> call, Throwable t) {
                mView.showSnackerToast("获取商品失败");
            }
        });
    }

    private void setData(GoodsModule goodsModule) {
        switch (status) {
            case STATUS_MOBILE:
                list.addAll(goodsModule.getMobile());
                break;
            case STATUS_UNICOM:
                list.addAll(goodsModule.getUnicom());
                break;
            case STATUS_TELCOM:
                list.addAll(goodsModule.getTelcom());
                break;
            case STATUS_JD:
                list.addAll(goodsModule.getJd());
                break;
        }
    }
}
