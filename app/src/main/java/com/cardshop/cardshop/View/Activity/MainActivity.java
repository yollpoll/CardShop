package com.cardshop.cardshop.View.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.PresenterImpl.MainPresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.MainFragment;

public class MainActivity extends BaseActivity {
    public static final String FRAGMENT_TAG = "fragment_tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        //初始化view
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainFragment fragment = (MainFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragment == null) {
            fragment = MainFragment.newInstance("这是旧内容");
            fragmentManager.beginTransaction().add(R.id.rl_main, fragment, FRAGMENT_TAG).commit();
        }

        //初始化presenter
        new MainPresenterImpl(fragment,"这是旧的内容2");

    }
}
