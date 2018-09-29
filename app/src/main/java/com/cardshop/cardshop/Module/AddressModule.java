package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.ResponseData;

import java.util.List;

import retrofit2.Callback;

public class AddressModule extends BaseModule {
    private String true_name;
    private String area_info;
    private String address;
    private String mob_phone;
    private String is_default;
    private String address_id;

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public String getArea_info() {
        return area_info;
    }

    public void setArea_info(String area_info) {
        this.area_info = area_info;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMob_phone() {
        return mob_phone;
    }

    public void setMob_phone(String mob_phone) {
        this.mob_phone = mob_phone;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public static void getAddressList(Callback<ResponseData<List<AddressModule>>> callback) {
//        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
//        AddressService service = retrofit.create(AddressService.class);
//        Call<ResponseData<List<AddressModule>>> call = service.getAddress(SPUtiles.getUser().getMid(),SPUtiles.getToken());
//        call.enqueue(callback);
    }

}
