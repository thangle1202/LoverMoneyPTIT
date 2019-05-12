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
import com.example.lovermoneyptit.api.APIUtils;
import com.example.lovermoneyptit.api.MoneyService;
import com.example.lovermoneyptit.custom.WalletArrayAdapter;
import com.example.lovermoneyptit.models.Group;
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.repository.WalletRepo;
import com.example.lovermoneyptit.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment implements View.OnClickListener {


    private Button btnAdd;
    private RecyclerView lvWallet;
    private List<Wallet> wallets = new ArrayList<>();
    private WalletAdapter walletAdapter;
    static Wallet thisItem = new Wallet();
    public static WalletRepo walletRepo;

    private MoneyService moneyService;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int pos = viewHolder.getAdapterPosition();
            thisItem = wallets.get(pos);

            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_edit_wallet);
            final EditText txtWalletName = dialog.findViewById(R.id.txtWalletName);
            final EditText txtWalletBalance = dialog.findViewById(R.id.txtWalletBalance);
            final EditText txtWalletDesc = dialog.findViewById(R.id.txtWalletDesc);
            Button btnConfirm = dialog.findViewById(R.id.btnConfirm);

            txtWalletName.setText(thisItem.getWalletName());
            txtWalletBalance.setText(FormatUtils.formatVnCurrence(thisItem.getBalance().toString()));
            txtWalletDesc.setText(thisItem.getDesc());

            // confirm add wallet
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Wallet walletToEdit = new Wallet();
                    String walletName = txtWalletName.getText().toString().trim();
//                    Double walletBalance = Double.parseDouble(txtWalletBalance.getText().toString());
                    String walletDesc = txtWalletDesc.getText().toString().trim();

                    if("".equals(walletName) || "".equals(walletDesc)){
                        Toast.makeText(getActivity(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                    }else{

                    walletToEdit.setId(viewHolder.getAdapterPosition()+1);
                    walletToEdit.setWalletName(walletName);
//                    walletToEdit.setBalance(walletBalance);
                    walletToEdit.setDesc(walletDesc);

                    walletRepo.updateWallet(walletToEdit);
                    showWallet(walletRepo.getAllWallets());
                    dialog.dismiss();
                    }
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

        // map component variable with component
        btnAdd = view.findViewById(R.id.btnAddWallet);

        // button event onclick
        btnAdd.setOnClickListener(this);

        wallets = walletRepo.getAllWallets();
        showWallet(wallets);

        return view;

    }

    public void showWallet(List<Wallet> wallets){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        lvWallet.setLayoutManager(layoutManager);
        walletAdapter = new WalletAdapter(wallets, this.getContext());
        walletAdapter.setmOnClickListener(mOnClickListener);
        lvWallet.setAdapter(walletAdapter);
    }

    @Override
    public void onClick(View v) {
        showDialog();
    }

    public void showDialog(){
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
                showWallet(walletRepo.getAllWallets());
            }
        });

        dialog.show();
    }



}
