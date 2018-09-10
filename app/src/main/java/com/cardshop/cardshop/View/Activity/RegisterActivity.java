package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.PresenterImpl.RegisterPresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.RegisterFragment;

public class RegisterActivity extends SimpleActivity {
    public static void gotoRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        FragmentManager fragmentManager = getSupportFragmentManager();
        RegisterFragment fragment = (RegisterFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragment == null) {
            fragment = RegisterFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.rl_root, fragment, FRAGMENT_TAG).commit();
        }
        new RegisterPresenterImpl(fragment);
    }
}
