package com.cardshop.cardshop.Contract;

import android.content.Context;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

public interface SettingContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void openGesture(boolean open);

        public abstract void clearCache(Context context);
        public abstract void logout();
    }

    interface IView extends IBaseView<SettingContract.IPresenter> {
        void openGesture(boolean open);

        void setCache(String content);
    }
}
