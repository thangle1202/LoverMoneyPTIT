package com.example.lovermoneyptit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lovermoneyptit.adapter.AdapterRecyclerDebt;
import com.example.lovermoneyptit.adapter.AdapterRecyclerPay;
import com.example.lovermoneyptit.models.Collect;
import com.example.lovermoneyptit.models.Debt;
import com.example.lovermoneyptit.repository.WalletRepo;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NeedToPay extends Fragment {

    Button btnAddPay;
    View view;
    WalletRepo repo;
    RecyclerView recyclerView;
    ArrayList<Debt> listCollects;
    public NeedToPay() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_need_to_pay, container, false);
        btnAddPay=view.findViewById(R.id.btn_add_pay);
        repo=new WalletRepo(getContext());
        listCollects= (ArrayList<Debt>) repo.getAllDebt();
        btnAddPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddCollectActivity.class);
                startActivity(intent);
            }
        });

        recyclerView=view.findViewById(R.id.recycler_pay);
        AdapterRecyclerDebt adapter=new AdapterRecyclerDebt(listCollects,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }

}
