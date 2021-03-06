package com.cardshop.cardshop.Http;

public class API {
    //飞鸽传书
    public static final String FG_HEAD_URL = "http://api.feige.ee/";
    public static final String FG_MSG_URL = "SmsService/Template";
    //微信
    public static final String WX_BASE_URL = "https://api.weixin.qq.com/";
    public static final String WX_GET_TOKEN = "sns/oauth2/access_token";
    public static final String WX_REFRESH_TOKEN = "sns/oauth2/refresh_token";
    public static final String WX_USER_INFO = "sns/userinfo";
    public static final String WX_BIND = "ktt/api/member/wxBind";

    //APP
    public static final String HEAD_URL = "http://106.14.184.148:8080/";
    //首页
    public static final String BANNER = "mobile/index.php?act=goods&op=get_banner";
    public static final String ANNOUNCEMENT = "mobile/index.php?act=goods&op=get_home_notice";
    public static final String GOODS_TYPE = "ktt/api/goods/class/list";
    public static final String GOODS = "ktt/api/goods/list";
    public static final String BANNER_ANNOUNCEMENT = "ktt/api/getBannerAndBulletin";

    //user
    public static final String LOGIN = "ktt/api/member/login";
    public static final String VERTIFY_CODE = "ktt/api/sendSmsCode";
    public static final String REGISTER = "ktt/api/member/register";
    public static final String CHANGE_PASSWORD = "ktt/api/member/modifyPsw";
    public static final String VERTIFY_SMS = "ktt/api/verifySmsCode";
    public static final String AUTH_LOGIN = "ktt/api/member/authLogin";
    public static final String CHANGE_NAME = "ktt/api/member/modifyName";
    public static final String USER_INFO = "ktt/api/member/info";
    public static final String SET_PAY_PASSWORD = "ktt/api/member/setPayPsw";
    public static final String SET_AVATAR = "ktt/api/member/modifyHeadImg";
    public static final String VERTIFY_PAY_PASSWORD = "ktt/api/verifyPayPsw";
    public static final String CHANGE_PHONE = "ktt/api/member/modifyPhone";
    //address
    public static final String ADDRESS = "ktt/api/address/list";
    public static final String ADD_ADDRESS = "ktt/api/address/save";
    public static final String DEL_ADDRESS = "ktt/api/address/delete";
    //card
    public static final String CARDS = "ktt/api/bankcard/list";
    public static final String ADD_CARD = "ktt/api/bankcard/add";
    public static final String DEL_CARD = "ktt/api/bankcard/unbinding";
    //realname
    public static final String REAL_NAME = "ktt/api/member/verified";
    //order
    public static final String ORDER_DINGDAN = "ktt/api/order/list";
    public static final String CREATE_ORDER = "ktt/api/order/commit";
    public static final String PAY_BALANCE = "ktt/api/pay/balance";
    public static final String ORDER_YIGOU = "ktt/api/order/purchased";
    public static final String APPLY_TIHUO = "ktt/api/pickup/apply";
    public static final String TIHUO_LIST = "ktt/api/pickup/list";
    public static final String ZHUANMAI_LIST = "ktt/api/goods/mine";
    public static final String GET_ALI_INFO = "ktt/api/pay/getAliPayOrderStr";
    //balance
    public static final String WITHDRAW = "ktt/api/member/withdrawApply";
    public static final String BALANCE_DETAIL_LIST = "ktt/api/member/balanceDetail";
    //found
    public static final String FOUND_LIST = "ktt/api/game/list";
}
