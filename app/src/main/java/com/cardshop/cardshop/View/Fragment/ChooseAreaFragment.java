package com.cardshop.cardshop.View.Fragment;

import android.app.Activity;
import android.content.Intent;
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
import com.cardshop.cardshop.Base.BaseFragmentStatePagerAdapter;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.ChooseAreaContract;
import com.cardshop.cardshop.R;

import java.util.List;

public class ChooseAreaFragment extends BaseFragment implements ChooseAreaContract.IView {
    private ChooseAreaContract.IPresenter presenter;
    private ViewPager mViewPager;
    private TabLayout mTablayout;
    private BaseFragmentStatePagerAdapter mAdapter;
    private int currentPager = 0;

    public static ChooseAreaFragment newInstance() {
        return new ChooseAreaFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(ChooseAreaContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose_area, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mViewPager = view.findViewById(R.id.vp_address);
        mTablayout = view.findViewById(R.id.tab);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("选择省市");
        setNoStatusBar();
        showBack();
    }

    @Override
    public void initVP(List<Fragment> list, List<String> titles) {
        mAdapter = new BaseFragmentStatePagerAdapter(getChildFragmentManager(), list, titles);
        mViewPager.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPager = position;
                presenter.currentPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void refresh() {
        mAdapter.notifyDataSetChanged();
        currentPager++;
        mViewPager.setCurrentItem(currentPager, true);
    }

    @Override
    public void onFinish(String area) {
        Intent intent = getActivity().getIntent();
        intent.putExtra("area", area);
        getActivity().setResult(Activity.RESULT_OK,intent);
        goBack();
    }
}
