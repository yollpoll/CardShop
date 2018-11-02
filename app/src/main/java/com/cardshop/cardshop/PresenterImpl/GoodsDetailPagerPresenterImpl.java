package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.GoodsDetailPagerContract;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.R;

public class GoodsDetailPagerPresenterImpl extends GoodsDetailPagerContract.IPresenter<GoodsDetailPagerContract.IView> {
    public static final int SPJS = 0;
    public static final int GGCS = 1;
    public static final int SHBZ = 2;

    public static final int YIDONG = 0;
    public static final int LIANTONG = 1;
    public static final int DIANXIN = 2;
    public static final int JINGDONG = 3;
    private GoodsDetailPagerContract.IView mView;
    private int position = 0;
    private int type = 0;
    private GoodsModule goodsModule;

    public GoodsDetailPagerPresenterImpl(GoodsDetailPagerContract.IView mView, int position, int type, GoodsModule goodsModule) {
        this.mView = mView;
        this.type = type;
        this.goodsModule = goodsModule;
        this.position = position;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
        switch (position) {
            case SPJS:
                initDetail();
                break;
            case GGCS:
                initGuige();
                break;
            case SHBZ:
                mView.initDetail(R.mipmap.icon_goods_shouhou);
                break;
        }
    }

    private void initDetail() {
        switch (type) {
            case YIDONG:
                mView.initDetail(R.mipmap.icon_goods_detail_yidong);
                break;
            case LIANTONG:
                mView.initDetail(R.mipmap.icon_goods_detail_liantong);
                break;
            case DIANXIN:
                mView.initDetail(R.mipmap.icon_goods_detail_dianxin);
                break;
            case JINGDONG:
                mView.initDetail(R.mipmap.icon_goods_detail_jingdong);
                break;
        }
    }

    private void initGuige() {
        if ((int) goodsModule.getGoodsPrice() == 100) {
            mView.initDetail(R.mipmap.icon_goods_100);
        } else if ((int) goodsModule.getGoodsPrice() == 50) {
            mView.initDetail(R.mipmap.icon_goods_50);
        }
    }
}
