package com.example.lovermoneyptit;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lovermoneyptit.adapter.SelectWalletAdapter;
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.repository.WalletRepo;

import java.util.List;

public class SelectWalletActivity extends AppCompatActivity {

    private RecyclerView rcvSelectWallet;
    private ImageButton btnAddWallet;
    private SelectWalletAdapter selectWalletAdapter;
    private List<Wallet> wallets;
    private WalletRepo walletRepo;
    private Toolbar toolbar;
    static Wallet thisItem = new Wallet();

    // onItemClickListener
    //  click to 1 wallet
    private View.OnClickListener mOnItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int pos = viewHolder.getAdapterPosition();
            thisItem = wallets.get(pos);

            Intent intent = new Intent(getApplicationContext(), AddDealActivity.class);
            intent.putExtra("walletName", thisItem.getWalletName());
            // put object to addDealActivity
            intent.putExtra("wallet", thisItem);
            setResult(AddDealActivity.REQUEST_CODE_SELECT_WALLET, intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_wallet);

        rcvSelectWallet = findViewById(R.id.rcvSelectWallet);
        btnAddWallet = findViewById(R.id.btnAddWallet);
        toolbar = findViewById(R.id.toolbarSelectWallet);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.selectWallet);


        btnAddWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //redirect to addWallet screen
            }
        });

        walletRepo = new WalletRepo(getApplicationContext());

        // get all wallet from table wallet
        wallets = walletRepo.getAllWallets();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        rcvSelectWallet.setLayoutManager(layoutManager);
        selectWalletAdapter = new SelectWalletAdapter(wallets, this.getApplicationContext());
        selectWalletAdapter.setmOnItemClickListener(mOnItemClickListener);
        rcvSelectWallet.setAdapter(selectWalletAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // handle arrow click here
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
