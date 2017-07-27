package com.example.android.test.GOTUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.test.R;

/**
 * Created by bdobre on 27/07/2017.
 */

public class FavoriteFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.tab_fragment_1, container, false);
        ((TextView)rootView.findViewById(R.id.textView)).setText("Tab3");
        return rootView;
    }
}
