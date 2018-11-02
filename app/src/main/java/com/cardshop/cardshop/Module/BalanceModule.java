package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.BalanceService;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class BalanceModule extends BaseModule {
    /**
     * lgId : 312
     * lgMemberId : 3899
     * lgDesc : 50元电信充值卡
     * lgAddTime : 1969390
     * lgAvAmount : 47.5
     * lgOrderSn : 1539567674408
     * lgStatus : false
     * lgGoodsId : null
     * lgGoodsNum : 0
     * lgType : 1
     * lgPdcId : null
     * lgPdcBankName : null
     * lgPdcBankNo : null
     * lgPdcBankUser : null
     * lgPdcBankAddress : null
     */

    private String lgId;
    private String lgMemberId;
    private String lgDesc;
    private String lgAddTime;
    private double lgAvAmount;
    private String lgOrderSn;
    private String lgStatus;
    private String lgGoodsId;
    private String lgGoodsNum;
    private String lgType;
    private String lgPdcId;
    private String lgPdcBankName;
    private String lgPdcBankNo;
    private String lgPdcBankUser;
    private String lgPdcBankAddress;

    public static void widthDraw(String amount, String cardId, Callback<ResponseData<BaseModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        BalanceService service = retrofit.create(BalanceService.class);
        service.withDraw(UserModule.getCurrentUser().getMember().getMemberId(), amount, cardId)
                .enqueue(callback);
    }

    public static void getBalanceDetailList(String pageNum, String pageSize, Callback<ResponseData<List<BalanceModule>>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        BalanceService service = retrofit.create(BalanceService.class);
        service.getDetailList(UserModule.getCurrentUser().getMember().getMemberId(), pageNum, pageSize)
                .enqueue(callback);

    }

    public String getLgId() {
        return lgId;
    }

    public void setLgId(String lgId) {
        this.lgId = lgId;
    }

    public String getLgMemberId() {
        return lgMemberId;
    }

    public void setLgMemberId(String lgMemberId) {
        this.lgMemberId = lgMemberId;
    }

    public String getLgDesc() {
        return lgDesc;
    }

    public void setLgDesc(String lgDesc) {
        this.lgDesc = lgDesc;
    }

    public String getLgAddTime() {
        return lgAddTime;
    }

    public void setLgAddTime(String lgAddTime) {
        this.lgAddTime = lgAddTime;
    }

    public double getLgAvAmount() {
        return lgAvAmount;
    }

    public void setLgAvAmount(double lgAvAmount) {
        this.lgAvAmount = lgAvAmount;
    }

    public String getLgOrderSn() {
        return lgOrderSn;
    }

    public void setLgOrderSn(String lgOrderSn) {
        this.lgOrderSn = lgOrderSn;
    }

    public String getLgStatus() {
        return lgStatus;
    }

    public void setLgStatus(String lgStatus) {
        this.lgStatus = lgStatus;
    }

    public String getLgGoodsId() {
        return lgGoodsId;
    }

    public void setLgGoodsId(String lgGoodsId) {
        this.lgGoodsId = lgGoodsId;
    }

    public String getLgGoodsNum() {
        return lgGoodsNum;
    }

    public void setLgGoodsNum(String lgGoodsNum) {
        this.lgGoodsNum = lgGoodsNum;
    }

    public String getLgType() {
        return lgType;
    }

    public void setLgType(String lgType) {
        this.lgType = lgType;
    }

    public String getLgPdcId() {
        return lgPdcId;
    }

    public void setLgPdcId(String lgPdcId) {
        this.lgPdcId = lgPdcId;
    }

    public String getLgPdcBankName() {
        return lgPdcBankName;
    }

    public void setLgPdcBankName(String lgPdcBankName) {
        this.lgPdcBankName = lgPdcBankName;
    }

    public String getLgPdcBankNo() {
        return lgPdcBankNo;
    }

    public void setLgPdcBankNo(String lgPdcBankNo) {
        this.lgPdcBankNo = lgPdcBankNo;
    }

    public String getLgPdcBankUser() {
        return lgPdcBankUser;
    }

    public void setLgPdcBankUser(String lgPdcBankUser) {
        this.lgPdcBankUser = lgPdcBankUser;
    }

    public String getLgPdcBankAddress() {
        return lgPdcBankAddress;
    }

    public void setLgPdcBankAddress(String lgPdcBankAddress) {
        this.lgPdcBankAddress = lgPdcBankAddress;
    }
}
