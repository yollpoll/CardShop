package com.cardshop.cardshop.View.Activity;

import android.app.Activity;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.InputNewPhoneContract;
import com.cardshop.cardshop.PresenterImpl.InputNewPhonePresenterImpl;
import com.cardshop.cardshop.View.Fragment.InputNewPhoneFragment;

public class InputNewPhoneActivity extends SimpleActivity {
    private InputNewPhoneFragment mFragment;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFragment.onReturnResult(requestCode, resultCode, data);
    }

    public static void gotoInputNewActivity(Activity context, int requestCode) {
        Intent intent = new Intent(context, InputNewPhoneActivity.class);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void initData() {
        super.initData();
        mFragment = InputNewPhoneFragment.newInstance();
        new InputNewPhonePresenterImpl((InputNewPhoneContract.IView) loadBaseFragment(mFragment));
    }


}
