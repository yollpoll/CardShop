package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Adapter.HomeGoodsAdapter;
import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.GoodsContract;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.GoodsDetailActivity;

import java.util.List;

public class GoodsPagerFragment extends BaseFragment implements GoodsContract.IView {

    private GoodsContract.IPresenter presenter;

    private RecyclerView rvGoods;
    private HomeGoodsAdapter mAdapter;

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    public static GoodsPagerFragment newInstance(int code) {
        GoodsPagerFragment fragment = new GoodsPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rvGoods = view.findViewById(R.id.rv_goods);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods, container, false);
    }

    @Override
    public void setPresenter(GoodsContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initGoods(List<GoodsModule.Entity> list) {
        mAdapter = new HomeGoodsAdapter(list, onItemClickListener);
        rvGoods.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvGoods.setLayoutManager(gridLayoutManager);
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            GoodsDetailActivity.gotoGoodsDetailActivity(getActivity());
        }
    };

    @Override
    public void refresh() {
        mAdapter.notifyDataSetChanged();
    }
}

