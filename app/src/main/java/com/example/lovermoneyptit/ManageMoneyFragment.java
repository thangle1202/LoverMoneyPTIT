package com.example.lovermoneyptit;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import com.example.lovermoneyptit.repository.WalletRepo;

import java.text.ParseException;
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
    private ImageButton btnAddDeal;

    private WalletRepo walletRepo;

    // item click
    static Deal thisItem = new Deal();
    private View.OnClickListener mOnClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int pos = viewHolder.getAdapterPosition();
            thisItem = deals.get(pos);

            Intent intent = new Intent(getActivity(), DealDetailActivity.class);
            intent.putExtra("deal", thisItem);
            startActivity(intent);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_manage_money, container, false);
        recyclerView = view.findViewById(R.id.rcvDeal);

        btnAddDeal = view.findViewById(R.id.btnAddDeal);
        // deal mac dinh
        walletRepo = new WalletRepo(getContext());
        walletRepo.initDeal();

        btnAddDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDealActivity.class);
                startActivity(intent);
            }
        });

        try {
            deals = walletRepo.getAllDeal();
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
            recyclerView.setLayoutManager(layoutManager);
            dealAdapter = new DealAdapter(deals, this.getContext());
            dealAdapter.setmOnClickListener(mOnClicklistener);
            recyclerView.setAdapter(dealAdapter);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
