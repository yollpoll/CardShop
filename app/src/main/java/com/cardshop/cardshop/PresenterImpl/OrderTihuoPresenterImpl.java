package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.OrderTihuoContract;

public class OrderTihuoPresenterImpl extends OrderTihuoContract.IPresenter<OrderTihuoContract.IView> {
    private OrderTihuoContract.IView mView;

    public OrderTihuoPresenterImpl(OrderTihuoContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }
}
