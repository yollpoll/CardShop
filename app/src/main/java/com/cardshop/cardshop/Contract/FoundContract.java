package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.FoundGameModule;

import java.util.List;

public interface FoundContract {
    abstract class IPresenter<V> extends BasePresenter<V> {
        public abstract void refreshData();
        public abstract void onItemClick(int position);
    }

    interface IView extends IBaseView<FoundContract.IPresenter> {
        void refresh();

        void initGames(List<FoundGameModule> list);
    }
}
