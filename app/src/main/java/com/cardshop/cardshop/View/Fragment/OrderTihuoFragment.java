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
import com.cardshop.cardshop.Contract.OrderTihuoContract;
import com.cardshop.cardshop.R;

import java.util.List;

public class OrderTihuoFragment extends BaseFragment implements OrderTihuoContract.IView {
    private OrderTihuoContract.IPresenter presenter;
    private ViewPager vpTihuo;
    private TabLayout tabLayout;
    private BasePagerAdapter mAdapter;

    public static OrderTihuoFragment newInstance() {
        return new OrderTihuoFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(OrderTihuoContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_tihuo, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        vpTihuo = view.findViewById(R.id.vp_tihuo);
        tabLayout = view.findViewById(R.id.tab);
    }

    @Override
    public void initVp(List<Fragment> list, List<String> titles) {
        mAdapter = new BasePagerAdapter(getChildFragmentManager(), list, titles);
        vpTihuo.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(vpTihuo);
    }

    @Override
    public void refreshVp() {
        mAdapter.notifyDataSetChanged();
    }
}
