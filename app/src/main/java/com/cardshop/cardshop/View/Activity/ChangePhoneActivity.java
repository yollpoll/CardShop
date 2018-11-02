package com.cardshop.cardshop.View.Activity;

import android.app.Activity;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.ChangePhonContract;
import com.cardshop.cardshop.PresenterImpl.ChangePhonPresenterImpl;
import com.cardshop.cardshop.View.Fragment.ChangePhoneFragment;

public class ChangePhoneActivity extends SimpleActivity {
    private String phone;

    public static void gotoChangePhoneActivity(Activity context, String phone, int requestCode) {
        Intent intent = new Intent(context, ChangePhoneActivity.class);
        intent.putExtra("phone", phone);
        context.startActivityForResult(intent,requestCode);
    }

    @Override
    protected void initData() {
        super.initData();
        phone = getIntent().getStringExtra("phone");
        new ChangePhonPresenterImpl((ChangePhonContract.IView) loadBaseFragment(ChangePhoneFragment.newInstance()), phone);
    }
}
