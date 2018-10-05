package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.RealMsgContract;
import com.cardshop.cardshop.R;

public class RealMsgFragment extends BaseFragment implements RealMsgContract.IView {
    private RealMsgContract.IPresenter presenter;

    private TextView tvName, tvCode;

    public static RealMsgFragment newInstance() {
        return new RealMsgFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(RealMsgContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_has_vertified, container, false);
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("实名信息");
        showBack();
        setNoStatusBar();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvName = view.findViewById(R.id.tv_name);
        tvCode = view.findViewById(R.id.tv_code);

    }

    @Override
    public void setData(String name, String code) {
        tvName.setText(name);
        tvCode.setText(code);
    }
}
