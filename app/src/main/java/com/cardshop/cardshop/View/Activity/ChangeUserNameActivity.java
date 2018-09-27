package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.ChangeUserNameContract;
import com.cardshop.cardshop.PresenterImpl.ChangeUserNamePresenterImpl;
import com.cardshop.cardshop.View.Fragment.ChangeUserNameFragment;

public class ChangeUserNameActivity extends SimpleActivity {
    public static void gotoChangeUserNameActivity(Context context) {
        Intent intent = new Intent(context, ChangeUserNameActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        new ChangeUserNamePresenterImpl((ChangeUserNameContract.IView) loadBaseFragment(ChangeUserNameFragment.newInstance()));
    }
}
