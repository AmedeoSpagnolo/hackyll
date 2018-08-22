package com.amedeospagnolo.hack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class pagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public pagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tabActive tab1 = new tabActive();
                return tab1;
            case 1:
                tabPast tab2 = new tabPast();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}