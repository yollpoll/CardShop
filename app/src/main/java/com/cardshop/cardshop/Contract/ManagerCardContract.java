package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.CardModule;

public interface ManagerCardContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void delCard();
    }

    interface IView extends IBaseView<ManagerCardContract.IPresenter> {
        void setCard(CardModule card);
    }
}
