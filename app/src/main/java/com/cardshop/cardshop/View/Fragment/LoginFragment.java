package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.LoginContract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.ForgetPasswordActivity;
import com.cardshop.cardshop.View.Activity.RegisterActivity;
import com.cardshop.framework.Utils.ToastUtils;

public class LoginFragment extends BaseFragment implements LoginContract.IView {
    private LoginContract.IPresenter presenter;

    private Button btnLogin;
    private TextInputEditText edtUsername, edtPassword;
    private TextView tvForgetPassword, tvRegister;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.start();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        btnLogin = view.findViewById(R.id.btn_login);
        edtUsername = view.findViewById(R.id.edt_phone);
        edtPassword = view.findViewById(R.id.edt_password);
        tvForgetPassword = view.findViewById(R.id.tv_forgetpswd);
        tvRegister = view.findViewById(R.id.tv_register);


        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkNull();
            }
        });

        edtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkNull();
            }
        });
        tvForgetPassword.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_login:
                presenter.login(edtUsername.getText().toString(),
                        edtPassword.getText().toString());
                break;
            case R.id.tv_forgetpswd:
                ForgetPasswordActivity.gotoForgetPasswordActivity(getActivity());
                break;
            case R.id.tv_register:
                RegisterActivity.gotoRegisterActivity(getActivity());
                break;
        }
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setBtnLoginEnable(boolean enable) {
        btnLogin.setEnabled(enable);
    }

    @Override
    public void showPassword(boolean isShow) {

    }

    @Override
    public void onLoginResult(boolean result, String message) {
        ToastUtils.showShort(message);
    }


    @Override
    public void setPresenter(LoginContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    private void checkNull() {
        presenter.checkNull(edtUsername.getText().toString(),
                edtPassword.getText().toString());
    }
}
