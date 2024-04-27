package com.hcl.hackathon.demo.constants;

public enum StatusCode {
    INTERNAL_SERVER_ERROR("99999","Internal Server Error."),
    NOT_FOUND("11111","Not Found"),
    TRADE_NO_INSTRUMENT("20001", "You don't have any instrument to sell"),
    TRADE_NOT_ENOUGH_INSTRUMENT("20002", "You don't have enough instrument to sell"),
    SUCCESS("00000","SUCCESS");
    StatusCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String code;

    public String desc;
}
