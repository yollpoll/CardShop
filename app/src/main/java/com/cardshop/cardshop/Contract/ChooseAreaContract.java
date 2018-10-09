package com.cardshop.cardshop.Contract;

import android.support.v4.app.Fragment;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;

import java.util.List;

public interface ChooseAreaContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void currentPager(int pager);
    }

    interface IView extends IBaseView<ChooseAreaContract.IPresenter> {
        public void initVP(List<Fragment> list, List<String> titles);

        void refresh();

        void onFinish(String area);
    }
}
