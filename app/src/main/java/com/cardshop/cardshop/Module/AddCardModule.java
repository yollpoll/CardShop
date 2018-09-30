package com.cardshop.cardshop.Module;

public class AddCardModule {
    private String name;
    private String code;
    private String identity;
    private String phone;
    private String bank;

    public AddCardModule(String name, String code, String identity, String phone, String bank) {
        this.name = name;
        this.code = code;
        this.identity = identity;
        this.phone = phone;
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
