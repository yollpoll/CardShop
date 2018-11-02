package com.cardshop.cardshop.PresenterImpl;

import android.support.v4.app.Fragment;

import com.cardshop.cardshop.Contract.GoodsContract;
import com.cardshop.cardshop.Contract.GoodsDetailContract;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.View.Fragment.GoodsDetailPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailPresenterImpl extends GoodsDetailContract.IPresenter<GoodsContract.IView> {
    private GoodsDetailContract.IView mView;
    private List<String> listTitles = new ArrayList<>();
    private List<Fragment> list = new ArrayList<>();
    private GoodsModule goodsModule;


    public GoodsDetailPresenterImpl(GoodsDetailContract.IView mView, GoodsModule goodsModule) {
        this.mView = mView;
        this.goodsModule = goodsModule;
        mView.setPresenter(this);
    }

    private void initFragment() {
        list.clear();
        for (int i = 0; i < listTitles.size(); i++) {
            GoodsDetailPagerFragment detailPagerFragment = GoodsDetailPagerFragment.newInstance();
            new GoodsDetailPagerPresenterImpl(detailPagerFragment, i, getCardType(goodsModule.getGoodsName()), goodsModule);
            list.add(detailPagerFragment);
        }
    }

    private void initTitles() {
        listTitles.clear();
        listTitles.add("商品介绍");
        listTitles.add("规格参数");
        listTitles.add("售后保障");
    }

    private int getCardType(String goodsName) {
        if (goodsName.contains("移动")) {
            return 0;
        } else if (goodsName.contains("联通")) {
            return 1;
        } else if (goodsName.contains("电信")) {
            return 2;
        } else if (goodsName.contains("京东")) {
            return 3;
        }
        return 0;
    }

    @Override
    public void start() {
        super.start();
        initTitles();
        initFragment();
        mView.initPager(listTitles, list);
        mView.initGoods(goodsModule);
    }

    @Override
    public void createOrder() {
        mView.onCreateOrder(goodsModule);
    }

}
