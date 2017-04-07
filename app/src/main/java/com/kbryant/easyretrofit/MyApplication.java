package com.kbryant.easyretrofit;

import android.content.Context;

import com.kbryant.retrofit.easyretrofit.RetrofitApplication;
import com.kbryant.retrofit.easyretrofit.http.HttpManager;
import com.orhanobut.logger.Logger;

/**
 * Created by WX on 2017/1/19.
 */

public class MyApplication extends RetrofitApplication {
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        //初始化log TAG
        Logger.init("log").hideThreadInfo();
        HttpManager.getApiSetting().setBaseUrl("http://apistore.baidu.com/microservice/");
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }
}
