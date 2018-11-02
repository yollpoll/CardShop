package com.cardshop.cardshop.View.Fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardshop.cardshop.Adapter.FragmentPagerAdapter;
import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.GoodsDetailContract;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.CreateOrderActivity;
import com.cardshop.framework.Utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailFragment extends BaseFragment implements GoodsDetailContract.IView {
    private GoodsDetailContract.IPresenter presenter;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView ivGoods;
    private TextView tvGoods, tvPurchase, tvPrice, tvOriPrice;
    private FragmentPagerAdapter mAdapter;

    private List<Fragment> listFrgament = new ArrayList<>();


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
    protected void initData() {
        super.initData();
        setTitle("商品详情");
        showBack();
        setNoStatusBar();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mTabLayout = view.findViewById(R.id.tab_detail);
        mViewPager = view.findViewById(R.id.vp_detail);
        ivGoods = view.findViewById(R.id.iv_goods);
        tvPurchase = view.findViewById(R.id.tv_purchase);
        tvPrice = view.findViewById(R.id.tv_price);
        tvOriPrice = view.findViewById(R.id.tv_ori_price);
        tvGoods = view.findViewById(R.id.tv_goods);

        tvOriPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvPurchase.setOnClickListener(this);
    }

    @Override
    public void initPager(List<String> titles, List<Fragment> list) {
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager(), list, titles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void initGoods(GoodsModule goodsModule) {
        tvGoods.setText(goodsModule.getGoodsName());
        tvPrice.setText("¥" + goodsModule.getAllPrice() + "");
        tvOriPrice.setText(goodsModule.getGoodsPrice() + "");
        ImageUtils.loadImage(goodsModule.getGoodsImage(), ivGoods, getActivity());

    }

    @Override
    public void onCreateOrder(GoodsModule goodsModule) {
        CreateOrderActivity.gotoCreateOrderActivity(getActivity(), goodsModule);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_purchase:
                presenter.createOrder();
                break;
        }
    }
}
