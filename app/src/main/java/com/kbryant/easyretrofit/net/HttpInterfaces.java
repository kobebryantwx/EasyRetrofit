package com.kbryant.easyretrofit.net;


import com.kbryant.easyretrofit.BJTime;
import com.kbryant.retrofit.easyretrofit.entity.BaseHttpResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Http接口
 * Created by WX on 2016/12/19.
 */

public interface HttpInterfaces {

    @GET
    Observable<BaseHttpResult<BJTime>> getWeather(@Url String url);

}
