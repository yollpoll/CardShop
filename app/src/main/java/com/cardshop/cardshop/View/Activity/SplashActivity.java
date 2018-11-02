package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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

}
