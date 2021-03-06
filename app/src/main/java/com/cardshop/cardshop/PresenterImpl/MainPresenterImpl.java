package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.MainContract;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Listener.OnRxScrollListener;
import com.cardshop.cardshop.Module.BannerAndAnnouncementModule;
import com.cardshop.cardshop.Module.GoodsTypeModule;
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
        getGoodsType();
        getBannerData();
    }

    @Override
    public void detach() {
        super.detach();
        stopScrollAnnouncement();
        stopScollBanner();
    }

    private void getGoodsType() {
        mView.setGoodsProgressBarShow(true);
        GoodsTypeModule.getGoodsType(new Callback<ResponseData<List<GoodsTypeModule>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<GoodsTypeModule>>> call, Response<ResponseData<List<GoodsTypeModule>>> response) {
                if (null != response.body()) {
                    if (response.body().isSuccess()) {
                        mView.initGoods(response.body().getData());
                        mView.setGoodsProgressBarShow(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseData<List<GoodsTypeModule>>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getBannerData() {
//        BannerModule.getBannerModule(new Callback<ResponseData<BannerModule>>() {
//            @Override
//            public void onResponse(Call<ResponseData<BannerModule>> call, Response<ResponseData<BannerModule>> response) {
//                if (response.body().isSuccess()) {
//                    listBanner.clear();
//                    listBanner.addAll(response.body().getData().getBanner());
//                    mView.refreshBanner();
//                    startScrollBanner();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseData<BannerModule>> call, Throwable t) {
//
//            }
//        });
        BannerAndAnnouncementModule.getBannerAndAnnouncement(new Callback<ResponseData<BannerAndAnnouncementModule>>() {
            @Override
            public void onResponse(Call<ResponseData<BannerAndAnnouncementModule>> call, Response<ResponseData<BannerAndAnnouncementModule>> response) {
                if (response.body().isSuccess()) {
                    listBanner.clear();
                    listBanner.addAll(response.body().getData().getBanner());
                    mView.refreshBanner();
                    startScrollBanner();

                    listAnnouncement.clear();
                    listAnnouncement.add(response.body().getData().getBulletion());
                    startScrollAnnouncement();
                }
            }

            @Override
            public void onFailure(Call<ResponseData<BannerAndAnnouncementModule>> call, Throwable t) {

            }
        });
    }


    @Override
    public void startScrollBanner() {
        RxUtils.isScrollBanner=true;
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
    public void startScrollAnnouncement() {
        RxUtils.isScrollAnnouncement=true;
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

}
