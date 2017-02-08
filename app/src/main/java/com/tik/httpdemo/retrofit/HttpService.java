package com.tik.httpdemo.retrofit;

import com.tik.httpdemo.bean.WeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;


public interface HttpService {
    @GET("sk/101010100.html")
    Call<WeatherBean> getDataString();
}
