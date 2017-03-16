package com.kbryant.retrofit.rxjavaandretrofitdemo.http;

import com.kbryant.retrofit.rxjavaandretrofitdemo.entity.BaseHttpResult;
import com.kbryant.retrofit.rxjavaandretrofitdemo.entity.StudyPlace;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Http接口
 * Created by WX on 2016/12/19.
 */

public interface HttpInterfaces {

    @FormUrlEncoded
    @POST("login/")
    Observable<BaseHttpResult<List<StudyPlace>>> getAllStation(@Field("teachPlace") String placeName);

}
