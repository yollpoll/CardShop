package com.cardshop.cardshop.Base;

import com.cardshop.cardshop.Utils.QiyuImageUtils;
import com.cardshop.framework.Base.SpqApplication;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;

public class BaseApplication extends SpqApplication {
    public static String FG_ACCOUNT = "13250337986";
    public static String FG_PSD = "b6c875de75910411855d348d0";
    public static String FG_TemplateId = "31124";
    public static String FG_SignId = "44170";

    public static String KEY_QIYU = "3b5499a836612be5c1aca05cced47a07";

    @Override
    public void onCreate() {
        super.onCreate();
        initQiyu();
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
