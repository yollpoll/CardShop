package com.cardshop.cardshop.Http;

public class API {
    //飞鸽传书
    public static final String FG_HEAD_URL = "http://api.feige.ee/";
    public static final String FG_MSG_URL = "SmsService/Template";
    //APP
    public static final String HEAD_URL = "http://www.s.kinfinger.com/";
    public static final String LOGIN = "mobile/index.php?act=connect&op=app_login";
    public static final String BANNER = "mobile/index.php?act=goods&op=get_banner";
    public static final String ANNOUNCEMENT = "mobile/index.php?act=goods&op=get_home_notice";
    public static final String GOODS = "mobile/index.php?act=goods&op=home_goods";
    public static final String VERTIFY_CODE="mobile/index.php?act=auto_register_login&op=app_send_code";
    public static final String REGISTER="mobile/index.php?act=auto_register_login&op=app_register";
}
