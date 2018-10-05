package com.cardshop.cardshop.View.Fragment;

import android.app.Activity;
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
import com.cardshop.cardshop.Contract.AddCardVertifyContract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Widget.PasswordEditLayout;

public class AddCardVertifyFragment extends BaseFragment implements AddCardVertifyContract.IView {
    private AddCardVertifyContract.Presenter presenter;
    private TextView tvPhone, tvCountDown;
    private PasswordEditLayout llVertifyCode;
    private Button btnOk;


    public static AddCardVertifyFragment newInstance() {
        return new AddCardVertifyFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(AddCardVertifyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_card_vertify, container, false);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        showBack();
        setTitle("添加银行卡");
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvPhone = view.findViewById(R.id.tv_phone);
        llVertifyCode = view.findViewById(R.id.ll_vertifycode);
        btnOk = view.findViewById(R.id.btn_ok);
        tvCountDown = view.findViewById(R.id.tv_count_down);

        tvCountDown.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void showCountDown(String count) {
        tvCountDown.setClickable(false);
        tvCountDown.setTextColor(getActivity().getResources().getColor(R.color.colorHint));
        tvCountDown.setText(count);
    }

    @Override
    public void showPhoneNum(String phone) {
        tvPhone.setText(phone);
    }

    @Override
    public void onAddResulte(boolean result, String message) {
        if (result) {
            //修改成功
            getActivity().setResult(Activity.RESULT_OK);
            goBack();
            showToast(message);
        } else {
            showSnackerToast(message);
        }
    }

    @Override
    public void onCountDownFinish() {
        tvCountDown.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
        tvCountDown.setText("重新发送");
        tvCountDown.setClickable(true);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_ok:
                vertifySms();
                break;
            case R.id.tv_count_down:
                presenter.sendSms();
                break;
        }
    }

    private void vertifySms() {
        if (llVertifyCode.checkInput()) {
            presenter.vertifySms(llVertifyCode.getPsw());
        } else {
            showSnackerToast("请输入完整的验证码");
        }
    }
}
