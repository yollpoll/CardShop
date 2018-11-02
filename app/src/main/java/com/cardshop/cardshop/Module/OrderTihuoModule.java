package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.OrderService;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class OrderTihuoModule extends BaseModule {
    private String addTime;
    private String cardStatus;
    private String expressCode;
    private String goodsId;
    private String goodsName;
    private String goodsNum;
    private String goodsImage;
    private String goodsPrice;
    private String ID;
    private String memberAvatar;
    private String memberId;
    private String memberName;
    private String phone;
    private String prizeId;
    private String tihuoCode;
    private boolean isShoppingCard;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public boolean isShoppingCard() {
        return isShoppingCard;
    }

    public void setShoppingCard(boolean shoppingCard) {
        isShoppingCard = shoppingCard;
    }

    public static void getGoods(OrderYigouModule goodsModule, String goodsNum, String pohone, String trueName, String area, Callback<ResponseData<BaseModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        OrderService service = retrofit.create(OrderService.class);
        service.applyGetGoods(UserModule.getCurrentUser().getMember().getMemberId(), UserModule.getCurrentUser().getMember().getMemberName(),
                UserModule.getAvatarName(), goodsModule.getZhuanmaiId(), goodsModule.getGoodsName(), goodsNum, goodsModule.getGoodsPrice(),
                pohone, trueName, area, goodsModule.getId()).enqueue(callback);
    }

    public static void getDataList(String gcName, String pageNum, String pageSize, Callback<ResponseData<List<OrderTihuoModule>>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        OrderService service = retrofit.create(OrderService.class);
        service.getTihuoList(UserModule.getCurrentUser().getMember().getMemberId(), gcName, pageNum, pageSize)
                .enqueue(callback);
    }

}
