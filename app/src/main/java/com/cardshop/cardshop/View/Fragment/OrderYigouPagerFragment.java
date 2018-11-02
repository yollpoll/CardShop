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

import com.cardshop.cardshop.Adapter.FooterAdapter;
import com.cardshop.cardshop.Adapter.OrderYigouAdapter;
import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.OrderYigouPagerContract;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.OrderYigouModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.ApplyGetGoodsActivity;

import java.util.List;

public class OrderYigouPagerFragment extends BaseFragment implements OrderYigouPagerContract.IView, SwipeRefreshLayout.OnRefreshListener {
    private OrderYigouPagerContract.IPresenter presenter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private OrderYigouAdapter mAdapter;
    private boolean isLoadingMore = false;

    public static OrderYigouPagerFragment newInstance() {
        return new OrderYigouPagerFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(OrderYigouPagerContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yigou_pager, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecyclerView = view.findViewById(R.id.rv_card);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }

    @Override
    public void initRv(List<OrderYigouModule> list) {
        mAdapter = new OrderYigouAdapter(list, onItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
        mAdapter.setNormal();
    }

    @Override
    public void onApplyGetGoods(OrderYigouModule orderYigouModule) {
        ApplyGetGoodsActivity.gotoApplyGetGoodsActivity(getActivity(), orderYigouModule);
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            presenter.applyGetGoods(position);
        }
    };
}
