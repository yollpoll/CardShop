package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.WithdrawContract;
import com.cardshop.cardshop.PresenterImpl.WithdrawPresenterImpl;
import com.cardshop.cardshop.View.Fragment.WithdrawFragment;

public class WithdrawActivity extends SimpleActivity {
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFragment.onReturnResult(requestCode, resultCode, data);
    }

    private WithdrawFragment mFragment;

    public static void gotoWithDrawActivity(Context context) {
        Intent intent = new Intent(context, WithdrawActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        mFragment = WithdrawFragment.newInstance();
        new WithdrawPresenterImpl((WithdrawContract.IView) loadBaseFragment(mFragment));
    }
}
