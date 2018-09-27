package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.ChooseCardWidthdrawContract;
import com.cardshop.cardshop.R;

public class ChooseCardWithdrawFragment extends BaseFragment implements ChooseCardWidthdrawContract.IView {
    private ChooseCardWidthdrawContract.IPresenter presenter;

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

            }
        });
    }
}
