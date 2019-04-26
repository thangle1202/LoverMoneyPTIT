package com.example.lovermoneyptit;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.repository.WalletRepo;

import java.util.ArrayList;
import java.util.List;

public class AddWalletFragment extends Fragment {

    public AddWalletFragment(){
        // constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_add_wallet, container, false);

        // mapping component var with component
        final Button btnBack = view.findViewById(R.id.btnBackToListWallet);
        final Button btnSave = view.findViewById(R.id.btnSaveWallet);
        final Button btnRemove = view.findViewById(R.id.btnRemoveWallet);
        final EditText edtWalletName = view.findViewById(R.id.edtWalletName);
        final EditText edtWalletBalance = view.findViewById(R.id.edtWalletBalance);
        final EditText edtWalletDesc = view.findViewById(R.id.edtWalletDesc);
        final TextView txtWalletId = view.findViewById(R.id.txtWalletId);

        // get bundle (case edit / delete)
        final Bundle bundle = getArguments();
        Wallet wallet = null;
        if(bundle != null){
            wallet = (Wallet) bundle.getSerializable("dungnd");
            txtWalletId.setText(String.valueOf(wallet.getId()));
            edtWalletName.setText(wallet.getWalletName());
            edtWalletBalance.setText(String.valueOf(wallet.getBalance()));
            edtWalletDesc.setText(wallet.getDesc());

            btnRemove.setEnabled(true); // enable button remove wallet
        }
        else btnRemove.setEnabled(false);

        // button Save event onclick (case add new wallet)
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String walletName = edtWalletName.getText().toString().trim();
                String walletBalance = edtWalletBalance.getText().toString().trim();
                String walletDesc = edtWalletDesc.getText().toString().trim();

                if(walletName.length() == 0 || walletBalance.length() == 0) { // check blank input
                    Toast.makeText(getActivity(), "Nhap day du!!", Toast.LENGTH_LONG).show();
                    return;
                }

                WalletRepo walletRepo = new WalletRepo(getActivity());

                if(bundle == null) { // case add
                    Wallet myWallet = new Wallet(walletName, Double.valueOf(walletBalance), walletDesc);
                    walletRepo.addWallet(myWallet);
                    // after add wallet, redirect to wallet fragment
                    Toast.makeText(getActivity(), "Them thanh cong!!", Toast.LENGTH_LONG).show();
                    switchFragment(view);
                } else { // case edit
                    Wallet myWallet = new Wallet(walletName, Double.valueOf(walletBalance), walletDesc);
                    myWallet.setId(Integer.valueOf(txtWalletId.getText().toString()));
                    walletRepo.updateWallet(myWallet);

                    // after edit wallet, redirect to wallet fragment
                    Toast.makeText(getActivity(), "Sua thanh cong!!", Toast.LENGTH_LONG).show();
                    switchFragment(view);
                }

            }
        });


        // button Remove event onclick
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WalletRepo walletRepo = new WalletRepo(getActivity());

                Wallet myWallet = new Wallet();
                myWallet.setId(Integer.valueOf(txtWalletId.getText().toString()));
                walletRepo.deleteWallet(myWallet);
                // after delete wallet, redirect to wallet fragment
                Toast.makeText(getActivity(), "Xoa thanh cong!!", Toast.LENGTH_LONG).show();
                switchFragment(view);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(view);
            }
        });

        return view;
    }

    private void switchFragment(View view){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, new WalletFragment());
        fragmentTransaction.commit();
    }

}
