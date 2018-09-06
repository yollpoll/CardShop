package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.IBasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface MainContract {
    interface MainPresenter extends IBasePresenter {
        void refresh();
    }

    interface MainView extends IBaseView<MainPresenter> {
        void refreshTxt(String content);
    }
}
