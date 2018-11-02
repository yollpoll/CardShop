package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.OrderZhuanmaiModule;

import java.util.List;

public interface OrderZhuanmaiPagerContact {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void loadMore();
        public abstract void refreshData();
    }

    interface IView extends IBaseView<OrderZhuanmaiPagerContact.IPresenter> {
        void initRv(List<OrderZhuanmaiModule> list);

        void refreshRv();

        void onLoadMore(int position, int count);
    }
}
