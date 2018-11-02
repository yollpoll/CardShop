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
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.ChangePhonContract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Widget.PasswordEditLayout;

public class ChangePhoneFragment extends BaseFragment implements ChangePhonContract.IView {
    private ChangePhonContract.IPresenter presenter;

    private TextView tvPhone;
    private PasswordEditLayout llVertifyCode;
    private TextView tvCountDown;
    private Button btnOk;

    public static ChangePhoneFragment newInstance() {
        return new ChangePhoneFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(ChangePhonContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_phone, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvPhone = view.findViewById(R.id.tv_phone);
        tvCountDown = view.findViewById(R.id.tv_count_down);
        llVertifyCode = view.findViewById(R.id.ll_vertifycode);
        btnOk = view.findViewById(R.id.btn_ok);

        tvCountDown.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        showBack();
        setTitle("更换手机号");
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_count_down:
                presenter.sendSms();
                break;
            case R.id.btn_ok:
                presenter.vertifySms(llVertifyCode.getPsw());
                break;
        }
    }

    @Override
    public void showCountDown(String count) {
        tvCountDown.setClickable(false);
        tvCountDown.setTextColor(getActivity().getResources().getColor(R.color.colorHint));
        tvCountDown.setText(count);
    }

    @Override
    public void setNewPhone(String phone) {
        tvPhone.setText(phone);
    }

    @Override
    public void onCountDownFinsh() {
        tvCountDown.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
        tvCountDown.setText("重新发送");
        tvCountDown.setClickable(true);
    }

    @Override
    public void onVertifySuccess(String phone) {
        showToast("修改成功");
        Intent intent = getActivity().getIntent();
        intent.putExtra("phone", phone);
        getActivity().setResult(Activity.RESULT_OK, intent);
        goBack();
    }
}
