package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.Contract.SetPayPassword2Contract;
import com.cardshop.cardshop.PresenterImpl.SetPayPassword2PresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.SetPayPassword2Fragment;

public class SetPayPassword2Activity extends BaseActivity {
    public static void gotoSetPayPassword2Activity(Context context) {
        Intent intent = new Intent(context, SetPayPassword2Activity.class);
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
        new SetPayPassword2PresenterImpl((SetPayPassword2Contract.IView) loadBaseFragment(SetPayPassword2Fragment.newInstance()));
    }
}
