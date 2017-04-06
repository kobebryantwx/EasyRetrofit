package com.kbryant.retrofit.easyretrofit.http;

import com.google.gson.Gson;
import com.kbryant.retrofit.easyretrofit.entity.BaseHttpResult;
import com.kbryant.retrofit.easyretrofit.exception.HttpResponseException;
import com.kbryant.retrofit.easyretrofit.exception.RetryWhenNetworkException;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * http交互处理类
 * Created by WX on 2016/7/16.
 */
public final class HttpManager {
    private volatile static HttpManager INSTANCE;
    private static ApiSetting apiSetting;

    //构造方法私有
    private HttpManager() {
    }

    //获取单例
    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }

    public static ApiSetting getApiSetting() {
        if (apiSetting == null) {
            apiSetting = new ApiSetting();
        }
        return apiSetting;
    }

    /**
     * 处理http请求
     *
     * @param activity             Rx管理activity
     * @param simpleOnNextListener 请求回调
     */
    public void doHttpDeal(RxAppCompatActivity activity, Observable observable, HttpOnNextListener simpleOnNextListener) {
        doHttpDeal(activity, observable, simpleOnNextListener, null);
    }

    /**
     * 处理http请求
     *
     * @param observable           可观察的
     * @param activity             Rx管理activity
     * @param simpleOnNextListener 请求回调
     * @param apiSetting           api请求设置
     */
    public void doHttpDeal(RxAppCompatActivity activity, Observable observable, HttpOnNextListener simpleOnNextListener, ApiSetting apiSetting) {
        if (apiSetting == null) {
            apiSetting = this.apiSetting;
        }
        ProgressSubscriber subscriber = new ProgressSubscriber(activity, apiSetting, simpleOnNextListener);
        observable
                /*失败后的retry配置*/
                .retryWhen(new RetryWhenNetworkException())
                /*生命周期管理*/
                .compose(activity.bindToLifecycle())
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                /*结果判断*/
                .map(new HttpResultFunc())
                  /*数据回调*/
                .subscribe(subscriber);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     * Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<BaseHttpResult<T>, T> {

        @Override
        public T call(BaseHttpResult<T> httpResult) {
            if (httpResult.getErrNum() != 0) {
                throw new HttpResponseException(httpResult.getErrMsg());
            }
            Logger.json(new Gson().toJson(httpResult.getRetData()));
            return httpResult.getRetData();
        }
    }
}
