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
import android.widget.EditText;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.ChangeUserNameContract;
import com.cardshop.cardshop.R;

public class ChangeUserNameFragment extends BaseFragment implements ChangeUserNameContract.IView {
    private ChangeUserNameContract.IPresenter presenter;

    private Button btnOk;
    private EditText edtUserName;


    public static ChangeUserNameFragment newInstance() {
        return new ChangeUserNameFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(ChangeUserNameContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_username, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        btnOk = view.findViewById(R.id.btn_ok);
        edtUserName = view.findViewById(R.id.edt_username);

        btnOk.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("修改用户名");
        showBack();
        setNoStatusBar();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_ok:
                presenter.change(edtUserName.getText().toString());
                break;
        }
    }

    @Override
    public void onResult(String userName) {
        Intent intent = getActivity().getIntent();
        intent.putExtra("name", userName);
        getActivity().setResult(Activity.RESULT_OK, intent);
        goBack();
    }
}
