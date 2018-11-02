package com.cardshop.cardshop.View.Activity;

import android.app.Activity;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.ChooseCardWidthdrawContract;
import com.cardshop.cardshop.PresenterImpl.ChooseCardWithdrawPresenterImpl;
import com.cardshop.cardshop.View.Fragment.ChooseCardWithdrawFragment;

public class ChooseCardWithdrawActivity extends SimpleActivity {
    public static void gotoChooseCardActivity(Activity context,int requestCode) {
        Intent intent = new Intent(context, ChooseCardWithdrawActivity.class);
        context.startActivityForResult(intent,requestCode);
    }

    @Override
    protected void initData() {
        super.initData();
        new ChooseCardWithdrawPresenterImpl((ChooseCardWidthdrawContract.IView) loadBaseFragment(ChooseCardWithdrawFragment.newInstance()));
    }
}
