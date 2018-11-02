package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.GoodsTypeModule;

import java.util.List;

public interface MainContract {
    abstract class MainPresenter<V> extends BasePresenter<V> {
        public abstract void getBannerData();

        public abstract void startScrollBanner();


        public abstract void startScrollAnnouncement();

        public abstract void stopScollBanner();

        public abstract void stopScrollAnnouncement();


    }

    interface MainView extends IBaseView<MainPresenter> {
        void initBanner(List<String> list, OnItemClickListener onItemClickListener);

        void refreshBanner();

        void setAnnouncement(String content);

        void setPageCount(int count);

        void initGoods(List<GoodsTypeModule> list);

        void setGoodsProgressBarShow(boolean show);
    }
}
