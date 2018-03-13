package com.farm.common;

public class MessageResult {
    private String code;
    private String msg;
    private Object data;

    public MessageResult() {
        this.code = "1";
    }

    public void setError(){
        this.code = "0";
    }

    public void setError(String msg){
        this.code = "0";
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
