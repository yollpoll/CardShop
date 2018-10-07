package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.AddressModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AddressService {
    @GET(API.ADDRESS)
    Call<ResponseData<List<AddressModule>>> getAddress(@Query("memberId") String mid);
}
