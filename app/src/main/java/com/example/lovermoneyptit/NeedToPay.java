package com.example.lovermoneyptit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class NeedToPay extends Fragment {

    Button btnAddPay;
    View view;
    public NeedToPay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_need_to_pay, container, false);
        btnAddPay=view.findViewById(R.id.btn_add_pay);
        btnAddPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddCollectActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
