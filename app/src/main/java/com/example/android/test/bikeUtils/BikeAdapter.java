package com.example.android.test;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bdobre on 20/07/2017.
 */

public class BikeAdapter extends RecyclerView.Adapter<BikeAdapter.BikeViewHolder> {
    private List<BikeLocation> bikes;
    private final BikeAdapterOnClickHandler clickHandler;

    @Override
    public BikeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bike_list_item,parent,false);
        return new BikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BikeViewHolder holder, int position) {
        holder.bind(position);
    }

    public void setData(List<BikeLocation> data){
        bikes = data;
    }

    @Override
    public int getItemCount() {
        if(bikes == null) return 0;
        return bikes.size();
    }

    public interface BikeAdapterOnClickHandler{
        void onClick(BikeLocation bikeData);
    }

    public BikeAdapter(BikeAdapterOnClickHandler clickHandler){
        this.clickHandler = clickHandler;
    }



    public class BikeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView bikeTextView;

        public BikeViewHolder(View view){
            super(view);
            bikeTextView = view.findViewById(R.id.tv_bike);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            BikeLocation bikeData = bikes.get(adapterPosition);
            clickHandler.onClick(bikeData);
        }

        public void bind(int position){
            BikeLocation bikeData  = bikes.get(position);
            bikeTextView.setText(bikeData.getLocation().getCity());
        }
    }
}
