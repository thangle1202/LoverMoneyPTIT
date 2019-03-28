package com.example.lovermoneyptit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thuannd.com.moneyloverclone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NeedToPay extends Fragment {


    public NeedToPay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_need_to_pay, container, false);
    }

}
