package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Adapter.FooterAdapter;
import com.cardshop.cardshop.Adapter.HomeGoodsAdapter;
import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.GoodsContract;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.GoodsDetailActivity;

import java.util.List;

public class GoodsPagerFragment extends BaseFragment implements GoodsContract.IView, SwipeRefreshLayout.OnRefreshListener {

    private GoodsContract.IPresenter presenter;

    private RecyclerView rvGoods;
    private HomeGoodsAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isLoadingMore = false;

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
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
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
    public void initGoods(List<GoodsModule> list) {
        mAdapter = new HomeGoodsAdapter(list, onItemClickListener);
        rvGoods.setAdapter(mAdapter);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvGoods.setLayoutManager(gridLayoutManager);
        rvGoods.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = gridLayoutManager.findLastVisibleItemPosition();
                int totalItemPosition = gridLayoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy>0 表示向下滑动
//                Log.d("spq", "status" + mAdapter.getStatus());
                if (lastItemPosition >= totalItemPosition - 4 && dy > 0) {
                    if (isLoadingMore || mAdapter.getStatus() == FooterAdapter.FOOTER_TYPE_LOADING
                            || mAdapter.getStatus() == FooterAdapter.FOOTER_TYPE_NOMORE) {
                    } else {
                        mAdapter.setLoading();
                        isLoadingMore = true;
                        presenter.loadMore();
                    }
                }
            }
        });
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            GoodsDetailActivity.gotoGoodsDetailActivity(getActivity());
        }
    };

    @Override
    public void refresh() {
        //这里是presenter回调的刷新时间
        swipeRefreshLayout.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
        mAdapter.setNormal();
    }

    @Override
    public void loadMore(int position, int count) {
        mAdapter.setNormal();
        mAdapter.notifyItemRangeInserted(position, count);
        if (count == 0)
            mAdapter.setNomore();
        isLoadingMore = false;
    }

    @Override
    public void onRefresh() {
        //这里是swipe的刷新时间
        presenter.refreshData();
    }
}

