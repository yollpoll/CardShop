package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.BalanceContract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.BalanceDetailActivity;
import com.cardshop.cardshop.View.Activity.RechargeActivity;
import com.cardshop.cardshop.View.Activity.WithdrawActivity;

public class BalanceFragment extends BaseFragment implements BalanceContract.IView {
    private TextView tvRecharge, tvGetMoney, tvBalance;

    private BalanceContract.IPresenter presenter;

    public static BalanceFragment newInstance() {
        return new BalanceFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(BalanceContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_balance, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvRecharge = view.findViewById(R.id.tv_recharge);
        tvGetMoney = view.findViewById(R.id.tv_get_money);
        tvBalance = view.findViewById(R.id.tv_balance);

        tvRecharge.setOnClickListener(this);
        tvGetMoney.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("我的余额");
        showBack();
        setHeadRightText("明细", this);
        setNoStatusBar();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_head_right:
                BalanceDetailActivity.gotoBalanceDetailActivity(getActivity());
                break;
            case R.id.tv_recharge:
                RechargeActivity.gotoRechargeActivity(getActivity());
                break;
            case R.id.tv_get_money:
                WithdrawActivity.gotoWithDrawActivity(getActivity());
                break;
        }
    }

    @Override
    public void setBalance(String balance) {
        tvBalance.setText(balance);
    }
}
