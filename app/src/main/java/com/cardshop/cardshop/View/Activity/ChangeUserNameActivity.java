package com.cardshop.cardshop.View.Activity;

import android.app.Activity;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.ChangeUserNameContract;
import com.cardshop.cardshop.PresenterImpl.ChangeUserNamePresenterImpl;
import com.cardshop.cardshop.View.Fragment.ChangeUserNameFragment;

public class ChangeUserNameActivity extends SimpleActivity {
    public static void gotoChangeUserNameActivity(Activity context, int requestCode) {
        Intent intent = new Intent(context, ChangeUserNameActivity.class);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void initData() {
        super.initData();
        new ChangeUserNamePresenterImpl((ChangeUserNameContract.IView) loadBaseFragment(ChangeUserNameFragment.newInstance()));
    }
}
