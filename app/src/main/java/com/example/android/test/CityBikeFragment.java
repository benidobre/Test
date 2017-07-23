package com.example.android.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bdobre on 20/07/2017.
 */

public class CityBikeFragment extends Fragment  {
    private RecyclerView recyclerView;
    private BikeAdapter bikeAdapter;



    public CityBikeFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_city_bikes,container,false);


        recyclerView = rootView.findViewById(R.id.recyclerview_bike);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);

        bikeAdapter = new BikeAdapter((BikeAdapter.BikeAdapterOnClickHandler) getActivity());
        recyclerView.setAdapter(bikeAdapter);

        ArrayList<String> bikes = new ArrayList<>();
        bikes.add("bicla 1");
        bikes.add("asdf");
        bikes.add("bicla 3");
        bikes.add("bicla 4");
        bikes.add("asdf2");
        bikes.add("bicla 6");
        bikes.add("bicla 7");
        bikes.add("asdf3");
        bikes.add("bicla 9");
        bikes.add("bicla 10");
        bikes.add("asdf4");
        bikes.add("bicla 12");
        bikes.add("bicla 1");
        bikes.add("asdf");
        bikes.add("bicla 3");
        bikes.add("bicla 4");
        bikes.add("asdf2");
        bikes.add("bicla 6");
        bikes.add("bicla 7");
        bikes.add("asdf3");
        bikes.add("bicla 9");
        bikes.add("bicla 10");
        bikes.add("asdf4");
        bikes.add("bicla 12");

        Call<BikeResponse> bikeCall = BikeService.Service.Get().getData();
        bikeCall.enqueue(new Callback<BikeResponse>() {
            @Override
            public void onResponse(Call<BikeResponse> call, Response<BikeResponse> response) {
                if(response.isSuccessful()) {
                    List<BikeLocation> bik;
                    bik = response.body().getBikes();
                    bikeAdapter.setData(bik);
                    bikeAdapter.notifyDataSetChanged();
                    return;
                }
            }

            @Override
            public void onFailure(Call<BikeResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Eroare 2", Toast.LENGTH_SHORT).show();

            }
        });



        return rootView;
    }






}
