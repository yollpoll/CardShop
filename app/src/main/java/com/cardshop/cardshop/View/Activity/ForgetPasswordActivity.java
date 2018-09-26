package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.PresenterImpl.ForgetPasswordPresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.ForgetPasswordFragment;

public class ForgetPasswordActivity extends SimpleActivity {
    private String title;

    public static void gotoForgetPasswordActivity(Context context, String title) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        title = getIntent().getStringExtra("title");
        FragmentManager fragmentManager = getSupportFragmentManager();
        ForgetPasswordFragment fragment = (ForgetPasswordFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragment == null) {
            fragment = ForgetPasswordFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.rl_root, fragment, FRAGMENT_TAG).commit();
        }
        new ForgetPasswordPresenterImpl(fragment, title);
    }
}
