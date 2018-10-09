package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.UserService;
import com.cardshop.cardshop.Utils.SPUtiles;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class UserModule extends BaseModule {
    public static final int CODE_VERTIFY_FAILED = 2;
    public static final int CODE_REGISETERED = 1;
    /**
     * cardNum : 1
     * member : {"memberId":860,"memberName":"哈哈哈哈哈好喜","memberTruename":"","memberAvatar":"http://www.s.kinfinger.com/data/upload/shop/avatar/avatar_860.jpg","memberSex":true,"memberBirthday":null,"memberPasswd":"e10adc3949ba59abbe56e057f20f883e","memberPaypwd":"b89f4075611bb0eb64e13f4bd1f20288","memberEmail":"14520000@qq.com","memberEmailBind":0,"memberMobile":"15239198642","memberMobileBind":1,"memberQq":null,"memberWw":null,"memberLoginNum":1,"memberTime":"1523681328","memberLoginTime":"1523681328","memberOldLoginTime":"1523681328","memberLoginIp":"171.13.94.129","memberOldLoginIp":"171.13.94.129","memberQqopenid":null,"memberQqinfo":null,"memberSinaopenid":null,"memberSinainfo":null,"weixinUnionid":"ofBz_1cLoN4V98oq-X4ePoNxh_nU","weixinInfo":null,"memberPoints":20,"availablePredeposit":10189,"freezePredeposit":0,"availableRcBalance":0,"freezeRcBalance":0,"informAllow":false,"isBuy":false,"isAllowtalk":false,"memberState":true,"memberSnsvisitnum":0,"memberAreaid":null,"memberCityid":0,"memberProvinceid":0,"memberAreainfo":"北京\t北京市\t西城区","memberPrivacy":null,"memberQuicklink":null,"memberExppoints":0,"inviterId":null,"memberWxopenid":null,"myGoldBean":106160,"authCode":"123456","memberIdCard":null,"isVerified":null}
     */

    private int cardNum;
    private MemberBean member;


    public static void login(String userName, String password, Callback<ResponseData<UserModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        UserService service = retrofit.create(UserService.class);
        Call<ResponseData<UserModule>> userModuleClass = service.login(userName, password);
        userModuleClass.enqueue(callback);
    }

    public static void authLogin(String openId, String nickName, String avatar, String authType
            , Callback<ResponseData<UserModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        UserService service = retrofit.create(UserService.class);
        Call<ResponseData<UserModule>> call = service.authLogin(openId, nickName, avatar, authType);
        call.enqueue(callback);
    }

    public static void changePassword(String phone, String smsCode, String password, Callback<ResponseData<UserModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        UserService service = retrofit.create(UserService.class);
        Call<ResponseData<UserModule>> call = service.changePassword(phone, smsCode, password);
        call.enqueue(callback);
    }

    public static void register(String phone, String password, String smsCode, String openId, Callback<ResponseData<UserModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        UserService service = retrofit.create(UserService.class);
        Call<ResponseData<UserModule>> call = service.register(phone, password, smsCode, openId);
        call.enqueue(callback);
    }

    public static void getMsgCode(String phone, Callback<ResponseData<BaseModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        UserService service = retrofit.create(UserService.class);
        Call<ResponseData<BaseModule>> call = service.sendMsg(phone);
        call.enqueue(callback);
    }

    public static void vertifySms(String phone, String sms, Callback<ResponseData<BaseModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        UserService service = retrofit.create(UserService.class);
        Call<ResponseData<BaseModule>> call = service.vertifySms(phone, sms);
        call.enqueue(callback);

    }

    public static void changeUserName(String userName, Callback<ResponseData<BaseModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        UserService service = retrofit.create(UserService.class);
        Call<ResponseData<BaseModule>> call = service.changeName(getCurrentUser().getMember().getMemberId(), userName);
        call.enqueue(callback);

    }
    public static void getUserInfo(Callback<ResponseData<UserModule>> callback){
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        UserService service = retrofit.create(UserService.class);
        Call<ResponseData<UserModule>> call = service.getUserInfo(getCurrentUser().getMember().getMemberId());
        call.enqueue(callback);
    }

    public static void saveToLocal(UserModule userModule) {
        SPUtiles.saveUser(userModule);
    }

    public static UserModule getCurrentUser() {
        return null != SPUtiles.getUser() ? SPUtiles.getUser() : new UserModule();
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public static class MemberBean {
        /**
         * memberId : 860
         * memberName : 哈哈哈哈哈好喜
         * memberTruename :
         * memberAvatar : http://www.s.kinfinger.com/data/upload/shop/avatar/avatar_860.jpg
         * memberSex : true
         * memberBirthday : null
         * memberPasswd : e10adc3949ba59abbe56e057f20f883e
         * memberPaypwd : b89f4075611bb0eb64e13f4bd1f20288
         * memberEmail : 14520000@qq.com
         * memberEmailBind : 0
         * memberMobile : 15239198642
         * memberMobileBind : 1
         * memberQq : null
         * memberWw : null
         * memberLoginNum : 1
         * memberTime : 1523681328
         * memberLoginTime : 1523681328
         * memberOldLoginTime : 1523681328
         * memberLoginIp : 171.13.94.129
         * memberOldLoginIp : 171.13.94.129
         * memberQqopenid : null
         * memberQqinfo : null
         * memberSinaopenid : null
         * memberSinainfo : null
         * weixinUnionid : ofBz_1cLoN4V98oq-X4ePoNxh_nU
         * weixinInfo : null
         * memberPoints : 20
         * availablePredeposit : 10189
         * freezePredeposit : 0
         * availableRcBalance : 0
         * freezeRcBalance : 0
         * informAllow : false
         * isBuy : false
         * isAllowtalk : false
         * memberState : true
         * memberSnsvisitnum : 0
         * memberAreaid : null
         * memberCityid : 0
         * memberProvinceid : 0
         * memberAreainfo : 北京	北京市	西城区
         * memberPrivacy : null
         * memberQuicklink : null
         * memberExppoints : 0
         * inviterId : null
         * memberWxopenid : null
         * myGoldBean : 106160
         * authCode : 123456
         * memberIdCard : null
         * isVerified : null
         */

        private String memberId;
        private String memberName;
        private String memberTruename;
        private String memberAvatar;
        private boolean memberSex;
        private String memberBirthday;
        private String memberPasswd;
        private String memberPaypwd;
        private String memberEmail;
        private int memberEmailBind;
        private String memberMobile;
        private int memberMobileBind;
        private String memberQq;
        private String memberWw;
        private int memberLoginNum;
        private String memberTime;
        private String memberLoginTime;
        private String memberOldLoginTime;
        private String memberLoginIp;
        private String memberOldLoginIp;
        private String memberQqopenid;
        private String memberQqinfo;
        private String memberSinaopenid;
        private String memberSinainfo;
        private String weixinUnionid;
        private String weixinInfo;
        private int memberPoints;
        private int availablePredeposit;
        private int freezePredeposit;
        private int availableRcBalance;
        private int freezeRcBalance;
        private boolean informAllow;
        private boolean isBuy;
        private boolean isAllowtalk;
        private boolean memberState;
        private int memberSnsvisitnum;
        private String memberAreaid;
        private int memberCityid;
        private int memberProvinceid;
        private String memberAreainfo;
        private String memberPrivacy;
        private String memberQuicklink;
        private int memberExppoints;
        private String inviterId;
        private String memberWxopenid;
        private int myGoldBean;
        private String authCode;
        private String memberIdCard;
        private String isVerified;

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getMemberTruename() {
            return memberTruename;
        }

        public void setMemberTruename(String memberTruename) {
            this.memberTruename = memberTruename;
        }

        public String getMemberAvatar() {
            return memberAvatar;
        }

        public void setMemberAvatar(String memberAvatar) {
            this.memberAvatar = memberAvatar;
        }

        public boolean isMemberSex() {
            return memberSex;
        }

        public void setMemberSex(boolean memberSex) {
            this.memberSex = memberSex;
        }

        public String getMemberBirthday() {
            return memberBirthday;
        }

        public void setMemberBirthday(String memberBirthday) {
            this.memberBirthday = memberBirthday;
        }

        public String getMemberPasswd() {
            return memberPasswd;
        }

        public void setMemberPasswd(String memberPasswd) {
            this.memberPasswd = memberPasswd;
        }

        public String getMemberPaypwd() {
            return memberPaypwd;
        }

        public void setMemberPaypwd(String memberPaypwd) {
            this.memberPaypwd = memberPaypwd;
        }

        public String getMemberEmail() {
            return memberEmail;
        }

        public void setMemberEmail(String memberEmail) {
            this.memberEmail = memberEmail;
        }

        public int getMemberEmailBind() {
            return memberEmailBind;
        }

        public void setMemberEmailBind(int memberEmailBind) {
            this.memberEmailBind = memberEmailBind;
        }

        public String getMemberMobile() {
            return memberMobile;
        }

        public void setMemberMobile(String memberMobile) {
            this.memberMobile = memberMobile;
        }

        public int getMemberMobileBind() {
            return memberMobileBind;
        }

        public void setMemberMobileBind(int memberMobileBind) {
            this.memberMobileBind = memberMobileBind;
        }

        public String getMemberQq() {
            return memberQq;
        }

        public void setMemberQq(String memberQq) {
            this.memberQq = memberQq;
        }

        public String getMemberWw() {
            return memberWw;
        }

        public void setMemberWw(String memberWw) {
            this.memberWw = memberWw;
        }

        public int getMemberLoginNum() {
            return memberLoginNum;
        }

        public void setMemberLoginNum(int memberLoginNum) {
            this.memberLoginNum = memberLoginNum;
        }

        public String getMemberTime() {
            return memberTime;
        }

        public void setMemberTime(String memberTime) {
            this.memberTime = memberTime;
        }

        public String getMemberLoginTime() {
            return memberLoginTime;
        }

        public void setMemberLoginTime(String memberLoginTime) {
            this.memberLoginTime = memberLoginTime;
        }

        public String getMemberOldLoginTime() {
            return memberOldLoginTime;
        }

        public void setMemberOldLoginTime(String memberOldLoginTime) {
            this.memberOldLoginTime = memberOldLoginTime;
        }

        public String getMemberLoginIp() {
            return memberLoginIp;
        }

        public void setMemberLoginIp(String memberLoginIp) {
            this.memberLoginIp = memberLoginIp;
        }

        public String getMemberOldLoginIp() {
            return memberOldLoginIp;
        }

        public void setMemberOldLoginIp(String memberOldLoginIp) {
            this.memberOldLoginIp = memberOldLoginIp;
        }

        public String getMemberQqopenid() {
            return memberQqopenid;
        }

        public void setMemberQqopenid(String memberQqopenid) {
            this.memberQqopenid = memberQqopenid;
        }

        public String getMemberQqinfo() {
            return memberQqinfo;
        }

        public void setMemberQqinfo(String memberQqinfo) {
            this.memberQqinfo = memberQqinfo;
        }

        public String getMemberSinaopenid() {
            return memberSinaopenid;
        }

        public void setMemberSinaopenid(String memberSinaopenid) {
            this.memberSinaopenid = memberSinaopenid;
        }

        public String getMemberSinainfo() {
            return memberSinainfo;
        }

        public void setMemberSinainfo(String memberSinainfo) {
            this.memberSinainfo = memberSinainfo;
        }

        public String getWeixinUnionid() {
            return weixinUnionid;
        }

        public void setWeixinUnionid(String weixinUnionid) {
            this.weixinUnionid = weixinUnionid;
        }

        public String getWeixinInfo() {
            return weixinInfo;
        }

        public void setWeixinInfo(String weixinInfo) {
            this.weixinInfo = weixinInfo;
        }

        public int getMemberPoints() {
            return memberPoints;
        }

        public void setMemberPoints(int memberPoints) {
            this.memberPoints = memberPoints;
        }

        public int getAvailablePredeposit() {
            return availablePredeposit;
        }

        public void setAvailablePredeposit(int availablePredeposit) {
            this.availablePredeposit = availablePredeposit;
        }

        public int getFreezePredeposit() {
            return freezePredeposit;
        }

        public void setFreezePredeposit(int freezePredeposit) {
            this.freezePredeposit = freezePredeposit;
        }

        public int getAvailableRcBalance() {
            return availableRcBalance;
        }

        public void setAvailableRcBalance(int availableRcBalance) {
            this.availableRcBalance = availableRcBalance;
        }

        public int getFreezeRcBalance() {
            return freezeRcBalance;
        }

        public void setFreezeRcBalance(int freezeRcBalance) {
            this.freezeRcBalance = freezeRcBalance;
        }

        public boolean isInformAllow() {
            return informAllow;
        }

        public void setInformAllow(boolean informAllow) {
            this.informAllow = informAllow;
        }

        public boolean isBuy() {
            return isBuy;
        }

        public void setBuy(boolean buy) {
            isBuy = buy;
        }

        public boolean isAllowtalk() {
            return isAllowtalk;
        }

        public void setAllowtalk(boolean allowtalk) {
            isAllowtalk = allowtalk;
        }

        public boolean isMemberState() {
            return memberState;
        }

        public void setMemberState(boolean memberState) {
            this.memberState = memberState;
        }

        public int getMemberSnsvisitnum() {
            return memberSnsvisitnum;
        }

        public void setMemberSnsvisitnum(int memberSnsvisitnum) {
            this.memberSnsvisitnum = memberSnsvisitnum;
        }

        public String getMemberAreaid() {
            return memberAreaid;
        }

        public void setMemberAreaid(String memberAreaid) {
            this.memberAreaid = memberAreaid;
        }

        public int getMemberCityid() {
            return memberCityid;
        }

        public void setMemberCityid(int memberCityid) {
            this.memberCityid = memberCityid;
        }

        public int getMemberProvinceid() {
            return memberProvinceid;
        }

        public void setMemberProvinceid(int memberProvinceid) {
            this.memberProvinceid = memberProvinceid;
        }

        public String getMemberAreainfo() {
            return memberAreainfo;
        }

        public void setMemberAreainfo(String memberAreainfo) {
            this.memberAreainfo = memberAreainfo;
        }

        public String getMemberPrivacy() {
            return memberPrivacy;
        }

        public void setMemberPrivacy(String memberPrivacy) {
            this.memberPrivacy = memberPrivacy;
        }

        public String getMemberQuicklink() {
            return memberQuicklink;
        }

        public void setMemberQuicklink(String memberQuicklink) {
            this.memberQuicklink = memberQuicklink;
        }

        public int getMemberExppoints() {
            return memberExppoints;
        }

        public void setMemberExppoints(int memberExppoints) {
            this.memberExppoints = memberExppoints;
        }

        public String getInviterId() {
            return inviterId;
        }

        public void setInviterId(String inviterId) {
            this.inviterId = inviterId;
        }

        public String getMemberWxopenid() {
            return memberWxopenid;
        }

        public void setMemberWxopenid(String memberWxopenid) {
            this.memberWxopenid = memberWxopenid;
        }

        public int getMyGoldBean() {
            return myGoldBean;
        }

        public void setMyGoldBean(int myGoldBean) {
            this.myGoldBean = myGoldBean;
        }

        public String getAuthCode() {
            return authCode;
        }

        public void setAuthCode(String authCode) {
            this.authCode = authCode;
        }

        public String getMemberIdCard() {
            return memberIdCard;
        }

        public void setMemberIdCard(String memberIdCard) {
            this.memberIdCard = memberIdCard;
        }

        public String getIsVerified() {
            return isVerified;
        }

        public void setIsVerified(String isVerified) {
            this.isVerified = isVerified;
        }
    }
}
