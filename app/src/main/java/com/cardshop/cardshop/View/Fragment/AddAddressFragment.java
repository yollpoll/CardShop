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
import android.widget.Switch;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.AddAddressContract;
import com.cardshop.cardshop.Module.AddressModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.ChooseAreaActivity;
import com.cardshop.cardshop.Widget.NoChildFocusRelativelayout;

public class AddAddressFragment extends BaseFragment implements AddAddressContract.IView {
    private AddAddressContract.Presenter presenter;
    public static final int REQUEST_CHOOSE_AREA = 1;

    private EditText edtName, edtPhone, editAddress, edtArea;
    private NoChildFocusRelativelayout rlArea;
    private Switch switchDefault;
    private Button btnSava;

    public static AddAddressFragment newInstance() {
        return new AddAddressFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(AddAddressContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_address, container, false);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("添加收货地址");
        showBack();
        setNoStatusBar();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        edtPhone = view.findViewById(R.id.edt_phone);
        edtName = view.findViewById(R.id.edt_name);
        editAddress = view.findViewById(R.id.edt_address);
        rlArea = view.findViewById(R.id.rl_area);
        switchDefault = view.findViewById(R.id.switch_default);
        btnSava = view.findViewById(R.id.btn_save);
        edtArea = view.findViewById(R.id.edt_area);

        rlArea.setOnClickListener(this);
        btnSava.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.rl_area:
                ChooseAreaActivity.gotoChooseArea(getActivity(), REQUEST_CHOOSE_AREA);
                break;
            case R.id.btn_save:
                presenter.checkInput(edtName.getText().toString(), edtPhone.getText().toString(), editAddress.getText().toString(), edtArea.getText().toString(),
                        switchDefault.isChecked());
                break;
        }
    }

    @Override
    public void onReturnResult(int requestCode, int resultCode, Intent data) {
        super.onReturnResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHOOSE_AREA) {
            if (resultCode == Activity.RESULT_OK) {
                String area = data.getStringExtra("area");
                edtArea.setText(area);
            }
        }
    }

    @Override
    public void init(AddressModule addressModule) {
        edtName.setText(addressModule.getTrueName());
        edtPhone.setText(addressModule.getMobPhone());
        edtArea.setText(addressModule.getAreaInfo());
        editAddress.setText(addressModule.getAddress());
        switchDefault.setChecked("1".equals(addressModule.getIsDefault()) ? true : false);
    }
}
