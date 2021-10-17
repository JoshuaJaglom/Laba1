package com.example.laba1.repository.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherAnalysis {
    private WeatherAPI api;

    public WeatherAnalysis() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(WeatherAPI.class);
    }

    public LiveData<Daily> getWeather() {
        MutableLiveData<Daily> weather = new MutableLiveData<>();
        api.getWeather().enqueue(new Callback<MyWeather>() {


            @Override
            public void onResponse(Call<MyWeather> call, Response<MyWeather> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MyWeather myWeather = response.body();
                    Daily daily = myWeather.getDaily()[1];
                    weather.setValue(daily);
                }
            }
            @Override
            public void onFailure(Call<MyWeather> call, Throwable t) {
                int res = 1;
                Log.e("efjeif", ""+call+t);

            }
        });
        return weather;
    }
}
