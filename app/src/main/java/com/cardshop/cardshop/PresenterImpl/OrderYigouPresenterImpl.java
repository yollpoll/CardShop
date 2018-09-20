package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.OrderYigouContract;

public class OrderYigouPresenterImpl extends OrderYigouContract.IPresenter<OrderYigouContract.IView> {
    private OrderYigouContract.IView mView;

    public OrderYigouPresenterImpl(OrderYigouContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }
}
