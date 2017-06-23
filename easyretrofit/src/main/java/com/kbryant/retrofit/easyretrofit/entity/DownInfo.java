package com.kbryant.retrofit.easyretrofit.entity;

import com.kbryant.retrofit.easyretrofit.download.HttpDownOnNextListener;
import com.kbryant.retrofit.easyretrofit.download.HttpDownService;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * apk下载请求数据基础类
 * Created by WX on 2016/10/20.
 */

@Entity
public class DownInfo {
    @Id(autoincrement = true)
    private long id;
    /*存储位置*/
    private String savePath;
    /*文件总长度*/
    private long countLength;
    /*下载长度*/
    private long readLength;
    /*下载唯一的HttpService*/
    @Transient
    private HttpDownService service;
    /*回调监听*/
    @Transient
    private HttpDownOnNextListener listener;
    /*超时设置*/
    private int connectionTime = 6;
    /*state状态数据库保存*/
    private int state = DownState.START.getState();
    /*url*/
    private String url;
    /*文件名,包括后缀*/
    private String name;

    public DownInfo(String url, String name, HttpDownOnNextListener listener) {
        setUrl(url);
        setName(name);
        setListener(listener);
    }

    @Generated(hash = 723014657)
    public DownInfo(long id, String savePath, long countLength, long readLength,
                    int connectionTime, int state, String url, String name) {
        this.id = id;
        this.savePath = savePath;
        this.countLength = countLength;
        this.readLength = readLength;
        this.connectionTime = connectionTime;
        this.state = state;
        this.url = url;
        this.name = name;
    }

    @Generated(hash = 928324469)
    public DownInfo() {
    }

    public DownState getDownState() {
        switch (getState()) {
            case 0:
                return DownState.START;
            case 1:
                return DownState.DOWN;
            case 2:
                return DownState.PAUSE;
            case 3:
                return DownState.STOP;
            case 4:
                return DownState.ERROR;
            case 5:
            default:
                return DownState.FINISH;
        }
    }


    public void setState(DownState downState) {
        this.state = downState.getState();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public HttpDownOnNextListener getListener() {
        return listener;
    }

    public void setListener(HttpDownOnNextListener listener) {
        this.listener = listener;
    }

    public HttpDownService getService() {
        return service;
    }

    public void setService(HttpDownService service) {
        this.service = service;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }


    public long getCountLength() {
        return countLength;
    }

    public void setCountLength(long countLength) {
        this.countLength = countLength;
    }


    public long getReadLength() {
        return readLength;
    }

    public void setReadLength(long readLength) {
        this.readLength = readLength;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    public void setState(int state) {
        this.state = state;
    }
}
