package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.CardService;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class CardModule extends BaseModule {

    /**
     * pdcId : 128
     * pdcSn : 123
     * pdcMemberId : 860
     * pdcMemberName : ..
     * pdcAmount : 1
     * pdcBankName : 中国银行
     * pdcBankNo : 6228482715698741
     * pdcBankUser : 胡爆炒
     * pdcAddTime : 1527750839
     * pdcPaymentTime : null
     * pdcPaymentState : 0
     * pdcPaymentAdmin : null
     * pdcProvince : 北京市
     * pdcCity : 朝阳区
     * pdcBankAddress : 呵呵
     * pdcMobile : null
     * pdcIdCard : null
     */

    private long pdcId;
    private long pdcSn;
    private long pdcMemberId;
    private String pdcMemberName;
    private long pdcAmount;
    private String pdcBankName;
    private String pdcBankNo;
    private String pdcBankUser;
    private long pdcAddTime;
    private Object pdcPaymentTime;
    private long pdcPaymentState;
    private Object pdcPaymentAdmin;
    private String pdcProvince;
    private String pdcCity;
    private String pdcBankAddress;
    private Object pdcMobile;
    private Object pdcIdCard;

    public static void getCardList(String memberId, Callback<ResponseData<List<CardModule>>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        CardService service = retrofit.create(CardService.class);
        service.getCardList(memberId).enqueue(callback);
    }

    public static void addCard(String memberId, String pdcBankUser, String pdcBankNo, String pdcBankName,
                               String pdcBankAddress, String idCardNo, String phone, Callback<ResponseData<AddCardModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        CardService service = retrofit.create(CardService.class);
        service.addCard(memberId, pdcBankUser, pdcBankNo, pdcBankName, pdcBankAddress, idCardNo, phone).enqueue(callback);
    }

    public static void delCard(String id, Callback<ResponseData<BaseModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        CardService service = retrofit.create(CardService.class);
        service.delCard(id).enqueue(callback);
    }

    public long getPdcId() {
        return pdcId;
    }

    public void setPdcId(long pdcId) {
        this.pdcId = pdcId;
    }

    public long getPdcSn() {
        return pdcSn;
    }

    public void setPdcSn(long pdcSn) {
        this.pdcSn = pdcSn;
    }

    public long getPdcMemberId() {
        return pdcMemberId;
    }

    public void setPdcMemberId(long pdcMemberId) {
        this.pdcMemberId = pdcMemberId;
    }

    public String getPdcMemberName() {
        return pdcMemberName;
    }

    public void setPdcMemberName(String pdcMemberName) {
        this.pdcMemberName = pdcMemberName;
    }

    public long getPdcAmount() {
        return pdcAmount;
    }

    public void setPdcAmount(long pdcAmount) {
        this.pdcAmount = pdcAmount;
    }

    public String getPdcBankName() {
        return pdcBankName;
    }

    public void setPdcBankName(String pdcBankName) {
        this.pdcBankName = pdcBankName;
    }

    public String getPdcBankNo() {
        return pdcBankNo;
    }

    public void setPdcBankNo(String pdcBankNo) {
        this.pdcBankNo = pdcBankNo;
    }

    public String getPdcBankUser() {
        return pdcBankUser;
    }

    public void setPdcBankUser(String pdcBankUser) {
        this.pdcBankUser = pdcBankUser;
    }

    public long getPdcAddTime() {
        return pdcAddTime;
    }

    public void setPdcAddTime(long pdcAddTime) {
        this.pdcAddTime = pdcAddTime;
    }

    public Object getPdcPaymentTime() {
        return pdcPaymentTime;
    }

    public void setPdcPaymentTime(Object pdcPaymentTime) {
        this.pdcPaymentTime = pdcPaymentTime;
    }

    public long getPdcPaymentState() {
        return pdcPaymentState;
    }

    public void setPdcPaymentState(long pdcPaymentState) {
        this.pdcPaymentState = pdcPaymentState;
    }

    public Object getPdcPaymentAdmin() {
        return pdcPaymentAdmin;
    }

    public void setPdcPaymentAdmin(Object pdcPaymentAdmin) {
        this.pdcPaymentAdmin = pdcPaymentAdmin;
    }

    public String getPdcProvince() {
        return pdcProvince;
    }

    public void setPdcProvince(String pdcProvince) {
        this.pdcProvince = pdcProvince;
    }

    public String getPdcCity() {
        return pdcCity;
    }

    public void setPdcCity(String pdcCity) {
        this.pdcCity = pdcCity;
    }

    public String getPdcBankAddress() {
        return pdcBankAddress;
    }

    public void setPdcBankAddress(String pdcBankAddress) {
        this.pdcBankAddress = pdcBankAddress;
    }

    public Object getPdcMobile() {
        return pdcMobile;
    }

    public void setPdcMobile(Object pdcMobile) {
        this.pdcMobile = pdcMobile;
    }

    public Object getPdcIdCard() {
        return pdcIdCard;
    }

    public void setPdcIdCard(Object pdcIdCard) {
        this.pdcIdCard = pdcIdCard;
    }

    public enum CardType {
        //中国银行，建设银行，招商银行，工商银行，交通银行，农业银行，通用
        ZGYH("中国银行", 0), JSYH("建设银行", 1), ZSYH("招商银行", 2),
        GSYH("工商银行", 3), JTYH("交通银行", 4), NYYH("农业银行", 5), TY("通用", 6);
        private String name;
        private int index;

        // 构造方法
        CardType(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static CardType getType(String name) {
            for (CardType c : CardType.values()) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
            return null;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

}
