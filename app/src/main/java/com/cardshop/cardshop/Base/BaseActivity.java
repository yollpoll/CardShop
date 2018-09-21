package com.cardshop.cardshop.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cardshop.cardshop.R;
import com.gyf.barlibrary.ImmersionBar;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected ImmersionBar mImmersionBar;
    public static final String FRAGMENT_TAG = "fragment_tag";

    protected void initFragment() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mImmersionBar != null) {
            this.mImmersionBar.destroy();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mImmersionBar = ImmersionBar.with(this);
    }

    protected void initData() {

    }

    protected void initView() {

    }

    public ImmersionBar getmImmersionBar() {
        return this.mImmersionBar;
    }

    @Override
    public void onClick(View view) {

    }

    protected BaseFragment loadBaseFragment(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.rl_root, fragment, FRAGMENT_TAG).commit();
        return fragment;
    }
}
