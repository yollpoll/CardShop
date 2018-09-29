package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.InputNewPhoneContract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.ChangePhoneActivity;

public class InputNewPhoneFragment extends BaseFragment implements InputNewPhoneContract.IView {
    private InputNewPhoneContract.IPresenter presenter;

    private TextView tvCurrentPhone;
    private TextInputEditText edtPhone;
    private Button btnNext;


    public static InputNewPhoneFragment newInstance() {
        return new InputNewPhoneFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(InputNewPhoneContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input_new_phone, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvCurrentPhone = view.findViewById(R.id.tv_current_phone);
        edtPhone = view.findViewById(R.id.edt_phone);
        btnNext = view.findViewById(R.id.btn_next);

        btnNext.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("更换手机号");
        setNoStatusBar();
        showBack();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_next:
                presenter.checkInput(edtPhone.getText().toString());
                break;
        }
    }

    @Override
    public void setCurrentPhone(String content, int start) {
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.colorHint)),
                start, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvCurrentPhone.setText(spannableString);
    }

    @Override
    public void onCheckInput(boolean result, String message) {
        if (result) {
            ChangePhoneActivity.gotoChangePhoneActivity(getContext(), edtPhone.getText().toString());
        } else {
            showSnackerToast(message);
        }
    }
}
