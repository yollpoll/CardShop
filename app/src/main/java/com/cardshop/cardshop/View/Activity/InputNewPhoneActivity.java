package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.InputNewPhoneContract;
import com.cardshop.cardshop.PresenterImpl.InputNewPhonePresenterImpl;
import com.cardshop.cardshop.View.Fragment.InputNewPhoneFragment;

public class InputNewPhoneActivity extends SimpleActivity {
    public static void gotoInputNewActivity(Context context) {
        Intent intent = new Intent(context, InputNewPhoneActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        new InputNewPhonePresenterImpl((InputNewPhoneContract.IView) loadBaseFragment(InputNewPhoneFragment.newInstance()));
    }


}
