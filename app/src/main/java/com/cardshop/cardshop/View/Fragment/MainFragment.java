package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Contract.MainContract;
import com.cardshop.cardshop.R;

public class MainFragment extends BaseFragment implements MainContract.MainView {
    private static final String ARG_PARAM1 = "param1";


    private String param1;
    private TextView tvContent;
    private Button btnOk;
    private MainContract.MainPresenter presenter;

    public static MainFragment newInstance(String param1) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        if (getArguments() != null) {
            param1 = getArguments().getString(ARG_PARAM1);
        }
        presenter.start();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvContent = view.findViewById(R.id.tv_content);
        btnOk=view.findViewById(R.id.btn_ok);

        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.btn_ok:
                presenter.refresh();
                break;
        }
    }

    @Override
    public void refreshTxt(String content) {
        tvContent.setText(content);
    }

    @Override
    public void setPresenter(MainContract.MainPresenter presenter) {
        this.presenter = presenter;
    }
}
