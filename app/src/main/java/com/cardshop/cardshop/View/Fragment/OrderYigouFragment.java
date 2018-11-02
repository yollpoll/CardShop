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

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePagerAdapter;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.OrderYigouContract;
import com.cardshop.cardshop.R;

import java.util.List;

public class OrderYigouFragment extends BaseFragment implements OrderYigouContract.IView {
    private OrderYigouContract.IPresenter presenter;
    private ViewPager mViewPager;
    private TabLayout mTablayout;
    private BasePagerAdapter mAdapter;


    public static OrderYigouFragment newInstance() {
        return new OrderYigouFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter = presenter;
    }

    @Override
    public void setPresenter(OrderYigouContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_yigou, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mViewPager = view.findViewById(R.id.vp_purchased);
        mTablayout = view.findViewById(R.id.tab);
    }

    @Override
    public void initVp(List<Fragment> list, List<String> listTitle) {
        mAdapter = new BasePagerAdapter(getChildFragmentManager(), list, listTitle);
        mViewPager.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void refreshVp() {
        mAdapter.notifyDataSetChanged();
    }
}
