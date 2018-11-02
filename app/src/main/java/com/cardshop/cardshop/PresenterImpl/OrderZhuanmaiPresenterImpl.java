package com.cardshop.cardshop.PresenterImpl;

import android.support.v4.app.Fragment;

import com.cardshop.cardshop.Contract.OrderZhuanmaiContract;
import com.cardshop.cardshop.View.Fragment.OrderZhuanmaiPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderZhuanmaiPresenterImpl extends OrderZhuanmaiContract.IPresenter<OrderZhuanmaiContract.IView> {
    private OrderZhuanmaiContract.IView mView;
    private List<Fragment> list = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public OrderZhuanmaiPresenterImpl(OrderZhuanmaiContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        mView.initVp(list, titles);
        initTitle();
        initFragment();
        mView.refreshVp();
    }

    private void initTitle() {
        titles.clear();
        titles.add("转卖中");
        titles.add("已转卖");
    }

    private void initFragment() {
        list.clear();
        for (int i = 0; i < titles.size(); i++) {
            OrderZhuanmaiPagerFragment orderTihuoPagerFragment = OrderZhuanmaiPagerFragment.newInstance();
            new OrderZhuanmaiPagerPresenterImpl(orderTihuoPagerFragment, i == 0 ? OrderZhuanmaiPagerPresenterImpl.TYPE_ZHUANMAIZHONG : OrderZhuanmaiPagerPresenterImpl.TYPE_YIZHUANMAI);
            list.add(orderTihuoPagerFragment);
        }
    }
}
