package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.AddressService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class AddressModule extends BaseModule {
    private String trueName;
    private String areaInfo;
    private String address;
    private String mobPhone;
    private String isDefault;
    private String addressId;
    private String memberId;
    private String areaId;
    private String cityId;
    private String telPhone;

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public static void getAddressList(Callback<ResponseData<List<AddressModule>>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        AddressService service = retrofit.create(AddressService.class);
        Call<ResponseData<List<AddressModule>>> call = service.getAddress(UserModule.getCurrentUser().getMember().getMemberId());
        call.enqueue(callback);
    }

    public static void addAddress(String name, String phone, String area, String address, boolean isDefault,
                                  Callback<ResponseData<AddressModule>> callback) {

        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        AddressService service = retrofit.create(AddressService.class);
        String memberId = UserModule.getCurrentUser().getMember().getMemberId();
        service.addAddress(name, phone, area, address, isDefault ? "1" : "0", memberId).enqueue(callback);
    }

    public static void changeAddress(String addressId, String name, String phone, String area, String address, boolean isDefault,
                                     Callback<ResponseData<AddressModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        AddressService service = retrofit.create(AddressService.class);
        String memberId = UserModule.getCurrentUser().getMember().getMemberId();
        service.changeAddress(addressId, name, phone, area, address, isDefault ? "1" : "0", memberId).enqueue(callback);
    }

    public static void delAddress(String addressId, Callback<ResponseData<BaseModule>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        AddressService service = retrofit.create(AddressService.class);
        service.delAddress(addressId).enqueue(callback);
    }

}
