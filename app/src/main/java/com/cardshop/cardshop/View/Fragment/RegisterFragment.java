package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.RegisterContract;
import com.cardshop.cardshop.R;

public class RegisterFragment extends BaseFragment implements RegisterContract.IView {
    private TextInputEditText edtPhone, edtPsd, edtConfirmPsd, edtVertifyCode;
    private TextView tvVertifyCode;
    private Button btnRegister;
    private RegisterContract.IPresenter presenter;

    public static RegisterFragment newInstance() {
        RegisterFragment registerFragment = new RegisterFragment();
        return registerFragment;
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(RegisterContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        edtPhone = view.findViewById(R.id.edt_phone);
        edtPsd = view.findViewById(R.id.edt_password);
        edtConfirmPsd = view.findViewById(R.id.edt_confirm_password);
        edtVertifyCode = view.findViewById(R.id.edt_vertify_code);
        tvVertifyCode = view.findViewById(R.id.tv_vertify_code);
        btnRegister = view.findViewById(R.id.btn_register);

        tvVertifyCode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        setTitle("注册");
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_vertify_code:
                getVertifyCode();
                break;
            case R.id.btn_register:
                regietser();
                break;
        }
    }

    @Override
    public void showSendVertifyCode(String result) {
        showSnackerToast(result);
    }

    @Override
    public void registerResult(boolean result, String content) {
        if (!result) {
            showSnackerToast(content);
        } else {
            showToast(content);
            getActivity().finish();
        }
    }


    private void getVertifyCode() {
        if (presenter.checkPhone(edtPhone.getText().toString())) {
            presenter.getVertifyCode(edtPhone.getText().toString());
        } else {
            showSnackerToast("请输入手机号");
        }
    }

    private void regietser() {
        String phone = edtPhone.getText().toString();
        String psd = edtPsd.getText().toString();
        String psdConfirm = edtConfirmPsd.getText().toString();
        String vertifyCode = edtVertifyCode.getText().toString();
        presenter.checkRegisterInput(phone, psd, psdConfirm, vertifyCode);
    }

}
