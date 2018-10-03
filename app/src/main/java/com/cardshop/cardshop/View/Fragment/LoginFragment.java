package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.LoginContract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.ForgetPasswordActivity;
import com.cardshop.cardshop.View.Activity.MainActivity;
import com.cardshop.cardshop.View.Activity.RegisterActivity;

public class LoginFragment extends BaseFragment implements LoginContract.IView {
    private LoginContract.IPresenter presenter;

    private Button btnLogin;
    private TextInputEditText edtUsername, edtPassword;
    private TextView tvForgetPassword, tvRegister;
    private CheckBox checkShowPassword;
    private ImageView ivWechat;

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
    protected void initData() {
        super.initData();
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
        checkShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edtPassword.setSelection(edtPassword.getText().toString().length());
                } else {
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edtPassword.setSelection(edtPassword.getText().toString().length());
                }
            }
        });
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        btnLogin = view.findViewById(R.id.btn_login);
        edtUsername = view.findViewById(R.id.edt_phone);
        edtPassword = view.findViewById(R.id.edt_password);
        tvForgetPassword = view.findViewById(R.id.tv_forgetpswd);
        tvRegister = view.findViewById(R.id.tv_register);
        checkShowPassword = view.findViewById(R.id.check_password);
        ivWechat = view.findViewById(R.id.iv_wechat);

        ivWechat.setOnClickListener(this);
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
                ForgetPasswordActivity.gotoForgetPasswordActivity(getActivity(), "忘记密码");
                break;
            case R.id.tv_register:
                RegisterActivity.gotoRegisterActivity(getActivity());
                break;
            case R.id.iv_wechat:
                presenter.loginViaWx();
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
    public void initLoginPhone(String phone) {
        edtUsername.setText(phone);
    }

    @Override
    public void showPassword(boolean isShow) {

    }

    @Override
    public void onLoginResult(boolean result, String message) {
        if (result) {
            MainActivity.gotoMainActivity(getActivity());
            getActivity().finish();
            showToast(message);
        } else {
            showSnackerToast(message);

        }
    }


    @Override
    public void setPresenter(LoginContract.IPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.onLoginViaWx();
    }

    private void checkNull() {
        presenter.checkNull(edtUsername.getText().toString(),
                edtPassword.getText().toString());
    }
}
