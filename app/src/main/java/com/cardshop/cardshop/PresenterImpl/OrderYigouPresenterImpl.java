package com.cardshop.cardshop.PresenterImpl;

import android.support.v4.app.Fragment;

import com.cardshop.cardshop.Contract.OrderYigouContract;
import com.cardshop.cardshop.View.Fragment.OrderYigouPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderYigouPresenterImpl extends OrderYigouContract.IPresenter<OrderYigouContract.IView> {
    private OrderYigouContract.IView mView;
    private List<Fragment> list = new ArrayList<>();
    private List<String> listTitles = new ArrayList<>();

    public OrderYigouPresenterImpl(OrderYigouContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        list.clear();
        listTitles.clear();
        listTitles.add("全部");
        listTitles.add("移动卡");
        listTitles.add("联通卡");
        listTitles.add("电信卡");
        listTitles.add("京东卡");

        for (int i = 0; i < listTitles.size(); i++) {
            OrderYigouPagerFragment orderYigouPagerFragment = OrderYigouPagerFragment.newInstance();
            new OrderYigouPagerPresenterImpl(orderYigouPagerFragment, listTitles.get(i));
            list.add(orderYigouPagerFragment);
        }
        mView.initVp(list, listTitles);
    }
}
