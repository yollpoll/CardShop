package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Adapter.OrderDingdanAdapter;
import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.OrderDingdanContract;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.OrderDingdanModule;
import com.cardshop.cardshop.R;

import java.util.List;

public class OrderDingdanFragment extends BaseFragment implements OrderDingdanContract.IView
        , SwipeRefreshLayout.OnRefreshListener {
    private OrderDingdanContract.IPresenter presenter;

    private OrderDingdanAdapter mAdapter;
    private RecyclerView rvOrder;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static OrderDingdanFragment newInstance() {
        return new OrderDingdanFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return presenter;
    }


    @Override
    public void setPresenter(OrderDingdanContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_dingdan, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rvOrder = view.findViewById(R.id.rv_order);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);

        swipeRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void initRv(List<OrderDingdanModule> list) {
        mAdapter = new OrderDingdanAdapter(list, onItemClickListener);
        rvOrder.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvOrder.setLayoutManager(linearLayoutManager);
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {

        }
    };

    @Override
    public void onRefresh() {

    }
}
