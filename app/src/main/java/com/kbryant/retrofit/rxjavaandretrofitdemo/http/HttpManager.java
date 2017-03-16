package com.kbryant.retrofit.rxjavaandretrofitdemo.http;

import com.kbryant.retrofit.rxjavaandretrofitdemo.entity.BaseHttpResult;
import com.kbryant.retrofit.rxjavaandretrofitdemo.exception.HttpResponseException;
import com.kbryant.retrofit.rxjavaandretrofitdemo.exception.RetryWhenNetworkException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * http交互处理类
 * Created by WX on 2016/7/16.
 */
public class HttpManager {
    private volatile static HttpManager INSTANCE;

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

    /**
     * 处理http请求
     *
     * @param observable
     * @param baseApi    请求设置
     */
    public void doHttpDeal(Observable observable, BaseApi baseApi) {
        ProgressSubscriber subscriber = new ProgressSubscriber(baseApi);
        observable
                /*失败后的retry配置*/
                .retryWhen(new RetryWhenNetworkException())
                /*生命周期管理*/
//                .compose(baseApi.getRxAppCompatActivity().bindToLifecycle())
//                .compose(baseApi.getRxAppCompatActivity().bindUntilEvent(ActivityEvent.DESTROY))
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
            if (!httpResult.isSuccess()) {
                throw new HttpResponseException(httpResult.getMsg());
            }
            return httpResult.getData();
        }
    }
}
