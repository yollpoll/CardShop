package com.cardshop.cardshop.View.Fragment;

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
import com.cardshop.cardshop.PresenterImpl.GoodsDetailPagerPresenterImpl;
import com.cardshop.cardshop.R;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailFragment extends BaseFragment implements GoodsDetailContract.IView {
    private GoodsDetailContract.IPresenter presenter;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView ivGoods;
    private TextView tvGoods;
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
    protected void initView(View view) {
        super.initView(view);
        mTabLayout = view.findViewById(R.id.tab_detail);
        mViewPager=view.findViewById(R.id.vp_detail);

    }

    @Override
    public void initPager(List<String> titles) {
        listFrgament.clear();
        for (int i = 0; i < titles.size(); i++) {
            GoodsDetailPagerFragment detailPagerFragment = GoodsDetailPagerFragment.newInstance();
            new GoodsDetailPagerPresenterImpl(detailPagerFragment);
            listFrgament.add(detailPagerFragment);
        }
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager(), listFrgament, titles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
