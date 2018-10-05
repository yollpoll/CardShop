package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.Contract.CardContract;
import com.cardshop.cardshop.PresenterImpl.CardPresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.CardFragment;

public class CardActivity extends BaseActivity {
    public static void gotoCardActivity(Context context) {
        Intent intent = new Intent(context, CardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initData() {
        super.initData();
        new CardPresenterImpl((CardContract.IView) loadBaseFragment(CardFragment.newInstance()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
