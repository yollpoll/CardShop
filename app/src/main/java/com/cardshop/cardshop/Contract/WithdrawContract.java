package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.CardModule;

public interface WithdrawContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void withdrawAll();

        public abstract void chooseCard(CardModule cardModule);

        public abstract void withdraw(String amount);

        public abstract void checkPayPassword(String amount,String psw);
    }

    interface IView extends IBaseView<WithdrawContract.IPresenter> {
        void initCard(CardModule cardModule,int avatarId);

        void initBanalce(String banalce);

        void onWithDrawAll(String money);
    }
}
