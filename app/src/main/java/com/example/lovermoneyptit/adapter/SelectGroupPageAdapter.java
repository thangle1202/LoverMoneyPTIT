package com.example.lovermoneyptit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SelectGroupPageAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private List<String> titles = new ArrayList<String>();

    public SelectGroupPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void add(Fragment fragment, String title){
        fragments.add(fragment);
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
