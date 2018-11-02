package com.cardshop.cardshop.PresenterImpl;

import android.support.v4.app.Fragment;

import com.cardshop.cardshop.Contract.OrderTihuoContract;
import com.cardshop.cardshop.View.Fragment.OrderTihuoPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderTihuoPresenterImpl extends OrderTihuoContract.IPresenter<OrderTihuoContract.IView> {
    private OrderTihuoContract.IView mView;
    private List<Fragment> list = new ArrayList<>();
    private List<String> title = new ArrayList<>();

    public OrderTihuoPresenterImpl(OrderTihuoContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        list.clear();
        title.clear();
        mView.initVp(list, title);
        initTitle();
        initFragment();
        mView.refreshVp();
    }

    private void initTitle() {
        title.add("充值卡");
        title.add("购物卡");
    }

    private void initFragment() {
        for (int i = 0; i < title.size(); i++) {
            OrderTihuoPagerFragment orderTihuoPagerFragment = OrderTihuoPagerFragment.newInstance();
            new OrderTihuoPagerPresenterImpl(orderTihuoPagerFragment,
                    i == 0 ? OrderTihuoPagerPresenterImpl.TYPE_RECHARGE : OrderTihuoPagerPresenterImpl.TYPE_SHOP);
            list.add(orderTihuoPagerFragment);
        }
    }
}
