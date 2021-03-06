package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.Contract.SettingContract;
import com.cardshop.cardshop.PresenterImpl.SettingPresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.SettingFragment;

public class SettingActivity extends BaseActivity {
    public static void gotoSettingActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
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
    protected void initData() {
        super.initData();
        new SettingPresenterImpl((SettingContract.IView) loadBaseFragment(SettingFragment.newInstance()));
    }
}
