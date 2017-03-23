package com.kbryant.retrofit.rxjavaandretrofitdemo.entity;

/**
 * 回调信息统一封装类
 * Created by WX on 2016/7/16.
 */
public class BaseHttpResult<T> {
    //  判断标示
    private int errNum;
    // 提示信息
    private String errMsg;
    //数据
    private T retData;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public T getRetData() {
        return retData;
    }

    public void setRetData(T retData) {
        this.retData = retData;
    }
}
