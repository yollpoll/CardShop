package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.BalanceDetailContract;
import com.cardshop.cardshop.PresenterImpl.BalanceDetailPresenterImpl;
import com.cardshop.cardshop.View.Fragment.BalanceDetailFragment;

public class BalanceDetailActivity extends SimpleActivity {
    public static void gotoBalanceDetailActivity(Context context) {
        Intent intent = new Intent(context, BalanceDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        new BalanceDetailPresenterImpl((BalanceDetailContract.IView) loadBaseFragment(BalanceDetailFragment.newInstance()));
    }
}
