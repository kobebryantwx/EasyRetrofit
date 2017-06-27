package com.kbryant.retrofit.easyretrofit.http;

import com.kbryant.retrofit.easyretrofit.exception.RetryWhenNetworkException;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * http交互处理类
 * Created by WX on 2016/7/16.
 */
public class HttpManager {
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
     * @param activity           Rx管理activity
     * @param httpOnNextListener 请求回调
     */
    public void doHttpRequest(RxAppCompatActivity activity, Observable observable, HttpOnNextListener httpOnNextListener) {
        doHttpRequest(activity, observable, httpOnNextListener, null);
    }

    /**
     * 处理http请求
     *
     * @param observable         可观察的
     * @param activity           Rx管理activity
     * @param httpOnNextListener 请求回调
     * @param apiSetting         api请求设置
     */
    public void doHttpRequest(RxAppCompatActivity activity, Observable observable, HttpOnNextListener httpOnNextListener, ApiSetting apiSetting) {
        if (apiSetting == null) {
            apiSetting = getApiSetting();
        }
        ApiSubscriber subscriber = new ApiSubscriber(activity, apiSetting, httpOnNextListener);
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
                .map(apiSetting.getFunc())
                .subscribe(subscriber);
    }

}
