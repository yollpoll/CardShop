package com.cardshop.cardshop.PresenterImpl;

        import com.cardshop.cardshop.Contract.OrderDingdanContract;

public class OrderDingdanPresenterImpl extends OrderDingdanContract.IPresenter<OrderDingdanContract.IView> {
    private OrderDingdanContract.IView mView;

    public OrderDingdanPresenterImpl(OrderDingdanContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }
}
