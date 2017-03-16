package com.kbryant.retrofit.rxjavaandretrofitdemo.http;

/**
 * 成功回调处理
 * Created by WX on 2016/11/5.
 */
public abstract class HttpOnNextListener<T> {
    /**
     * 成功后回调方法
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 緩存回調結果
     *
     * @param string
     */
    public void onCacheSuccess(String string) {

    }

    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     *
     * @param e
     */
    public void onError(Throwable e) {

    }

    /**
     * 取消回調
     */
    public void onCancel() {

    }
}
