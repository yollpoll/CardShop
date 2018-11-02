package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.OrderYigouModule;

import java.util.List;

public interface OrderYigouPagerContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void load();

        public abstract void refresh();

        public abstract void applyGetGoods(int position);
    }

    interface IView extends IBaseView<OrderYigouPagerContract.IPresenter> {
        void initRv(List<OrderYigouModule> list);

        void onLoad(int position, int count);

        void onRefreshData();

        void onApplyGetGoods(OrderYigouModule orderYigouModule);
    }
}
