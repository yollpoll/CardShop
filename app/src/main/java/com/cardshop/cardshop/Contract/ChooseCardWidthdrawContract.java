package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.CardModule;

import java.util.List;

public interface ChooseCardWidthdrawContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void chooseCard(int position);

        public abstract void refresh();
    }

    interface IView extends IBaseView<ChooseCardWidthdrawContract.IPresenter> {
        void initRv(List<CardModule> list);

        void onRefreshData();

        void onChooseCard(CardModule cardModule);
    }
}
