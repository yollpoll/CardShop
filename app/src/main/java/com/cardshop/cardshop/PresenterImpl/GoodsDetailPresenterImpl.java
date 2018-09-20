package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.GoodsContract;
import com.cardshop.cardshop.Contract.GoodsDetailContract;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailPresenterImpl extends GoodsDetailContract.IPresenter<GoodsContract.IView> {
    private GoodsDetailContract.IView mView;
    private List<String> listTitles = new ArrayList<>();


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
        listTitles.clear();
        listTitles.add("商品介绍");
        listTitles.add("规格参数");
        listTitles.add("售后保障");
        mView.initPager(listTitles);
    }
}
