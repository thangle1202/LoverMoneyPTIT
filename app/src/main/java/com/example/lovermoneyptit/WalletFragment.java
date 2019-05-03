package com.example.lovermoneyptit;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lovermoneyptit.adapter.DealAdapter;
import com.example.lovermoneyptit.adapter.WalletAdapter;
import com.example.lovermoneyptit.custom.WalletArrayAdapter;
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.repository.WalletRepo;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment {


    private Button btnAdd;
    private RecyclerView lvWallet;
    private List<Wallet> wallets = new ArrayList<>();
    private WalletAdapter walletAdapter;
    static Wallet thisItem = new Wallet();
    public static WalletRepo walletRepo;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int pos = viewHolder.getAdapterPosition();
            thisItem = wallets.get(pos);

            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_edit_wallet);
            final EditText txtWalletName = dialog.findViewById(R.id.txtWalletName);
            final EditText txtWalletBalance = dialog.findViewById(R.id.txtWalletBalance);
            final EditText txtWalletDesc = dialog.findViewById(R.id.txtWalletDesc);
            Button btnConfirm = dialog.findViewById(R.id.btnConfirm);

            txtWalletName.setText(thisItem.getWalletName());
            txtWalletBalance.setText(thisItem.getBalance().toString());
            txtWalletDesc.setText(thisItem.getDesc());

            // confirm add wallet
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Wallet walletToEdit = new Wallet();
                    String walletName = txtWalletName.getText().toString().trim();
                    Double walletBalance = Double.parseDouble(txtWalletBalance.getText().toString());
                    String walletDesc = txtWalletDesc.getText().toString().trim();

                    if("".equals(walletName) || "".equals(walletDesc) || walletBalance == null){
                        Toast.makeText(getActivity(), "không được bỏ trống", Toast.LENGTH_SHORT).show();
                    }

                    walletToEdit.setWalletName(walletName);
                    walletToEdit.setBalance(walletBalance);
                    walletToEdit.setDesc(walletDesc);

                    int res = walletRepo.updateWallet(walletToEdit);
                    Log.i("result update: ", String.valueOf(res));
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    };

    public WalletFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        // recyclerVie
        lvWallet = view.findViewById(R.id.lvWallet);
        // repository
        walletRepo = new WalletRepo(getActivity());
        walletRepo.init();

        wallets = walletRepo.getAllWallets();

        // map component variable with component
        btnAdd = view.findViewById(R.id.btnAddWallet);

        // button event onclick
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_add_wallet);
                final EditText txtWalletName = dialog.findViewById(R.id.txtWalletName);
                final EditText txtWalletBalance = dialog.findViewById(R.id.txtWalletBalance);
                final EditText txtWalletDesc = dialog.findViewById(R.id.txtWalletDesc);
                Button btnConfirm = dialog.findViewById(R.id.btnConfirm);

                // confirm add wallet
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Wallet walletToAdd = new Wallet();
                        String walletName = txtWalletName.getText().toString();
                        Double walletBalance = Double.parseDouble(txtWalletBalance.getText().toString());
                        String walletDesc = txtWalletDesc.getText().toString();

                        if("".equals(walletName) || "".equals(walletDesc) || walletBalance == null){
                            Toast.makeText(getActivity(), "không được bỏ trống", Toast.LENGTH_SHORT).show();
                        }

                        walletToAdd.setWalletName(walletName);
                        walletToAdd.setBalance(walletBalance);
                        walletToAdd.setDesc(walletDesc);

                        walletRepo.addWallet(walletToAdd);

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        lvWallet.setLayoutManager(layoutManager);
        walletAdapter = new WalletAdapter(wallets, this.getContext());
        walletAdapter.setmOnClickListener(mOnClickListener);
        lvWallet.setAdapter(walletAdapter);

        return view;

    }

}
