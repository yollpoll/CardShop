package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Widget.GesturePasswordView;

import java.util.List;

public interface GestureContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void onCheckPsW(List<GesturePasswordView> listPassword);
    }

    interface IView extends IBaseView<GestureContract.IPresenter> {
        void setTip(String content);
        void onCheckResult(boolean result);
    }
}
