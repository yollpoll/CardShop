package com.cardshop.cardshop.Contract;

import android.support.v4.app.Fragment;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.GoodsModule;

import java.util.List;

public interface GoodsDetailContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void createOrder();
    }

    interface IView extends IBaseView<GoodsDetailContract.IPresenter> {
        void initPager(List<String> titles, List<Fragment> list);
        void initGoods(GoodsModule goodsModule);
        void onCreateOrder(GoodsModule goodsModule);
    }
}
