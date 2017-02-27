package com.kbryant.retrofit.rxjavaandretrofitdemo.entity;

/**
 * 回调信息统一封装类
 * Created by WX on 2016/7/16.
 */
public class BaseHttpResult<T> {
    //  判断标示
    private boolean success;
    private int code;
    //    提示信息
    private String msg;
    //显示数据（用户需要关心的数据）
    private T data;

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
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
