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
import com.cardshop.cardshop.Contract.ForgetPasswordContract;
import com.cardshop.cardshop.R;

public class ForgetPasswordFragment extends BaseFragment implements ForgetPasswordContract.IView {
    private TextView tvGetVertifyCode;
    private TextInputEditText edtPhone, edtVertifyCode, edtPsw, edtConfimPsw;
    private Button btnSubmit;

    ForgetPasswordContract.Presenter presenter;

    public static ForgetPasswordFragment newInstance() {
        ForgetPasswordFragment forgetPasswordFragment = new ForgetPasswordFragment();
        return forgetPasswordFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forgetpassword, container, false);
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }


    @Override
    public void setPresenter(ForgetPasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvGetVertifyCode = view.findViewById(R.id.tv_get_vertify_code);
        edtPhone = view.findViewById(R.id.edt_phone);
        edtVertifyCode = view.findViewById(R.id.edt_vertify_code);
        edtPsw = view.findViewById(R.id.edt_password);
        edtConfimPsw = view.findViewById(R.id.edt_confirm_password);
        btnSubmit = view.findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(this);
        tvGetVertifyCode.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_get_vertify_code:
                presenter.getVertifyCode(edtPhone.getText().toString());
                break;
            case R.id.btn_submit:
                presenter.checkInput(edtPhone.getText().toString(), edtVertifyCode.getText().toString(),
                        edtPsw.getText().toString(), edtConfimPsw.getText().toString());
                break;
        }
    }

    @Override
    public void showCountDown(String count) {
        tvGetVertifyCode.setClickable(false);
        tvGetVertifyCode.setTextColor(getActivity().getResources().getColor(R.color.colorHint));
        tvGetVertifyCode.setText(count);
    }

    @Override
    public void onCountDownFinish() {
        tvGetVertifyCode.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
        tvGetVertifyCode.setText("重新发送");
        tvGetVertifyCode.setClickable(true);
    }
}
