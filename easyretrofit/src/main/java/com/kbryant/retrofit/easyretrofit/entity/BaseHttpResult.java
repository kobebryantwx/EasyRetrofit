package com.kbryant.retrofit.easyretrofit.entity;

/**
 * 回调信息统一封装类
 * Created by WX on 2016/7/16.
 */
public class BaseHttpResult<T> {
    //  判断标示
    private int success;

    //数据
    private T result;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
