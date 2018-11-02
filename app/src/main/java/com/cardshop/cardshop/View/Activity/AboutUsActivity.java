package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.AboutUsContact;
import com.cardshop.cardshop.PresenterImpl.AboutUsPresenterImpl;
import com.cardshop.cardshop.View.Fragment.AboutUsFragment;

public class AboutUsActivity extends SimpleActivity {
    public static void gotoAboutUs(Context context) {
        Intent intent = new Intent(context, AboutUsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        new AboutUsPresenterImpl((AboutUsContact.IView) loadBaseFragment(AboutUsFragment.newInstance()));
    }
}
