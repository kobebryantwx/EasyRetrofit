package com.kbryant.retrofit.rxjavaandretrofitdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by WX on 2017/1/19.
 */

public class MyApplication extends Application {
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }
}
