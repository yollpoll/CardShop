package com.cardshop.cardshop.Module;

import com.cardshop.cardshop.Base.BaseModule;
import com.cardshop.cardshop.Http.HttpTools;
import com.cardshop.cardshop.Http.ResponseData;
import com.cardshop.cardshop.RetrofitService.MainService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class GoodsTypeModule extends BaseModule {

    /**
     * gcId : 1
     * gcName : 移动卡
     * typeId : 1
     * typeName :
     * gcParentId : 0
     * commisRate : 5
     * gcSort : true
     * gcVirtual : 0
     * gcTitle :
     * gcKeywords :
     * gcDescription :
     * gcShow : true
     * status : 0
     * flag : mobile
     */

    private int gcId;
    private String gcName;
    private int typeId;
    private String typeName;
    private int gcParentId;
    private int commisRate;
    private boolean gcSort;
    private int gcVirtual;
    private String gcTitle;
    private String gcKeywords;
    private String gcDescription;
    private boolean gcShow;
    private int status;
    private String flag;

    public int getGcId() {
        return gcId;
    }

    public void setGcId(int gcId) {
        this.gcId = gcId;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getGcParentId() {
        return gcParentId;
    }

    public void setGcParentId(int gcParentId) {
        this.gcParentId = gcParentId;
    }

    public int getCommisRate() {
        return commisRate;
    }

    public void setCommisRate(int commisRate) {
        this.commisRate = commisRate;
    }

    public boolean isGcSort() {
        return gcSort;
    }

    public void setGcSort(boolean gcSort) {
        this.gcSort = gcSort;
    }

    public int getGcVirtual() {
        return gcVirtual;
    }

    public void setGcVirtual(int gcVirtual) {
        this.gcVirtual = gcVirtual;
    }

    public String getGcTitle() {
        return gcTitle;
    }

    public void setGcTitle(String gcTitle) {
        this.gcTitle = gcTitle;
    }

    public String getGcKeywords() {
        return gcKeywords;
    }

    public void setGcKeywords(String gcKeywords) {
        this.gcKeywords = gcKeywords;
    }

    public String getGcDescription() {
        return gcDescription;
    }

    public void setGcDescription(String gcDescription) {
        this.gcDescription = gcDescription;
    }

    public boolean isGcShow() {
        return gcShow;
    }

    public void setGcShow(boolean gcShow) {
        this.gcShow = gcShow;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public static void getGoodsType(Callback<ResponseData<List<GoodsTypeModule>>> callback) {
        Retrofit retrofit = HttpTools.getInstance().getRetrofit();
        MainService service = retrofit.create(MainService.class);
        Call<ResponseData<List<GoodsTypeModule>>> call = service.getGoodsType();
        call.enqueue(callback);
    }
}
