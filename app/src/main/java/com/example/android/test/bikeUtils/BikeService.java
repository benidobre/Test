package com.example.android.test.bikeUtils;

/**
 * Created by bdobre on 20/07/2017.
 */

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface BikeService {

    @GET("/v2/networks")
    Call<BikeResponse> getData();

    class Service{
        private static BikeService sInstance;

        public synchronized static BikeService Get(){
            if(sInstance == null){
                sInstance = new Retrofit.Builder()
                        .baseUrl("http://api.citybik.es/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(BikeService.class);
            }
            return sInstance;
        }
    }

}
