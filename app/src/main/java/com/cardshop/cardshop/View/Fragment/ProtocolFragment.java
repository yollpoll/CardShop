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
import com.cardshop.cardshop.Contract.ProtocolContract;
import com.cardshop.cardshop.R;

public class ProtocolFragment extends BaseFragment implements ProtocolContract.IView {
    private ProtocolContract.IPresenter presenter;
    private TextView tvProtocol;

    public static ProtocolFragment newInstance() {
        return new ProtocolFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(ProtocolContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_protocol, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvProtocol = view.findViewById(R.id.tv_protocol);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
//        setTitle("用户协议");
        showBack();
    }

    @Override
    public void initProtocol(String content) {
        tvProtocol.setText(content);
    }
}
