package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.GoodsDetailPagerContract;

public class GoodsDetailPagerPresenterImpl extends GoodsDetailPagerContract.IPresenter<GoodsDetailPagerContract.IView> {
    private GoodsDetailPagerContract.IView mView;

    public GoodsDetailPagerPresenterImpl(GoodsDetailPagerContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }
}
