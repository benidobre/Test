package com.example.android.test.GOTUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.test.MainActivity;

import java.util.HashSet;
import java.util.Set;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Context mContext;
 
    public PagerAdapter(FragmentManager fm, int NumOfTabs,Context context) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        mContext = context;
    }
 
    @Override
    public Fragment getItem(int position) {
 
        switch (position) {
            case 0:
                BooksFragment tab1 = new BooksFragment();
                return tab1;
            case 1:
                HousesFragment tab2 = new HousesFragment();
                return tab2;
            case 2:
                Bundle bundle= new Bundle();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
                Set<String> list = prefs.getStringSet("fav",new HashSet<String>());

                int[] characters= new int[list.size()];
                int i=0;
                for(String s:list){
                    characters[i++] = Integer.parseInt(s);
                }
                bundle.putIntArray("characters",characters);
                CharactersFragment tab3 = new CharactersFragment();
                tab3.setArguments(bundle);
                return tab3;
            default:
                return null;
        }
    }
 
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}