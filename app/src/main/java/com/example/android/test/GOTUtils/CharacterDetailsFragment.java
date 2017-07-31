package com.example.android.test.GOTUtils;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.test.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by bdobre on 31/07/2017.
 */

public class CharacterDetailsFragment extends Fragment {
    private int id;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.got_character_details, container, false);


        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = prefs.edit();

        ImageView image = rootView.findViewById(R.id.character_details_image);
        TextView nameTv = rootView.findViewById(R.id.character_details_name);
        TextView bornTv = rootView.findViewById(R.id.character_details_born);
        TextView aliasesTV = rootView.findViewById(R.id.character_details_aliases);
        Bundle bundle = getArguments();

        Picasso.with(getContext()).load(Uri.parse("https://robohash.org/"+bundle.get("name"))).into(image);
        nameTv.setText(bundle.getString("name"));
        bornTv.setText(bundle.getString("born"));

        id = bundle.getInt("id");

        ArrayList<String> aliases = bundle.getStringArrayList("aliases");
        String alias = "";
        for(String s:aliases){
            alias += s + ", ";

        }
        aliasesTV.setText(alias);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.favorite_menu, menu);


        Set<String> fav = prefs.getStringSet("fav",null);
        if(fav != null && !fav.isEmpty()) {
            if(fav.contains(id+"")) {
                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.favorite_active));
            }
        }
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.favorite_item){
            Set<String> fav = prefs.getStringSet("fav",new HashSet<String>());
            Set<String> in = new HashSet<String>(fav);


                if(fav.contains(id+"")){
                    item.setIcon(getResources().getDrawable(R.drawable.favorite_inactive));
                    in.remove(id+"");

                }else{
                    in.add(id+"");
                    item.setIcon(getResources().getDrawable(R.drawable.favorite_active));
                }

            editor.putStringSet("fav",in).commit();
            return true;
        }
        return false;
    }
}
