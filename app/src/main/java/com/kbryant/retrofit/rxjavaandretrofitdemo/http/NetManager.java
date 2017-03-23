package com.kbryant.retrofit.rxjavaandretrofitdemo.http;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * 网络请求
 * Created by WX on 2017/1/19.
 */

public class NetManager {

    public static void getWeather(RxAppCompatActivity activity, String placeName, HttpOnNextListener simpleOnNextListener) {
        BaseApi api = new BaseApi(activity, simpleOnNextListener);
        HttpManager.getInstance().doHttpDeal(api.getApi(HttpInterfaces.class).getWeather(placeName), api);
    }
}
