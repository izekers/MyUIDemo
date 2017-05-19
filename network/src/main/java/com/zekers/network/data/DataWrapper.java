package com.zekers.network.data;

/**
 * 数据封装器
 * Created by Administrator on 2017/1/24.
 */
public class DataWrapper<T>{
    private int code;
    private String msg;
    private T data;

    public boolean success(){
        return code==1;
    };
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
}
