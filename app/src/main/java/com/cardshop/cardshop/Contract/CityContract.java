package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.CityModule;

import java.util.List;

public interface CityContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void checkEd(int position);
    }

    interface IView extends IBaseView<CityContract.IPresenter> {
        void initRv(List<CityModule> list);
        void refresh();
    }
}
