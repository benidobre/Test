package com.example.android.test.GOTUtils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bdobre on 28/07/2017.
 */

public class HousesAdapter extends RecyclerView.Adapter<HousesAdapter.HousesViewHolder>{
    private List<House> houses=new ArrayList<>();
    private final HouseAdapterOnClickHandler clickHandler;

    public HousesAdapter(HouseAdapterOnClickHandler clickHandler){
        this.clickHandler = clickHandler;
    }

    @Override
    public HousesAdapter.HousesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bike_list_item,parent,false);
        return new HousesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HousesAdapter.HousesViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(houses == null) return 0;
        return houses.size();
    }

    public void setData(List<House> list){
        houses = list;
        notifyDataSetChanged();
    }

    public List<House> getData(){
        return houses;
    }

    public interface HouseAdapterOnClickHandler{
        void onClick(House house);
    }

    public class HousesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView houseTextView;
        public HousesViewHolder(View view){
            super(view);
             houseTextView = view.findViewById(R.id.tv_bike);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(houses.get(adapterPosition));

        }
        public void bind(int position){
            House house = houses.get(position);
             houseTextView.setText(house.getName());
        }
    }
}