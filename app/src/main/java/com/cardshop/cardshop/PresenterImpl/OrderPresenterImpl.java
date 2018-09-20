package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.OrderContract;

import java.util.ArrayList;
import java.util.List;

public class OrderPresenterImpl extends OrderContract.IPresenter<OrderContract.IView> {
    private OrderContract.IView mView;
    private List<String> listTitle = new ArrayList<>();

    public OrderPresenterImpl(OrderContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        listTitle.clear();
        listTitle.add("订单");
        listTitle.add("已购");
        listTitle.add("提货");
        listTitle.add("转卖");
        mView.initPager(listTitle);
    }
}
