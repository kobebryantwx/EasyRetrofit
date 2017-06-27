package com.kbryant.retrofit.easyretrofit.http;

import com.kbryant.retrofit.easyretrofit.RetrofitApplication;

import retrofit2.Converter;
import rx.functions.Func1;

/**
 * 请求设置统一封装类
 * Created by WX on 2016/11/5.
 */
public final class ApiSetting {

    /*是否能取消加载框*/
    private boolean cancel;
    /*是否显示加载框*/
    private boolean showProgress;
    /*是否需要缓存处理*/
    private boolean cache = true;
    /*基础url*/
    private String baseUrl;
    /*超时时间*/
    private int connectionTime = 20;
    /*有网情况下的本地缓存时间*/
    private int cookieNetWorkTime = 50;
    /*无网络的情况下本地缓存时间*/
    private int cookieNoNetWorkTime = 2 * 60 * 60;
    //缓存容量
    private long cacheSize = 10 * 1024 * 1024; // 10MB
    //缓存路径
    private String cacheFile = RetrofitApplication.getContext().getCacheDir() + "/http";
    //返回数据判断
    private Func1 func = null;
    //retrofit结果转换类
    private Converter.Factory convertFactory;

    public Converter.Factory getConvertFactory() {
        return convertFactory;
    }

    public ApiSetting setConvertFactory(Converter.Factory convertFactory) {
        this.convertFactory = convertFactory;
        return this;
    }

    public Func1 getFunc() {
        return func;
    }

    public ApiSetting setFunc(Func1 func) {
        this.func = func;
        return this;
    }

    public String getCacheFile() {
        return cacheFile;
    }

    public ApiSetting setCacheFile(String cacheFile) {
        this.cacheFile = cacheFile;
        return this;
    }

    public long getCacheSize() {
        return cacheSize;
    }

    public ApiSetting setCacheSize(long cacheSize) {
        this.cacheSize = cacheSize;
        return this;
    }

    public int getCookieNoNetWorkTime() {
        return cookieNoNetWorkTime;
    }

    public ApiSetting setCookieNoNetWorkTime(int cookieNoNetWorkTime) {
        this.cookieNoNetWorkTime = cookieNoNetWorkTime;
        return this;
    }

    public int getCookieNetWorkTime() {
        return cookieNetWorkTime;
    }

    public ApiSetting setCookieNetWorkTime(int cookieNetWorkTime) {
        this.cookieNetWorkTime = cookieNetWorkTime;
        return this;
    }


    public int getConnectionTime() {
        return connectionTime;
    }

    public ApiSetting setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
        return this;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public ApiSetting setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String getUrl() {
        return baseUrl;
    }

    public boolean isCache() {
        return cache;
    }

    public ApiSetting setCache(boolean cache) {
        this.cache = cache;
        return this;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public ApiSetting setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
        return this;
    }

    public boolean isCancel() {
        return cancel;
    }

    public ApiSetting setCancel(boolean cancel) {
        this.cancel = cancel;
        return this;
    }

}
