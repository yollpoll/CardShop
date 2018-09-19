package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.GoodsDetailContract;
import com.cardshop.cardshop.R;

public class GoodsDetailFragment extends BaseFragment implements GoodsDetailContract.IView {
    private GoodsDetailContract.IPresenter presenter;

    public static GoodsDetailFragment newInstance() {
        return new GoodsDetailFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(GoodsDetailContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods_detail, container, false);
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
    }

}
