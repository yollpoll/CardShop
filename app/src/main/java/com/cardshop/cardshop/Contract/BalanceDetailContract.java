package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.BalanceModule;

import java.util.List;

public interface BalanceDetailContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void load();

        public abstract void refresh();
    }

    interface IView extends IBaseView<BalanceDetailContract.IPresenter> {
        void initRv(List<BalanceModule> list);

        void onLoad(int position, int count);

        void onRefreshData();
    }
}
