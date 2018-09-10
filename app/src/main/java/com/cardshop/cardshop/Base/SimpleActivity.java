package com.cardshop.cardshop.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cardshop.cardshop.R;


//只有一个fragment的通用activity
public class SimpleActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }
}
