package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.AddressModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AddressService {
    @FormUrlEncoded
    @POST(API.ADDRESS)
    Call<ResponseData<List<AddressModule>>> getAddress(@Field("mid") String mid,@Field("token") String token);
}
