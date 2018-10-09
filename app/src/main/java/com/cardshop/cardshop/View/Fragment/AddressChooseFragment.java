package com.cardshop.cardshop.View.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.cardshop.cardshop.Listener.OnItemLongClickListener;
import com.cardshop.cardshop.Module.AddressModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.AddAddressActivity;
import com.cardshop.cardshop.View.Activity.AddressChooseActivity;

import java.util.List;

public class AddressChooseFragment extends BaseFragment implements AddressChooseContract.IView {
    private AddressChooseContract.Presenter presenter;

    public static final int MODE_CHOOSE = 1;
    public static final int MODE_EDIT = 2;
    private int action = MODE_CHOOSE;
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
        mAdapter = new MyAddressAdapter(list, onItemClickListener, MODE_EDIT == action);
        rvAddress.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAddress.setLayoutManager(linearLayoutManager);
        rvAddress.setItemAnimator(new DefaultItemAnimator());
        if (MODE_EDIT == action)
            mAdapter.setOnItemLongClickListener(onItemLongClickListener);
    }

    @Override
    public void refreshRecyclerView() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void gotoEditAddress(AddressModule addressModule) {
        AddAddressActivity.gotoChangeAddressActivity(getActivity(), addressModule);
    }

    @Override
    public void initActionMode(int actionMode) {
        this.action = actionMode;
        switch (actionMode) {
            case MODE_CHOOSE:
                setHeadRightText("管理", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AddressChooseActivity.gotoAddressChooseActivity(getActivity(), MODE_EDIT);
                    }
                });
                break;

            case MODE_EDIT:
                setHeadRightIv(R.mipmap.icon_add, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AddAddressActivity.gotoAddAddressActivity(getActivity());
                    }
                });
                break;
        }
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            presenter.onItemClick(position);
        }
    };
    private OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {
        @Override
        public void onLongClick(View view, final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("删除地址")
                    .setMessage("确认删除收货地址吗?")
                    .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            presenter.delAddress(position);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        }
    };
}
