package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.AddressModule;
import com.cardshop.cardshop.Module.OrderYigouModule;

public interface ApplyGetGoodsContact {
    abstract class Presenter<V> extends BasePresenter<V> {
        public abstract void addCount();

        public abstract void reduceCount();

        public abstract void chooseAddress(AddressModule addressModule);

        public abstract void applyGetGoods(String phone);

        public abstract void checkPayPassword(String phone,String password);
        public abstract void checkInput(String phone);
    }

    interface IView extends IBaseView<ApplyGetGoodsContact.Presenter> {
        void initModule(OrderYigouModule orderYigouModule);

        void onSetCount(String count, String allPrice);

        void initCardType(boolean isRecharge);
        void showPassword();
    }
}
