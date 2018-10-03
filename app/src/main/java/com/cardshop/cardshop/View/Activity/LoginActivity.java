package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.PresenterImpl.LoginPresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.LoginFragment;

public class LoginActivity extends BaseActivity {
    public static void gotoLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        mImmersionBar
                .fitsSystemWindows(false)
                .statusBarDarkFont(true)
                .init();
        FragmentManager fragmentManager = getSupportFragmentManager();
        LoginFragment fragment = (LoginFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragment == null) {
            fragment = (LoginFragment) LoginFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.rl_root, fragment, FRAGMENT_TAG).commit();
        }
        //初始化presenter
        new LoginPresenterImpl(fragment);
    }
}
