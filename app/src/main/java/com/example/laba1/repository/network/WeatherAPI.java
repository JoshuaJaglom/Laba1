package com.example.laba1.repository.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherAPI {
    @GET("onecall?lat=55.751125&lon=37.617831&exclude=hourly&appid=c5af8c5386619c27509740b802c70a47")
    Call<MyWeather> getWeather();
}
