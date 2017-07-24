package com.example.android.test.weatherUtils;

import com.example.android.test.bikeUtils.BikeResponse;
import com.example.android.test.bikeUtils.BikeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bdobre on 24/07/2017.
 */

public interface WeatherService {


    @GET("/api/location/search/")
    Call<List<WeatherLocation>> getNearCities(@Query("lattlong") String latlong);

    @GET("/api/location/{id}")
    Call<WeatherResponse> getWeather(@Path("id") int id );

    class Service{
        private static WeatherService sInstance;

        public synchronized static WeatherService Get(){
            if(sInstance == null){
                sInstance = new Retrofit.Builder()
                        .baseUrl("https://www.metaweather.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(WeatherService.class);
            }
            return sInstance;
        }
    }
}
