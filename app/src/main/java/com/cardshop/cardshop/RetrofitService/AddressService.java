package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.AddressModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddressService {
    @GET(API.ADDRESS)
    Call<ResponseData<List<AddressModule>>> getAddress(@Query("memberId") String mid);

    @FormUrlEncoded
    @POST(API.ADD_ADDRESS)
    Call<ResponseData<AddressModule>> addAddress(@Field("trueName") String trueName, @Field("mobPhone") String mobPhone,
                                                 @Field("areaInfo") String areaInfo, @Field("address") String address,
                                                 @Field("isDefault") String isDefault, @Field("memberId") String memberId);

    @FormUrlEncoded
    @POST(API.ADD_ADDRESS)
    Call<ResponseData<AddressModule>> changeAddress(@Field("addressId") String addressId, @Field("trueName") String trueName,
                                                    @Field("mobPhone") String mobPhone, @Field("areaInfo") String areaInfo,
                                                    @Field("address") String address, @Field("isDefault") String isDefault,
                                                    @Field("memberId") String memberId);

    @FormUrlEncoded
    @POST(API.DEL_ADDRESS)
    Call<ResponseData<BaseModule>> delAddress(@Field("addressId") String addressId);
}
