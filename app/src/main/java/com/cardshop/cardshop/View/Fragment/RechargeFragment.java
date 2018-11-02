package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.RechargeContract;
import com.cardshop.cardshop.R;

public class RechargeFragment extends BaseFragment implements RechargeContract.IView {

    private RechargeContract.IPresenter presenter;
    private RadioGroup rgWay;
    private EditText edtMoney;
    private Button btnOk;

    public static RechargeFragment newInstance() {
        return new RechargeFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(RechargeContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recharge, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rgWay = view.findViewById(R.id.rg_buy);
        edtMoney = view.findViewById(R.id.edt_money);
        btnOk = view.findViewById(R.id.btn_ok);

        btnOk.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        setTitle("充值");
        showBack();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_ok:
                presenter.recharge(edtMoney.getText().toString());
                break;
        }
    }
}
