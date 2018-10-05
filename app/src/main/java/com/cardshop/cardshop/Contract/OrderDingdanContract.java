package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.OrderDingdanModule;

import java.util.List;

public interface OrderDingdanContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
    }

    interface IView extends IBaseView<IPresenter> {
        public void initRv(List<OrderDingdanModule> list);
    }
}
