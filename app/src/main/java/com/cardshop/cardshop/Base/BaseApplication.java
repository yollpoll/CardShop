package com.cardshop.cardshop.Base;

import com.cardshop.cardshop.Utils.QiyuImageUtils;
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
    public static final String SECRET="53addccb62fe4ad49ee416e3b1a6c908";
    private IWXAPI iwxapi;

    @Override
    public void onCreate() {
        super.onCreate();
        initQiyu();
        regToWx();
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
}
