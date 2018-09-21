package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Adapter.MyAddressAdapter;
import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.AddressChooseContract;
import com.cardshop.cardshop.Listener.OnItemClickListener;
import com.cardshop.cardshop.Module.AddressModule;
import com.cardshop.cardshop.R;

import java.util.List;

public class AddressChooseFragment extends BaseFragment implements AddressChooseContract.IView {
    private AddressChooseContract.Presenter presenter;

    private RecyclerView rvAddress;
    private MyAddressAdapter mAdapter;


    public static AddressChooseFragment newInstance() {
        return new AddressChooseFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(AddressChooseContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_address_choose, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rvAddress = view.findViewById(R.id.rv_address);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        setTitle("我的地址");
        showBack();
    }

    @Override
    public void initRecyclerView(List<AddressModule> list) {
        mAdapter = new MyAddressAdapter(list, onItemClickListener);
        rvAddress.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAddress.setLayoutManager(linearLayoutManager);
        rvAddress.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void refreshRecyclerView() {
        mAdapter.notifyDataSetChanged();
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {

        }
    };
}
