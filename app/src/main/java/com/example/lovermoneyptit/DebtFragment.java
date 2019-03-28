package com.example.lovermoneyptit;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lovermoneyptit.adapter.DebtAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class DebtFragment extends Fragment {

    View view;
    public DebtFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_debt, container, false);
        setupPager();

        return view;
    }

    private void setupPager(){
        ViewPager viewPager=view.findViewById(R.id.debt_pager);

        DebtAdapter debtAdapter=new DebtAdapter(getActivity().getSupportFragmentManager());
        debtAdapter.add(new NeedToPay(),"Must Pay");
        debtAdapter.add(new NeedToCollect(),"Must Collect");
        viewPager.setAdapter(debtAdapter);

        TabLayout tabLayout=view.findViewById(R.id.debt_tab);
        tabLayout.setupWithViewPager(viewPager);
    }

}
