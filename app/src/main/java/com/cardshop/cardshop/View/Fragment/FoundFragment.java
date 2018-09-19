package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Adapter.FoundGamesAdapter;
import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.FoundContract;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.FoundGameModule;
import com.cardshop.cardshop.R;

import java.util.List;

public class FoundFragment extends BaseFragment implements FoundContract.IView, SwipeRefreshLayout.OnRefreshListener {
    private FoundContract.IPresenter presenter;

    private RecyclerView rvFound;
    private SwipeRefreshLayout swipeRefresh;
    private FoundGamesAdapter mAdapter;

    public static FoundFragment newInstance() {
        return new FoundFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_found, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rvFound = view.findViewById(R.id.rv_founds);
        swipeRefresh = view.findViewById(R.id.swipe_refresh);

        swipeRefresh.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
    }

    @Override
    public BasePresenter createPresenter() {
        return presenter;
    }

    @Override
    public void refresh() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initGames(List<FoundGameModule.Entity> list) {
        mAdapter = new FoundGamesAdapter(list, onItemClickListener);
        rvFound.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFound.setLayoutManager(linearLayoutManager);
        rvFound.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void setPresenter(FoundContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRefresh() {
        presenter.refreshData();
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {

        }
    };
}
