package com.example.android.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.test.weatherUtils.ConsolidatedWeather;
import com.example.android.test.weatherUtils.WeatherLocation;
import com.example.android.test.weatherUtils.WeatherResponse;
import com.example.android.test.weatherUtils.WeatherService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bdobre on 20/07/2017.
 */


public class WeatherFragment extends Fragment {


    public WeatherFragment(){}

    private int woeid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather,container,false);

        Bundle bundle = getArguments();
        final double longitude = bundle.getDouble("longitude");
        final double latitude = bundle.getDouble("latitude");

        Call<List<WeatherLocation>> nearCall = WeatherService.Service.Get().getNearCities(latitude+","+longitude);
        nearCall.enqueue(new Callback<List<WeatherLocation>>() {
            @Override
            public void onResponse(Call<List<WeatherLocation>> call, Response<List<WeatherLocation>> response) {
                if(response.isSuccessful()){
                    List<WeatherLocation> list = response.body();
                    if(list != null) {
                        Toast.makeText(getContext(), list.get(0).getWoeid()+"", Toast.LENGTH_LONG).show();
                        woeid = list.get(0).getWoeid();

                        Call<WeatherResponse> weatherCall = WeatherService.Service.Get().getWeather(woeid);
                        weatherCall.enqueue(new Callback<WeatherResponse>() {
                            @Override
                            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                                if(response.isSuccessful()){
                                    WeatherResponse wr = response.body();
                                    List<ConsolidatedWeather> list = wr.getConsolidatedWeather();
                                    Toast.makeText(getContext(), "dfsfds", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                                Toast.makeText(getContext(), "sdfs", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<List<WeatherLocation>> call, Throwable t) {
                Toast.makeText(getContext(), "La la la", Toast.LENGTH_SHORT).show();
            }
        });



        return rootView;
    }
}
