package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.Contract.PayPasswordContract;
import com.cardshop.cardshop.PresenterImpl.SetPayPasswordPresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.SetPayFragment;

public class SetPayPasswordActivity extends BaseActivity {

    public static void gotoSetPayPasswordActivity(Context context) {
        Intent intent = new Intent(context, SetPayPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        new SetPayPasswordPresenterImpl((PayPasswordContract.IView) loadBaseFragment(SetPayFragment.newInstance()));
    }

}
