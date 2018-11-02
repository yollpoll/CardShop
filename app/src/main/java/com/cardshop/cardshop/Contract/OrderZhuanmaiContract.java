package com.cardshop.cardshop.Contract;

import android.support.v4.app.Fragment;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

import java.util.List;

public interface OrderZhuanmaiContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
    }

    interface IView extends IBaseView<OrderZhuanmaiContract.IPresenter> {
        void initVp(List<Fragment> list, List<String> titles);

        void refreshVp();
    }
}
