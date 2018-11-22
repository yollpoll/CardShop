package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cardshop.cardshop.Listener.CountDownListener;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.Utils.RxUtils;
import com.cardshop.cardshop.Utils.SPUtiles;

public class SplashActivity extends AppCompatActivity {

    public static void gotoSplashActivity(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//        setNoStatusBar();
        hideBottomUIMenu();
        RxUtils.startCountDown(1000, 1000, new CountDownListener() {
            @Override
            public void onCountDown(int count) {
                if (SPUtiles.isOpenGesture()) {
                    //手势密码打开以后不做处理
                    return;
                }
                if (count == 0) {
                    if (null == UserModule.getCurrentUser().getMember()) {
                        LoginActivity.gotoLoginActivity(SplashActivity.this);
                    } else {
                        MainActivity.gotoMainActivity(SplashActivity.this);
                    }
                    SplashActivity.this.finish();
                }
            }

        });
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
