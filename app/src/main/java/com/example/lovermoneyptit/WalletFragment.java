package com.example.lovermoneyptit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    private ListView listView;
    private List<Wallet> wallets = new ArrayList<>();

    private WalletArrayAdapter adapter = null;

    public WalletFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        // listview
        listView = view.findViewById(R.id.lvWallet);
        // repository
        WalletRepo walletRepo = new WalletRepo(getActivity());
        walletRepo.init();

        wallets = walletRepo.getAllWallets();

        adapter = new WalletArrayAdapter(getActivity(), R.layout.item_wallet, wallets);

        listView.setAdapter(adapter);

        // map component variable with component
        btnAdd = view.findViewById(R.id.btnAddWallet);
        listView = view.findViewById(R.id.lvWallet);

        // button event onclick
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new AddWalletFragment());
                fragmentTransaction.commit();
            }
        });

        // event listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Toast.makeText(getActivity(), wallets.get(i).getWalletName(), Toast.LENGTH_LONG).show();

            Bundle bundle = new Bundle();
            Wallet wallet = wallets.get(i);

            bundle.putSerializable("dungnd", wallet);

            FragmentTransaction ft = getFragmentManager().beginTransaction();

            AddWalletFragment frag = new AddWalletFragment();

            frag.setArguments(bundle);

            ft.replace(R.id.frame_container, frag);

            ft.commit();

            }
        });

        return view;

    }

}
