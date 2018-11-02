package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface RechargeContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void recharge(String money);
    }

    interface IView extends IBaseView<RechargeContract.IPresenter> {
    }
}
