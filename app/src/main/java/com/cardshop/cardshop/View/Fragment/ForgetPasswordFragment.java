package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.ForgetPasswordContract;
import com.cardshop.cardshop.R;

public class ForgetPasswordFragment extends BaseFragment implements ForgetPasswordContract.IView {
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
    public void gotoNext() {

    }

    @Override
    public void setPresenter(ForgetPasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        setTitle("忘记密码");
    }
}
