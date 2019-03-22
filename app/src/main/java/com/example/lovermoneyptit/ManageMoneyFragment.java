package com.example.lovermoneyptit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class ManageMoneyFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Deal> deals;
    private DealAdapter dealAdapter;
    private Realm realm;
    private EditText edtWalletName, edtWalletAmount, edtWalletDesc;
    private Button btnAddWallet, btnView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_manage_money, container, false);
        recyclerView = view.findViewById(R.id.rcvDeal);
        edtWalletName = view.findViewById(R.id.edtWalletName);
        edtWalletAmount = view.findViewById(R.id.edtWalletAmount);
        edtWalletDesc = view.findViewById(R.id.edtWalletDesc);
        btnAddWallet = view.findViewById(R.id.btnAddWallet);
        btnView = view.findViewById(R.id.btnView);

        // init RealmObject
        realm = Realm.getDefaultInstance();

        btnAddWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Wallet wallet = new Wallet();
                wallet.setWalletName(edtWalletName.getText().toString());
                wallet.setAmount(Long.parseLong(edtWalletAmount.getText().toString()));
                wallet.setDesc(edtWalletDesc.getText().toString());
                RealmManager.addItem(wallet, Wallet.class);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Wallet wallet = (Wallet) RealmManager.getByFieldName("walletName", "giai tri", Wallet.class);
                System.out.println("id wallet:" + wallet.getId());
            }
        });

        // data demo for recyclerview
        deals = new ArrayList<Deal>();
        Deal deal1 = new Deal();
        deal1.setCreatedDate(new Date());
        deal1.setValue(70000l);
        deals = new ArrayList<Deal>();
        Deal deal2 = new Deal();
        deal2.setCreatedDate(new Date());
        deal2.setValue(99999l);

        deals.add(deal2);
        deals.add(deal1);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        dealAdapter = new DealAdapter(deals, this.getContext());
        recyclerView.setAdapter(dealAdapter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
