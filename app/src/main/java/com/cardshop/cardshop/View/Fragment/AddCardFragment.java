package com.cardshop.cardshop.View.Fragment;

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
import com.cardshop.cardshop.Contract.AddCardContract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.AddCardVertifyActivity;

public class AddCardFragment extends BaseFragment implements AddCardContract.IView {
    private AddCardContract.Presenter presenter;

    private EditText edtName, edtCode, edtIdentity, edtPhone, edtBank;
    private Button btnNext;

    public static AddCardFragment newInstance() {
        return new AddCardFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(AddCardContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_card, container, false);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("添加银行卡");
        setNoStatusBar();
        showBack();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        edtName = view.findViewById(R.id.edt_people);
        edtCode = view.findViewById(R.id.edt_code);
        edtIdentity = view.findViewById(R.id.edt_identity);
        edtPhone = view.findViewById(R.id.edt_phone);
        edtBank = view.findViewById(R.id.edt_bank);
        btnNext = view.findViewById(R.id.btn_next);

        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_next:
                presenter.checkInput(edtName.getText().toString(), edtCode.getText().toString(),
                        edtIdentity.getText().toString(), edtPhone.getText().toString(), edtBank.getText().toString());
                break;
        }
    }

    @Override
    public void gotoNext(String name, String code, String identity, String phone, String bank) {
        AddCardVertifyActivity.gotoAddCardVertifyActivity(getActivity(),name,code,identity,phone,bank);
    }
}
