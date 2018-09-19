package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.GoodsModule;

import java.util.List;

public interface GoodsContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void refreshData();
    }

    interface IView extends IBaseView<GoodsContract.IPresenter> {
        void initGoods(List<GoodsModule.Entity> list);

        void refresh();
    }
}
