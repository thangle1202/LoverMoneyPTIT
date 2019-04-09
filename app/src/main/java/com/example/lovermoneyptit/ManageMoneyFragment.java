package com.example.lovermoneyptit;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovermoneyptit.adapter.DealAdapter;
import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.repository.RealmManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class ManageMoneyFragment extends Fragment{

    private RecyclerView recyclerView;
    private List<Deal> deals;
    private DealAdapter dealAdapter;
    private ImageButton btnAddDeal;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_manage_money, container, false);
        recyclerView = view.findViewById(R.id.rcvDeal);

        btnAddDeal = view.findViewById(R.id.btnAddDeal);


        btnAddDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                // testAddDeal
                fragmentTransaction.replace(R.id.frame_container, new AddDealFragment(), null);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        // data demo for recyclerview
        deals = new ArrayList<Deal>();
        Deal deal1 = new Deal();
        deal1.setCreatedDate(new Date());
        deal1.setValue(70000l);

        Deal deal2 = new Deal();
        deal2.setCreatedDate(new Date());
        deal2.setValue(99999l);

        Deal deal3 = new Deal();
        deal3.setCreatedDate(new Date());
        deal3.setValue(99999l);

        Deal deal4 = new Deal();
        deal4.setCreatedDate(new Date());
        deal4.setValue(99999l);

        Deal deal5 = new Deal();
        deal5.setCreatedDate(new Date());
        deal5.setValue(99999l);

        Deal deal6 = new Deal();
        deal6.setCreatedDate(new Date());
        deal6.setValue(99999l);

        Deal deal7 = new Deal();
        deal7.setCreatedDate(new Date());
        deal7.setValue(99999l);

        Deal deal8 = new Deal();
        deal8.setCreatedDate(new Date());
        deal8.setValue(99999l);

        Deal deal9 = new Deal();
        deal9.setCreatedDate(new Date());
        deal9.setValue(99999l);

        deals.add(deal1);
        deals.add(deal2);
        deals.add(deal3);
        deals.add(deal4);
        deals.add(deal5);
        deals.add(deal6);
        deals.add(deal7);
        deals.add(deal8);
        deals.add(deal9);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        dealAdapter = new DealAdapter(deals, this.getContext());
        recyclerView.setAdapter(dealAdapter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
