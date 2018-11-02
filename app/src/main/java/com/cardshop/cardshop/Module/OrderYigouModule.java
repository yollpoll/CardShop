package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.OrderService;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class OrderYigouModule extends BaseModule {

    /**
     * id : 79
     * zhuanmaiId : 16
     * sellId : 871
     * sellerName :
     * goodsName : 100元联通充值卡
     * goodsNum : 1
     * goodsPrice : 100
     * cardStatus : 30
     * goodsImage : http://www.s.kinfinger.com/wap/images/lt100.png
     * addTime : 1933310
     * allPrice : 95
     * orderSn : 1539531602861
     * buyerId : 3899
     * totalPrice : 95
     */

    private String id;
    private String zhuanmaiId;
    private String sellId;
    private String sellerName;
    private String goodsName;
    private String goodsNum;
    private String goodsPrice;
    private String cardStatus;
    private String goodsImage;
    private String addTime;
    private String allPrice;
    private String orderSn;
    private String buyerId;
    private String totalPrice;

    public static void getOrderYigou(String gcName, String pageNum, String pageSize, Callback<ResponseData<List<OrderYigouModule>>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        OrderService service = retrofit.create(OrderService.class);
        service.getOrderYigou(UserModule.getCurrentUser().getMember().getMemberId(), gcName, pageNum, pageSize)
                .enqueue(callback);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZhuanmaiId() {
        return zhuanmaiId;
    }

    public void setZhuanmaiId(String zhuanmaiId) {
        this.zhuanmaiId = zhuanmaiId;
    }

    public String getSellId() {
        return sellId;
    }

    public void setSellId(String sellId) {
        this.sellId = sellId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
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

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
