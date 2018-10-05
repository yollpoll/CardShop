package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.InputRealNameContract;
import com.cardshop.cardshop.Contract.RealMsgContract;
import com.cardshop.cardshop.PresenterImpl.InputRealPresenterImpl;
import com.cardshop.cardshop.PresenterImpl.RealMsgPresenterImpl;
import com.cardshop.cardshop.View.Fragment.InPutRealNameFragment;
import com.cardshop.cardshop.View.Fragment.RealMsgFragment;

public class RealNameActivity extends SimpleActivity {
    private boolean isVertified = false;

    public static void gotoRealNameActivity(Context context, boolean isVertifide) {
        Intent intent = new Intent(context, RealNameActivity.class);
        intent.putExtra("isVertified", isVertifide);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        isVertified = getIntent().getBooleanExtra("isVertified", false);
        if (isVertified) {
            //已经通过认证
            new RealMsgPresenterImpl((RealMsgContract.IView) loadBaseFragment(RealMsgFragment.newInstance()));
        } else {
            new InputRealPresenterImpl((InputRealNameContract.IView) loadBaseFragment(InPutRealNameFragment.newInstance()));
        }
    }
}
