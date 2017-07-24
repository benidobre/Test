package com.example.android.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.test.bikeUtils.BikeAdapter;
import com.example.android.test.bikeUtils.BikeLocation;
import com.example.android.test.bikeUtils.BikeResponse;
import com.example.android.test.bikeUtils.BikeService;

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
