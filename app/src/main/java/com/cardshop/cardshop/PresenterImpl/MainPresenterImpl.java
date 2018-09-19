package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.MainContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Listener.OnRxScrollListener;
import com.cardshop.cardshop.Module.AnnouncementModule;
import com.cardshop.cardshop.Module.BannerModule;
import com.cardshop.cardshop.Utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterImpl extends MainContract.MainPresenter<MainContract.MainView> {
    private MainContract.MainView mView;

    private boolean isScrollBanner = true, isScrollAnnouncement = true;
    //banner
    private List<String> listBanner = new ArrayList<>();
    //announcement
    private List<String> listAnnouncement = new ArrayList<>();

    public MainPresenterImpl(MainContract.MainView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }


    @Override
    public void start() {
        mView.initBanner(listBanner, null);
        mView.initGoods();
        getBannerData();
        getAnnounceMentData();
    }

    @Override
    public void detach() {
        super.detach();
        stopScrollAnnouncement();
        stopScollBanner();
    }


    @Override
    public void getBannerData() {
        BannerModule.getBannerModule(new Callback<ResponseData<BannerModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BannerModule>> call, Response<ResponseData<BannerModule>> response) {
                if (response.body().isSuccess()) {
                    listBanner.clear();
                    listBanner.addAll(response.body().getDatas().getBanner());
                    mView.refreshBanner();
                    startScrollBanner();
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BannerModule>> call, Throwable t) {

            }
        });
    }


    @Override
    public void startScrollBanner() {
        RxUtils.StartScrollBanner(listBanner, new OnRxScrollListener() {
            @Override
            public void onScroll(int position) {
                mView.setPageCount(position);
            }
        });
    }

    @Override
    public void stopScollBanner() {
        RxUtils.stopScrollBanner();
    }

    @Override
    public void getAnnounceMentData() {
        AnnouncementModule.getModule(new Callback<ResponseData<AnnouncementModule>>() {
            @Override
            public void onResponse(Call<ResponseData<AnnouncementModule>> call, Response<ResponseData<AnnouncementModule>> response) {
                listAnnouncement.clear();
                listAnnouncement.addAll(response.body().getDatas().getHome_notice());
                startScrollAnnouncement();
            }

            @Override
            public void onFailure(Call<ResponseData<AnnouncementModule>> call, Throwable t) {

            }
        });
    }

    @Override
    public void startScrollAnnouncement() {
        RxUtils.StartScroll(listAnnouncement, new OnRxScrollListener() {
            @Override
            public void onScroll(int position) {
                mView.setAnnouncement(listAnnouncement.get(position));
            }
        });
    }

    @Override
    public void stopScrollAnnouncement() {
        RxUtils.stopScrollAnnouncement();
    }

    @Override
    public void getGoodsData() {

    }
}
