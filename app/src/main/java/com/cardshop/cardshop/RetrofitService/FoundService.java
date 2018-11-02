package com.cardshop.cardshop.RetrofitService;

import com.cardshop.cardshop.Http.API;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.Module.FoundGameModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoundService {
    @GET(API.FOUND_LIST)
    Call<ResponseData<List<FoundGameModule>>> getFoundList();
}
