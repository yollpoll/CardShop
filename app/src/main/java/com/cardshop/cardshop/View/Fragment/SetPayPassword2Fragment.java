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
import com.cardshop.cardshop.Contract.SetPayPassword2Contract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Widget.PasswordEditLayout;

public class SetPayPassword2Fragment extends BaseFragment implements SetPayPassword2Contract.IView {
    private SetPayPassword2Contract.IPresenter presenter;

    private TextView tvTips;
    private PasswordEditLayout llPassword;
    private int status = 0;//第一次输入


    public static SetPayPassword2Fragment newInstance() {
        return new SetPayPassword2Fragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(SetPayPassword2Contract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_paypassword2, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        llPassword = view.findViewById(R.id.ll_password);
        tvTips = view.findViewById(R.id.tv_tip);

        llPassword.setOnInputCompleteListener(onInputCompleteListener);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        showBack();
        setTitle("设置支付密码");
    }

    private PasswordEditLayout.OnInputCompleteListener onInputCompleteListener = new PasswordEditLayout.OnInputCompleteListener() {
        @Override
        public void onComplete(String psw) {
            if (status == 0) {
                presenter.setPsw(psw);
            } else if (status == 1) {
                presenter.confrimPsw(psw);
            }
        }
    };

    @Override
    public void onFirstInput(boolean result, String tip) {
        tvTips.setText(tip);
        llPassword.clearPsw();
        status = 1;
    }

    @Override
    public void onConfirmInput(boolean result, String tip) {
        if (!result) {
            //密码错误
            tvTips.setText(tip);
            llPassword.clearPsw();
            status = 0;
        } else {
            //设置成功
            tvTips.setText(tip);
        }
    }

    @Override
    public void onSuccess() {
        goBack();
    }
}
