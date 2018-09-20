package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.GoodsDetailPagerContract;
import com.cardshop.cardshop.R;

public class GoodsDetailPagerFragment extends BaseFragment implements GoodsDetailPagerContract.IView {
    private GoodsDetailPagerContract.IPresenter presenter;

    public static GoodsDetailPagerFragment newInstance() {
        return new GoodsDetailPagerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods_detail_pager, container, false);
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(GoodsDetailPagerContract.IPresenter presenter) {
        this.presenter = presenter;
    }
}
