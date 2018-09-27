package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.RechargeContract;
import com.cardshop.cardshop.PresenterImpl.RechargePresenterImpl;
import com.cardshop.cardshop.View.Fragment.RechargeFragment;

public class RechargeActivity extends SimpleActivity {
    public static void gotoRechargeActivity(Context context) {
        Intent intent = new Intent(context, RechargeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        new RechargePresenterImpl((RechargeContract.IView) loadBaseFragment(RechargeFragment.newInstance()));
    }
}
