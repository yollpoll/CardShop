package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.OrderService;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class OrderZhuanmaiModule extends BaseModule {
    private String addTime;
    private String afterNum;
    private String allPrice;
    private String cardStatus;
    private String code;
    private String goodsId;
    private String goodsName;
    private String goodsNum;
    private String goodsImage;
    private String goodsPrice;
    private String ID;
    private String memberAvatar;
    private String memberId;
    private String memberName;
    private String prizeId;
    private String tihuoCode;
    private String zhuanmaiRate;

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAfterNum() {
        return afterNum;
    }

    public void setAfterNum(String afterNum) {
        this.afterNum = afterNum;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMemberAvatar() {
        return memberAvatar;
    }

    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
    }

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

    public String getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
    }

    public String getTihuoCode() {
        return tihuoCode;
    }

    public void setTihuoCode(String tihuoCode) {
        this.tihuoCode = tihuoCode;
    }

    public String getZhuanmaiRate() {
        return zhuanmaiRate;
    }

    public void setZhuanmaiRate(String zhuanmaiRate) {
        this.zhuanmaiRate = zhuanmaiRate;
    }

    public static void getZhuanmaiList(String pageNum, String pageSize, String status, Callback<ResponseData<List<OrderZhuanmaiModule>>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        OrderService service = retrofit.create(OrderService.class);
        service.getZhuanmaiList(UserModule.getCurrentUser().getMember().getMemberId(),
                status, pageNum, pageSize).enqueue(callback);
    }
}
