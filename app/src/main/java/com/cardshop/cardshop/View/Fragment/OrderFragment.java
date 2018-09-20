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

import com.cardshop.cardshop.Adapter.FragmentPagerAdapter;
import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.OrderContract;
import com.cardshop.cardshop.PresenterImpl.OrderDingdanPresenterImpl;
import com.cardshop.cardshop.PresenterImpl.OrderTihuoPresenterImpl;
import com.cardshop.cardshop.PresenterImpl.OrderYigouPresenterImpl;
import com.cardshop.cardshop.PresenterImpl.OrderZhuanmaiPresenterImpl;
import com.cardshop.cardshop.R;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends BaseFragment implements OrderContract.IView {
    private OrderContract.IPresenter presenter;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> listFragment = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;


    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(OrderContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mViewPager = view.findViewById(R.id.vp_order);
        mTabLayout = view.findViewById(R.id.tab_order);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void initPager(List<String> list) {
        listFragment.clear();
        OrderDingdanFragment orderDingdanFragment = OrderDingdanFragment.newInstance();
        OrderYigouFragment orderYigouFragment = OrderYigouFragment.newInstance();
        OrderTihuoFragment orderTihuoFragment = OrderTihuoFragment.newInstance();
        OrderZhuanmaiFragment orderZhuanmaiFragment = OrderZhuanmaiFragment.newInstance();

        new OrderDingdanPresenterImpl(orderDingdanFragment);
        new OrderYigouPresenterImpl(orderYigouFragment);
        new OrderTihuoPresenterImpl(orderTihuoFragment);
        new OrderZhuanmaiPresenterImpl(orderZhuanmaiFragment);

        listFragment.add(orderDingdanFragment);
        listFragment.add(orderYigouFragment);
        listFragment.add(orderTihuoFragment);
        listFragment.add(orderZhuanmaiFragment);

        mAdapter = new FragmentPagerAdapter(getChildFragmentManager(), listFragment, list);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
