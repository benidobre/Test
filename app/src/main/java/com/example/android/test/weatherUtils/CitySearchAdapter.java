package com.example.android.test.weatherUtils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bdobre on 26/07/2017.
 */

public class CitySearchAdapter extends ArrayAdapter<CitySearch> {


    public CitySearchAdapter(Context c, List<CitySearch> list){
        super(c,0,list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview_weather_item,parent,false);
        }
        CitySearch current = getItem(position);
        TextView name = listItemView.findViewById(R.id.listview_item_name);
        name.setText(current.getTitle());
        return listItemView;
    }
}
