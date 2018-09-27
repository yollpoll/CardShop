package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.WithdrawContract;
import com.cardshop.cardshop.PresenterImpl.WithdrawPresenterImpl;
import com.cardshop.cardshop.View.Fragment.WithdrawFragment;

public class WithdrawActivity extends SimpleActivity {

    public static void gotoWithDrawActivity(Context context) {
        Intent intent = new Intent(context, WithdrawActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        new WithdrawPresenterImpl((WithdrawContract.IView) loadBaseFragment(WithdrawFragment.newInstance()));
    }
}
