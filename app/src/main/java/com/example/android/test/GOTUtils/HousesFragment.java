package com.example.android.test.GOTUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.test.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bdobre on 27/07/2017.
 */

public class HousesFragment extends Fragment{
    private RecyclerView recyclerView;
    private HousesAdapter housesAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private boolean hasItems = false;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView =  inflater.inflate(R.layout.got_houses, container, false);

            recyclerView = rootView.findViewById(R.id.recyclerview_got_houses);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

            recyclerView.setLayoutManager(layoutManager);

            housesAdapter = new HousesAdapter((HousesAdapter.HouseAdapterOnClickHandler) getActivity());
            recyclerView.setAdapter(housesAdapter);

            fetch(1);
            scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    // Triggered only when new data needs to be appended to the list
                    // Add whatever code is needed to append new items to the bottom of the list
                    fetch(page+1);
                }
            };


            return rootView;
        }


    public void fetch(int page){
        Call<List<House>> housesCall = GOTService.Service.Get().getHouses(page,50);
        housesCall.enqueue(new Callback<List<House>>() {
            @Override
            public void onResponse(Call<List<House>> call, Response<List<House>> response) {
                if(response.isSuccessful()){
                    List<House> houses = response.body();
                    List<House> list = housesAdapter.getData();
                    for(House h:houses){
                        list.add(h);
                    }
                    housesAdapter.setData(list);
                    if(!hasItems){
                        hasItems = true;
                        recyclerView.addOnScrollListener(scrollListener);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<House>> call, Throwable t) {

            }
        });
    }
}
