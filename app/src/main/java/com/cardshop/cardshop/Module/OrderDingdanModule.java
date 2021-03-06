package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.OrderService;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class OrderDingdanModule extends BaseModule {

    private String addTime;
    private String allPrice;
    private String buyerId;
    private String cardStatus;
    private String goodsImage;
    private String goodsName;
    private String goodsNum;
    private String goodsPrice;
    private String ID;
    private String orderSn;
    private String sellId;
    private String sellerName;
    private String totalPrice;
    private String zhuanmaiId;

    public static void getOrderDingDan(String memberId, String pageNum, String pageSize,
                                       Callback<ResponseData<List<OrderDingdanModule>>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        OrderService service = retrofit.create(OrderService.class);
        service.getOrderDingdan(memberId, pageNum, pageSize).enqueue(callback);

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

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getZhuanmaiId() {
        return zhuanmaiId;
    }

    public void setZhuanmaiId(String zhuanmaiId) {
        this.zhuanmaiId = zhuanmaiId;
    }

    public static String getStatusName(String status) {
        switch (status) {
            case "0":
                return "已取消";
            case "10":
                return "未付款";
            case "20":
                return "已付款";
            case "30":
                return "交易完成";
            default:
                return "未知";
        }
    }
}
