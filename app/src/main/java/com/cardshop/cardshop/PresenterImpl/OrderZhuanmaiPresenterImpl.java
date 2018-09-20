package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.OrderZhuanmaiContract;

public class OrderZhuanmaiPresenterImpl extends OrderZhuanmaiContract.IPresenter<OrderZhuanmaiContract.IView> {
    private OrderZhuanmaiContract.IView mView;

    public OrderZhuanmaiPresenterImpl(OrderZhuanmaiContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }
}
