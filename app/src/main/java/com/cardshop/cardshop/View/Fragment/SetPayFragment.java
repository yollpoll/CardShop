package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.PayPasswordContract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.SetPayPassword2Activity;
import com.cardshop.cardshop.Widget.PasswordEditLayout;

public class SetPayFragment extends BaseFragment implements PayPasswordContract.IView {
    private PayPasswordContract.IPresenter presenter;

    private TextView tvPhone;
    private LinearLayout llPassword;
    private Button btnOk;
    private PasswordEditLayout elPassword;

    public static SetPayFragment newInstance() {
        return new SetPayFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(PayPasswordContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_set_pay, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvPhone = view.findViewById(R.id.tv_phone);
        llPassword = view.findViewById(R.id.ll_password);
        btnOk = view.findViewById(R.id.btn_ok);
        elPassword = view.findViewById(R.id.el_password);

        btnOk.setOnClickListener(this);
        llPassword.setOnClickListener(this);
        elPassword.setOnInputCompleteListener(onInputCompleteListener);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("设置支付密码");
        setNoStatusBar();
        showBack();
    }

    private PasswordEditLayout.OnInputCompleteListener onInputCompleteListener = new PasswordEditLayout.OnInputCompleteListener() {
        @Override
        public void onComplete(String psw) {
        }
    };

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_ok:
                SetPayPassword2Activity.gotoSetPayPassword2Activity(getActivity());
                break;
        }
    }
}
