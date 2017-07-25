package com.example.android.test.weatherUtils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.test.R;
import com.example.android.test.bikeUtils.BikeAdapter;
import com.example.android.test.bikeUtils.BikeLocation;

import java.util.List;

/**
 * Created by bdobre on 25/07/2017.
 */

public class WeatherAdapter  extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<ConsolidatedWeather> list;
    private final WeatherAdapterOnClickHandler onClickHandler;

    public WeatherAdapter(WeatherAdapterOnClickHandler onClickHandler){
        this.onClickHandler = onClickHandler;
    }

    public void setData(List<ConsolidatedWeather> data){
        list = data;
        notifyDataSetChanged();
    }

    public interface WeatherAdapterOnClickHandler{
        void onWeatherClick(ConsolidatedWeather weatherData);
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item,parent,false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if( list == null) return 0;
        return list.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView date;
        TextView state;
        TextView min;
        TextView max;

        public WeatherViewHolder(View view){
            super(view);
            date = view.findViewById(R.id.weather_date);
            state = view.findViewById(R.id.weather_state);
            min = view.findViewById(R.id.weather_min);
            max = view.findViewById(R.id.weather_max);
        }

        public void bind(int position){
            ConsolidatedWeather weatherData = list.get(position);
            state.setText(weatherData.getWeatherStateName());
            date.setText(weatherData.getApplicableDate());
            min.setText(Math.floor(weatherData.getMinTemp())+"");
            max.setText(Math.floor(weatherData.getMaxTemp())+"");
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            onClickHandler.onWeatherClick(list.get(adapterPosition));
        }
    }


}
