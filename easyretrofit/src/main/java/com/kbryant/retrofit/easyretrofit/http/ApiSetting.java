package com.kbryant.retrofit.easyretrofit.http;

import com.kbryant.retrofit.easyretrofit.RetrofitApplication;

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

    public String getCacheFile() {
        return cacheFile;
    }

    public void setCacheFile(String cacheFile) {
        this.cacheFile = cacheFile;
    }

    public long getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(long cacheSize) {
        this.cacheSize = cacheSize;
    }

    public int getCookieNoNetWorkTime() {
        return cookieNoNetWorkTime;
    }

    public void setCookieNoNetWorkTime(int cookieNoNetWorkTime) {
        this.cookieNoNetWorkTime = cookieNoNetWorkTime;
    }

    public int getCookieNetWorkTime() {
        return cookieNetWorkTime;
    }

    public void setCookieNetWorkTime(int cookieNetWorkTime) {
        this.cookieNetWorkTime = cookieNetWorkTime;
    }


    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return baseUrl;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

}
