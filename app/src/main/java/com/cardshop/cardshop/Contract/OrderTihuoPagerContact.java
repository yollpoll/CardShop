package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.OrderTihuoModule;

import java.util.List;

public interface OrderTihuoPagerContact {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void loadMore();

        public abstract void refreshData();
    }

    interface IView extends IBaseView<OrderTihuoPagerContact.IPresenter> {
        void initRv(List<OrderTihuoModule> list,boolean isShoppingCard);

        void refreshRv();

        void onLoadMore(int position, int count);
    }
}
