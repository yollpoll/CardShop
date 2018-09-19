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
    private List<Entity> mobile;

    public List<Entity> getMobile() {
        return mobile;
    }

    public void setMobile(List<Entity> mobile) {
        this.mobile = mobile;
    }

    public static class Entity {
        private String id;
        private String goods_name;
        private String member_name;
        private String goods_price;
        private String all_price;
        private String zhuanmai_rate;
        private String goods_num;
        private String add_time;
        private String goods_image;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getAll_price() {
            return all_price;
        }

        public void setAll_price(String all_price) {
            this.all_price = all_price;
        }

        public String getZhuanmai_rate() {
            return zhuanmai_rate;
        }

        public void setZhuanmai_rate(String zhuanmai_rate) {
            this.zhuanmai_rate = zhuanmai_rate;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static void getGoods(int code, Callback<ResponseData<GoodsModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        MainService service = retrofit.create(MainService.class);
        Call<ResponseData<GoodsModule>> call = service.getGoods(code);
        call.enqueue(callback);
    }
}
