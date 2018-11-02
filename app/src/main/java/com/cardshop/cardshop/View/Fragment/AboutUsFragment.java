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
import com.cardshop.cardshop.Contract.AboutUsContact;
import com.cardshop.cardshop.R;

public class AboutUsFragment extends BaseFragment implements AboutUsContact.IView {
    private AboutUsContact.Presenter presenter;
    private TextView tvVersion;

    public static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(AboutUsContact.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvVersion=view.findViewById(R.id.tv_version);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        showBack();
        setTitle("关于我们");
    }

    @Override
    public void setVersion(String content) {
        tvVersion.setText(content);
    }
}
