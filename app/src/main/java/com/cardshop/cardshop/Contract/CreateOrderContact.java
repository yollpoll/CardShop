package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.GoodsModule;

public interface CreateOrderContact {
    abstract class Presenter<V> extends BasePresenter<V> {
        public abstract void addCount();

        public abstract void reduceCount();

        public abstract void createOrder();

        public abstract void payByBalance(String psw);

        public abstract void payAli();

    }

    interface IView extends IBaseView<CreateOrderContact.Presenter> {
        void initGoods(GoodsModule goodsModule);

        void onSetCount(int count, double amount);

        void initBalance(String balance);

        void onCreateOrder();

    }
}
