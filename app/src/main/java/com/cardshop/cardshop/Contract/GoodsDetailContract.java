package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface GoodsDetailContract {
    abstract class IPresenter<V> extends BasePresenter<V> {

    }

    interface IView extends IBaseView<GoodsDetailContract.IPresenter> {
    }
}
