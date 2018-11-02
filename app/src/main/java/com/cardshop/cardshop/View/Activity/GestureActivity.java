package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.Contract.GestureContract;
import com.cardshop.cardshop.PresenterImpl.GesturePresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.GestureFragment;

public class GestureActivity extends BaseActivity {
    private boolean isSetPasw = true;

    public static void gotoGestureActivity(Context context, boolean isSetPsw) {
        Intent intent = new Intent(context, GestureActivity.class);
        intent.putExtra("psw", isSetPsw);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        isSetPasw = getIntent().getBooleanExtra("psw", true);
        new GesturePresenterImpl((GestureContract.IView) loadBaseFragment(GestureFragment.newInstance()), isSetPasw);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isSetPasw) {
            return super.onKeyDown(keyCode, event);
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;//不执行父类点击事件
        }
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }
}
