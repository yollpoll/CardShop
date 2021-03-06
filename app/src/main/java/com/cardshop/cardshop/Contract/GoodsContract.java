package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.GoodsModule;

import java.util.List;

public interface GoodsContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void refreshData();

        public abstract void loadMore();

        public abstract void onClick(int position);

        public abstract void onBuyClick(int position);
    }

    interface IView extends IBaseView<GoodsContract.IPresenter> {
        void initGoods(List<GoodsModule> list);

        void refresh();

        void loadMore(int position, int count);

        void onClick(GoodsModule goodsModule);
    }
}
