package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.GoodsContract;
import com.cardshop.cardshop.Contract.GoodsDetailContract;

public class GoodsDetailPresenterImpl extends GoodsDetailContract.IPresenter<GoodsContract.IView> {
    private GoodsDetailContract.IView mView;

    public GoodsDetailPresenterImpl(GoodsDetailContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.setTitle("商品详情");
        mView.showBack();
        mView.setNoStatusBar();
    }
}
