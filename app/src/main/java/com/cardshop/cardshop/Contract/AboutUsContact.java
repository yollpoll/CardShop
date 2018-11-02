package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface AboutUsContact {
    abstract class Presenter<V> extends BasePresenter<V> {

    }

    interface IView extends IBaseView<AboutUsContact.Presenter> {
        void setVersion(String content);
    }
}
