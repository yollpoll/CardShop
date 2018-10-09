package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Adapter.CityAdapter;
import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.CityContract;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.CityModule;
import com.cardshop.cardshop.R;

import java.util.List;

public class CityFragment extends BaseFragment implements CityContract.IView {
    private CityContract.IPresenter presenter;
    private RecyclerView rvCity;
    private CityAdapter mAdapter;

    public static CityFragment newInstance() {
        return new CityFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(CityContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city, container, false);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rvCity = view.findViewById(R.id.rv_city);
    }

    @Override
    public void initRv(List<CityModule> list) {
        mAdapter = new CityAdapter(list, onItemClickListener);
        mAdapter.setNormal();
        rvCity.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCity.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void refresh() {
        mAdapter.notifyDataSetChanged();
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            presenter.checkEd(position);
        }
    };
}
