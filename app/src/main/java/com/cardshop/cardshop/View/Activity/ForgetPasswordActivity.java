package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.PresenterImpl.ForgetPasswordPresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.ForgetPasswordFragment;

public class ForgetPasswordActivity extends SimpleActivity {
    public static void gotoForgetPasswordActivity(Context context) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        FragmentManager fragmentManager = getSupportFragmentManager();
        ForgetPasswordFragment fragment = (ForgetPasswordFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragment == null) {
            fragment = ForgetPasswordFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.rl_root, fragment, FRAGMENT_TAG).commit();
        }
        new ForgetPasswordPresenterImpl(fragment);
    }
}
