package com.kbryant.easyretrofit;

import com.kbryant.retrofit.easyretrofit.RetrofitApplication;
import com.kbryant.retrofit.easyretrofit.entity.BaseHttpResult;
import com.kbryant.retrofit.easyretrofit.exception.HttpResponseException;
import com.kbryant.retrofit.easyretrofit.http.HttpManager;
import com.orhanobut.logger.Logger;

import rx.functions.Func1;

/**
 * Created by WX on 2017/1/19.
 */

public class MyApplication extends RetrofitApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化log TAG
        Logger.init("log").hideThreadInfo();
        HttpManager.getApiSetting().setBaseUrl("http://api.k780.com:88/").setFunc(new HttpResultFunc());
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     * Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<BaseHttpResult<T>, T> {

        @Override
        public T call(BaseHttpResult<T> httpResult) {
            if (httpResult.getSuccess() != 1) {
                throw new HttpResponseException("失败");
            }
//            Log.i("retrofit",new Gson().toJson(httpResult.getRetData()));
            return httpResult.getResult();
        }
    }
}
