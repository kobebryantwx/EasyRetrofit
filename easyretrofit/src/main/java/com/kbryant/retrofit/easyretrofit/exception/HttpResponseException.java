package com.kbryant.retrofit.easyretrofit.exception;

/**
 * 自定义错误信息，统一处理返回处理
 * Created by WX on 2016/7/16.
 */
public class HttpResponseException extends RuntimeException {

    public static final int NO_DATA = 0x2;

    public HttpResponseException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public HttpResponseException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 转换错误数据
     *
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case NO_DATA:
                message = "无数据";
                break;
            default:
                message = "error";
                break;

        }
        return message;
    }
}

