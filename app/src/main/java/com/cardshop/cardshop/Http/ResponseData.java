package com.cardshop.cardshop.Http;

/**
 * Created by 鹏祺 on 2017/9/20.
 */

public class ResponseData<T extends Object> {
    private int code;
    private String message;
    private T datas;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        datas = datas;
    }

    public boolean isSuccess() {
        return 200 == code;
    }
}
