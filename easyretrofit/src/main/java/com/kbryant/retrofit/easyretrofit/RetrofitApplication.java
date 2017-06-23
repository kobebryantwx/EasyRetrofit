package com.kbryant.retrofit.easyretrofit;

import android.app.Application;
import android.content.Context;

/**
 * Describe :
 * Created by Wx on 2017/4/6 0006.
 */
public class RetrofitApplication extends Application {
    private static RetrofitApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }
}
