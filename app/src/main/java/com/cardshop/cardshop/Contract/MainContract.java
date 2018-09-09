package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface MainContract {
    abstract class MainPresenter<V> extends BasePresenter<V> {
        public abstract void refresh();
    }

    interface MainView extends IBaseView<MainPresenter> {
        void refreshTxt(String content);
    }
}
