package com.example.android.test.GOTUtils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
 
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
 
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
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
                FavoriteFragment tab3 = new FavoriteFragment();
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