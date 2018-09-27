package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.WithdrawContract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.ChooseCardWithdrawActivity;

public class WithdrawFragment extends BaseFragment implements WithdrawContract.IView {

    private WithdrawContract.IPresenter presenter;

    private RelativeLayout rlCard;

    public static WithdrawFragment newInstance() {
        return new WithdrawFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(WithdrawContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_withdraw, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rlCard = view.findViewById(R.id.rl_card_msg);

        rlCard.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("提现");
        showBack();
        setNoStatusBar();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.rl_card_msg:
                ChooseCardWithdrawActivity.gotoChooseCardActivity(getActivity());
                break;
        }
    }
}
