package com.example.lovermoneyptit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DebtAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> list=new ArrayList<>();
    private List<String> title=new ArrayList<String>();

    public DebtAdapter(FragmentManager fm) {
        super(fm);
    }

    public void add(Fragment fragment, String tit){
        list.add(fragment);
        title.add(tit);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
