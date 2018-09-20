package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.cardshop.cardshop.Adapter.FragmentPagerAdapter;
import com.cardshop.cardshop.Adapter.HomeAdPagerAdapter;
import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.MainContract;
import com.cardshop.cardshop.Listener.AppBarStateChangeListener;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.PresenterImpl.GoodsPagerPresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Widget.CycleGalleryViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BaseFragment implements MainContract.MainView {
    private MainContract.MainPresenter presenter;

    private CollapsingToolbarLayout ctbLayout;
    private TextView tvTitle;
    private AppBarLayout appBarLayout;
    //banner
    private HomeAdPagerAdapter bannerAdapter;
    private CycleGalleryViewPager bannerPager;
    //announcement
    private TextSwitcher tsAnnouncement;
    //Goods
    private ViewPager goodsPager;
    private FragmentPagerAdapter goodsAdapter;
    private List<Fragment> lisFragment = new ArrayList<>();
    private List<String> listTitles = new ArrayList<>();
    private TabLayout mTabLayout;


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        tsAnnouncement.removeAllViews();
        tsAnnouncement.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tvAnnouncement = new TextView(getActivity());
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT);
                tvAnnouncement.setLayoutParams(params);
                tvAnnouncement.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                return tvAnnouncement;
            }
        });

    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        ctbLayout = view.findViewById(R.id.collapsing);
        tvTitle = view.findViewById(R.id.tv_title);
        appBarLayout = view.findViewById(R.id.app_bar);
        bannerPager = view.findViewById(R.id.cpager_banner);
        tsAnnouncement = view.findViewById(R.id.switch_announcement);
        goodsPager = view.findViewById(R.id.pager_goods);
        mTabLayout = view.findViewById(R.id.tab_goods);

        appBarLayout.addOnOffsetChangedListener(appBarStateChangeListener);
    }

    private AppBarStateChangeListener appBarStateChangeListener = new AppBarStateChangeListener() {
        @Override
        public void onStateChanged(AppBarLayout appBarLayout, State state) {
            if (state == State.EXPANDED) {
                //展开状态
                tvTitle.setVisibility(View.GONE);
            } else if (state == State.COLLAPSED) {
                //折叠状态
                tvTitle.setVisibility(View.VISIBLE);
            } else {
                //中间状态

            }
        }
    };

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
        }
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(MainContract.MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initBanner(List<String> list, OnItemClickListener onItemClickListener) {
        bannerAdapter = new HomeAdPagerAdapter(list, onItemClickListener);
        bannerPager.setNarrowFactor(1);
        bannerPager.setAdapter(bannerAdapter);
    }

    @Override
    public void refreshBanner() {
        bannerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPageCount(int count) {
        bannerPager.setCurrentItem(count, true);
    }

    @Override
    public void setAnnouncement(String content) {
        tsAnnouncement.setText(content);
    }

    @Override
    public void initGoods() {
        listTitles.clear();
        lisFragment.clear();
        for (int i = 0; i < 4; i++) {
            GoodsPagerFragment goodsPagerFragment = GoodsPagerFragment.newInstance(i);
            lisFragment.add(goodsPagerFragment);
            new GoodsPagerPresenterImpl(goodsPagerFragment, 0);
        }
        listTitles.add("移动卡");
        listTitles.add("联通卡");
        listTitles.add("电信卡");
        listTitles.add("京东卡");
        goodsAdapter = new FragmentPagerAdapter(getChildFragmentManager(), lisFragment, listTitles);
        goodsPager.setAdapter(goodsAdapter);
        mTabLayout.setupWithViewPager(goodsPager);
    }

}
