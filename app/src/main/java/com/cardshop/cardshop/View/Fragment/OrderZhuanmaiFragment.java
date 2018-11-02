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
import com.cardshop.cardshop.Contract.OrderZhuanmaiContract;
import com.cardshop.cardshop.R;

import java.util.List;

public class OrderZhuanmaiFragment extends BaseFragment implements OrderZhuanmaiContract.IView {
    private OrderZhuanmaiContract.IPresenter presenter;
    private ViewPager vpZhuanmai;
    private TabLayout tab;
    private BasePagerAdapter mAdapter;

    public static OrderZhuanmaiFragment newInstance() {
        return new OrderZhuanmaiFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(OrderZhuanmaiContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_zhuanmai, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        vpZhuanmai = view.findViewById(R.id.vp_zhuanmai);
        tab = view.findViewById(R.id.tab);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void initVp(List<Fragment> list, List<String> titles) {
        mAdapter = new BasePagerAdapter(getChildFragmentManager(), list, titles);
        vpZhuanmai.setAdapter(mAdapter);
        tab.setupWithViewPager(vpZhuanmai);
    }

    @Override
    public void refreshVp() {
        mAdapter.notifyDataSetChanged();
    }
}
