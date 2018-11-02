package com.cardshop.cardshop.View.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.WithdrawContract;
import com.cardshop.cardshop.Module.CardModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Utils.DialogUtils;
import com.cardshop.cardshop.View.Activity.ChooseCardWithdrawActivity;
import com.cardshop.cardshop.Widget.PasswordEditLayout;

public class WithdrawFragment extends BaseFragment implements WithdrawContract.IView, PasswordEditLayout.OnInputCompleteListener {

    public static final int REQUEST_CHOOSE_CARD = 1;
    private WithdrawContract.IPresenter presenter;

    private RelativeLayout rlCard;
    private TextView tvBalance;
    private ImageView ivCard;
    private TextView tvCardName, tvCardCode;
    private TextView tvWithDrawAll;
    private EditText edtMoney;
    private TextView tvNoCard;
    private Button btnOk;

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
        tvBalance = view.findViewById(R.id.tv_balance);
        ivCard = view.findViewById(R.id.iv_card);
        tvCardName = view.findViewById(R.id.tv_card_name);
        tvCardCode = view.findViewById(R.id.tv_card_code);
        tvWithDrawAll = view.findViewById(R.id.tv_withdraw_all);
        edtMoney = view.findViewById(R.id.edt_money);
        tvNoCard = view.findViewById(R.id.tv_no_card);
        btnOk = view.findViewById(R.id.btn_ok);


        btnOk.setOnClickListener(this);
        tvWithDrawAll.setOnClickListener(this);
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
                ChooseCardWithdrawActivity.gotoChooseCardActivity(getActivity(), REQUEST_CHOOSE_CARD);
                break;
            case R.id.tv_withdraw_all:
                presenter.withdrawAll();
                break;
            case R.id.btn_ok:
                DialogUtils.showPayPasswordDialog(getActivity(), this);
//                presenter.withdraw(edtMoney.getText().toString());
                break;
        }
    }

    @Override
    public void initCard(CardModule cardModule, int avatarId) {
        if (null != cardModule) {
            tvNoCard.setVisibility(View.GONE);
            rlCard.setVisibility(View.VISIBLE);
            tvCardCode.setText("尾号" + cardModule.getPdcBankNo().substring(cardModule.getPdcBankNo().length() - 4));
            tvCardName.setText(cardModule.getPdcBankName());
            ivCard.setImageResource(avatarId);
        } else {
            tvNoCard.setVisibility(View.VISIBLE);
            rlCard.setVisibility(View.GONE);
        }
    }

    @Override
    public void initBanalce(String banalce) {
        tvBalance.setText("可用余额: " + banalce);
    }

    @Override
    public void onWithDrawAll(String money) {
        edtMoney.setText(money);
        edtMoney.setSelection(money.length());
    }

    @Override
    public void onReturnResult(int requestCode, int resultCode, Intent data) {
        super.onReturnResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHOOSE_CARD) {
            if (resultCode == Activity.RESULT_OK) {
                CardModule cardModule = (CardModule) data.getSerializableExtra("card");
                presenter.chooseCard(cardModule);
            }
        }
    }

    @Override
    public void onComplete(String psw) {
        presenter.checkPayPassword(edtMoney.getText().toString(), psw);
    }
}
