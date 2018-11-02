package com.cardshop.cardshop.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.RegisterContract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.ProtocolActivity;

public class RegisterFragment extends BaseFragment implements RegisterContract.IView {
    private TextInputEditText edtPhone, edtPsd, edtConfirmPsd, edtVertifyCode;
    private TextView tvVertifyCode;
    private Button btnRegister;
    private RegisterContract.IPresenter presenter;
    private TextView tvProtocol;

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
        tvProtocol = view.findViewById(R.id.tv_register_protocol);

        tvVertifyCode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        setTitle("注册");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("注册立即同意《卡淘淘用户协议》");
        spannableStringBuilder.setSpan(new MyClickableSpan(getActivity()), 6, 15, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvProtocol.setText(spannableStringBuilder);
        tvProtocol.setMovementMethod(LinkMovementMethod.getInstance());
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

    public class MyClickableSpan extends ClickableSpan {

        private Context mContext;

        public MyClickableSpan(Context context) {
            mContext = context;
        }

        @Override
        public void onClick(View widget) {
            ProtocolActivity.gotoProtocolActivity(getActivity(), "用户协议", R.raw.register_protocol);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(mContext.getResources().getColor(R.color.colorPrimary));
            ds.setUnderlineText(true);
        }
    }
}
