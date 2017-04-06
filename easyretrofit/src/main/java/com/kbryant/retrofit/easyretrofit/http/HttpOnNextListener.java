package com.kbryant.retrofit.easyretrofit.http;

/**
 * 成功回调处理
 * Created by WX on 2016/11/5.
 */
public abstract class HttpOnNextListener<T> {
    /**
     * 开始回调
     */
    public void onStart() {

    }


    /**
     * 成功后回调方法
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 缓存回调结果
     *
     * @param string
     */
    public void onCacheSuccess(String string) {

    }

    /**
     * 失败或者错误方法
     *
     * @param e
     */
    public void onError(Throwable e) {

    }

    /**
     * 取消回调
     */
    public void onCancel() {

    }
}
