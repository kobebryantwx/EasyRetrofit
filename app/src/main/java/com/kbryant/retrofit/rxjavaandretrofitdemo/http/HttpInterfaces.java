package com.kbryant.retrofit.rxjavaandretrofitdemo.http;

import com.kbryant.retrofit.rxjavaandretrofitdemo.entity.BaseHttpResult;
import com.kbryant.retrofit.rxjavaandretrofitdemo.entity.Weather;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Http接口
 * Created by WX on 2016/12/19.
 */

public interface HttpInterfaces {

    @GET("weather")
    Observable<BaseHttpResult<Weather>> getWeather(@Query("cityname") String placeName);

}
