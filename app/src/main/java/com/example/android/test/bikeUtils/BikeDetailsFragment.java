package com.example.android.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by bdobre on 21/07/2017.
 */

public class BikeDetailsFragment extends Fragment {

    public BikeDetailsFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bike_detail,container,false);



        Bundle bundle = getArguments();
        final double longitude = bundle.getDouble("longitude");
        final double latitude = bundle.getDouble("latitude");

        TextView nameTV = rootView.findViewById(R.id.station_name);
        final String name = bundle.getString("name");
        nameTV.setText(name);
        TextView cityTV = rootView.findViewById(R.id.station_location);
        cityTV.setText(bundle.getString("city")+", "+bundle.getString("country"));

        Button button = rootView.findViewById(R.id.location_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:"+latitude+","+longitude+"?q="+name);

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        return rootView;
    }
}
