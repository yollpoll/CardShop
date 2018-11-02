package com.cardshop.cardshop.Base;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;

import com.cardshop.cardshop.Utils.QiyuImageUtils;
import com.cardshop.cardshop.Utils.SPUtiles;
import com.cardshop.cardshop.View.Activity.GestureActivity;
import com.cardshop.framework.Base.SpqApplication;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class BaseApplication extends SpqApplication {
    //网易七鱼
    public static String KEY_QIYU = "3b5499a836612be5c1aca05cced47a07";
    //微信
    public static final String APP_ID = "wx2df25a1c114139a9";
    public static final String SECRET = "53addccb62fe4ad49ee416e3b1a6c908";
    private IWXAPI iwxapi;

    private int mFinalCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        initCamera();
        initQiyu();
        regToWx();
        registerAliveCallBack();
    }

    public void initCamera() {
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    public IWXAPI getIwxapi() {
        return iwxapi;
    }

    private void regToWx() {
        iwxapi = WXAPIFactory.createWXAPI(this, APP_ID, true);
        iwxapi.registerApp(APP_ID);
    }

    // 如果返回值为null，则全部使用默认参数。
    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        return options;
    }

    private void initQiyu() {
        // appKey 可以在七鱼管理系统->设置->APP接入 页面找到
        Unicorn.init(this, KEY_QIYU, options(), new QiyuImageUtils(this));
    }

    private void registerAliveCallBack() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                mFinalCount++;
                //如果mFinalCount ==1，说明是从后台到前台
                if (mFinalCount == 1) {
                    //说明从后台回到了前台
                    if (SPUtiles.isOpenGesture()) {
                        GestureActivity.gotoGestureActivity(getApplicationContext(), false);
                    }
//                    SplashActivity.gotoSplashActivity(getApplicationContext());
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                mFinalCount--;
                //如果mFinalCount ==0，说明是前台到后台
                if (mFinalCount == 0) {
                    //说明从前台回到了后台
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
