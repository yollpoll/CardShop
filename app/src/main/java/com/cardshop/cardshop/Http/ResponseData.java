package com.cardshop.cardshop.Http;

/**
 * Created by 鹏祺 on 2017/9/20.
 */

public class ResponseData<T extends Object> {
    public static final int SUCCESS = 0;
    public static final int FAILED = -1;

    private int code;
    private String msg;
    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return SUCCESS == code;
    }
}
