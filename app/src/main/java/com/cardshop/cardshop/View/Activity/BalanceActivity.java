package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.Contract.BalanceContract;
import com.cardshop.cardshop.PresenterImpl.BalancePresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.BalanceFragment;

public class BalanceActivity extends BaseActivity {
    public static void gotoBalanceActivity(Context context) {
        Intent intent = new Intent(context, BalanceActivity.class);
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
        new BalancePresenterImpl((BalanceContract.IView) loadBaseFragment(BalanceFragment.newInstance()));
    }
}
