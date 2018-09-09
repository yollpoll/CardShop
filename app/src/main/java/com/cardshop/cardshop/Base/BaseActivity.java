package com.cardshop.cardshop.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

public class BaseActivity extends AppCompatActivity {
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

}
