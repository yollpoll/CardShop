package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.CardModule;

import java.util.List;

public interface CardContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
    }

    interface IView extends IBaseView<CardContract.IPresenter> {
        void initRvCard(List<CardModule> list);
        void refreshRv();
    }
}
