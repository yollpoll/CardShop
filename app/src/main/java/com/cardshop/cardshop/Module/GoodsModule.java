package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.MainService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class GoodsModule extends BaseModule {


    /**
     * id : 3
     * prizeId : 52
     * memberId : 1009
     * memberName : 13405769219
     * memberAvatar : avator_5.jpg
     * goodsId : 1
     * goodsName : 100元移动充值卡
     * goodsNum : 0
     * goodsPrice : 100
     * zhuanmaiRate : 8.6
     * cardStatus : 40
     * addTime : 1525868135
     * allPrice : 86
     * goodsImage : http://www.s.kinfinger.com/wap/images/yd100.png
     * code : ZM2018051902147
     * afterNum : 1
     */

    private int id;
    private int prizeId;
    private int memberId;
    private String memberName;
    private String memberAvatar;
    private int goodsId;
    private String goodsName;
    private int goodsNum;
    private double goodsPrice;
    private double zhuanmaiRate;
    private String cardStatus;
    private double addTime;
    private double allPrice;
    private String goodsImage;
    private String code;
    private double afterNum;

    public static void getGoods(String gcName, String pageNum, Callback<ResponseData<List<GoodsModule>>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        MainService service = retrofit.create(MainService.class);
        Call<ResponseData<List<GoodsModule>>> call = service.getGoods(gcName, pageNum, 20 + "");
        call.enqueue(callback);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(int prizeId) {
        this.prizeId = prizeId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberAvatar() {
        return memberAvatar;
    }

    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public double getZhuanmaiRate() {
        return zhuanmaiRate;
    }

    public void setZhuanmaiRate(double zhuanmaiRate) {
        this.zhuanmaiRate = zhuanmaiRate;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public double getAddTime() {
        return addTime;
    }

    public void setAddTime(double addTime) {
        this.addTime = addTime;
    }

    public double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(double allPrice) {
        this.allPrice = allPrice;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getAfterNum() {
        return afterNum;
    }

    public void setAfterNum(double afterNum) {
        this.afterNum = afterNum;
    }
}
