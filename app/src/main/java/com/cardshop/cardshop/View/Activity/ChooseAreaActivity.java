package com.cardshop.cardshop.View.Activity;

import android.app.Activity;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.ChooseAreaContract;
import com.cardshop.cardshop.PresenterImpl.ChooseAreaPresenterImpl;
import com.cardshop.cardshop.View.Fragment.ChooseAreaFragment;

public class ChooseAreaActivity extends SimpleActivity {
    public static void gotoChooseArea(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, ChooseAreaActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void initData() {
        super.initData();
        new ChooseAreaPresenterImpl((ChooseAreaContract.IView) loadBaseFragment(ChooseAreaFragment.newInstance()));
    }
}
