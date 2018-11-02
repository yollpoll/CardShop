package com.cardshop.cardshop.View.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Adapter.WithDrawCardAdapter;
import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.ChooseCardWidthdrawContract;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.CardModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.AddCardActivity;

import java.util.List;

public class ChooseCardWithdrawFragment extends BaseFragment implements ChooseCardWidthdrawContract.IView, OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    private ChooseCardWidthdrawContract.IPresenter presenter;
    private RecyclerView rvCard;
    private WithDrawCardAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    public static ChooseCardWithdrawFragment newInstance() {
        return new ChooseCardWithdrawFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(ChooseCardWidthdrawContract.IPresenter presenter) {
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
        rvCard = view.findViewById(R.id.rv);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("提现");
        showBack();
        setNoStatusBar();
        setHeadRightIv(R.mipmap.icon_add, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCardActivity.gotoAddCardActivity(getActivity());
            }
        });
    }

    @Override
    public void initRv(List<CardModule> list) {
        mAdapter = new WithDrawCardAdapter(list, this);
        rvCard.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCard.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onRefreshData() {
        mAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }

    @Override
    public void onChooseCard(CardModule cardModule) {
        Intent intent = getActivity().getIntent();
        intent.putExtra("card", cardModule);
        getActivity().setResult(Activity.RESULT_OK, intent);
        goBack();
    }

    @Override
    public void onClick(View view, int position) {
        presenter.chooseCard(position);
    }
}
