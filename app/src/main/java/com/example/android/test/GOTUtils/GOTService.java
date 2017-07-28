package com.example.android.test.GOTUtils;


import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bdobre on 27/07/2017.
 */

public interface GOTService {
    @GET("api/books?page=1")
    Call<List<Book>> getBooks(@Query("pageSize") int pageSize);

    @GET("api/houses")
    Call<List<House>> getHouses(@Query("page") int page,@Query("pageSize") int pageSize);

    @GET("api/characters/{ch}")
    Call<Character> getCharacter(@Path("ch") int ch);

    class Service{
        private static GOTService sInstance;

        public synchronized static GOTService Get(){
            if(sInstance == null){
                sInstance = new Retrofit.Builder()
                        .baseUrl("https://www.anapioficeandfire.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(GOTService.class);
            }
            return sInstance;
        }
    }
}
