package com.cardshop.cardshop.Http;

public enum BussniessCodeExplain {
    ;
    // 成员变量
    private String name;
    private int code;

    // 构造方法
    BussniessCodeExplain(String name, int code) {
        this.name = name;
        this.code = code;
    }

    // 普通方法
    public static String getName(int code) {
        for (BussniessCodeExplain c : BussniessCodeExplain.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}