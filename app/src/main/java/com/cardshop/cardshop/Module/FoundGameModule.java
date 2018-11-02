package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.FoundService;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class FoundGameModule extends BaseModule {

    /**
     * id : 1
     * name : 猜单双赢金豆
     * url : null
     * cover : http://106.14.184.148:8080/images/cover1.png
     * sortNo : 1
     * isActive : 1
     */

    private String id;
    private String name;
    private String url;
    private String cover;
    private String sortNo;
    private String isActive;

    public static void getFoundList(Callback<ResponseData<List<FoundGameModule>>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        FoundService service = retrofit.create(FoundService.class);
        service.getFoundList().enqueue(callback);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
