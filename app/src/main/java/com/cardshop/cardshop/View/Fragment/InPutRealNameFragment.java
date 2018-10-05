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
import com.cardshop.cardshop.Contract.InputRealNameContract;
import com.cardshop.cardshop.R;

public class InPutRealNameFragment extends BaseFragment implements InputRealNameContract.IView {

    private InputRealNameContract.IPresenter presenter;
    private EditText edtName, edtCode;
    private Button btnOk;

    public static InPutRealNameFragment newInstance() {
        return new InPutRealNameFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(InputRealNameContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input_real_name, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        edtName = view.findViewById(R.id.edt_name);
        edtCode = view.findViewById(R.id.edt_identity);
        btnOk = view.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("实名信息");
        showBack();
        setNoStatusBar();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_ok:
                presenter.checkInput(edtName.getText().toString(), edtCode.getText().toString());
                break;
        }
    }
}
