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

import com.cardshop.cardshop.Adapter.BalanceDetailAdapter;
import com.cardshop.cardshop.Adapter.FooterAdapter;
import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.BalanceDetailContract;
import com.cardshop.cardshop.Module.BalanceModule;
import com.cardshop.cardshop.R;

import java.util.List;

public class BalanceDetailFragment extends BaseFragment implements BalanceDetailContract.IView, SwipeRefreshLayout.OnRefreshListener {
    private BalanceDetailContract.IPresenter presenter;
    private RecyclerView rvBalance;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BalanceDetailAdapter mAdapter;
    private boolean isLoadingMore;

    public static BalanceDetailFragment newInstance() {
        return new BalanceDetailFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(BalanceDetailContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_only_rv, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rvBalance = view.findViewById(R.id.rv);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("明细");
        showBack();
        setNoStatusBar();
    }

    @Override
    public void initRv(List<BalanceModule> list) {
        mAdapter = new BalanceDetailAdapter(list);
        rvBalance.setAdapter(mAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvBalance.setLayoutManager(linearLayoutManager);
        rvBalance.setItemAnimator(new DefaultItemAnimator());
        rvBalance.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                int totalItemPosition = linearLayoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy>0 表示向下滑动
//                Log.d("spq", "status" + mAdapter.getStatus());
                if (lastItemPosition >= totalItemPosition - 4 && dy > 0) {
                    if (isLoadingMore || mAdapter.getStatus() == FooterAdapter.FOOTER_TYPE_LOADING
                            || mAdapter.getStatus() == FooterAdapter.FOOTER_TYPE_NOMORE) {
                    } else {
                        mAdapter.setLoading();
                        isLoadingMore = true;
                        presenter.load();
                    }
                }
            }
        });
    }

    @Override
    public void onLoad(int position, int count) {
        mAdapter.setNormal();
        mAdapter.notifyItemRangeInserted(position, count);
        if (count == 0)
            mAdapter.setNomore();
        isLoadingMore = false;
    }

    @Override
    public void onRefreshData() {
        swipeRefreshLayout.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
        mAdapter.setNormal();
    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }
}
