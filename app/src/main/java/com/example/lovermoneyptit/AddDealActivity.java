package com.example.lovermoneyptit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.models.Group;
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.repository.WalletRepo;
import com.example.lovermoneyptit.utils.FormatUtils;

import java.text.SimpleDateFormat;

public class AddDealActivity extends AppCompatActivity implements
        borrowLoanFragment.OnFragmentInteractionListener,
        CashOutFragment.OnFragmentInteractionListener,
        CashInFragment.OnFragmentInteractionListener {

    static final int REQUEST_CODE_SELECT_WALLET = 1, REQUEST_CODE_SELECT_GROUP = 2, REQUEST_CODE_SELECT = 3;
    // Views
    TextView txtDealCreatedDate, txtDesc, txtDealValue, txtGroup;

    TextView txtSelectWallet;

    LinearLayout selectWalletLayout, datePickerlayout, selectGrouplayout;

    static Toolbar toolbarAddDeal;

    Deal dealToAdd;
    WalletRepo walletRepo;
    Wallet wallet = null;
    Group group = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deal);

        // bindview
        txtSelectWallet = findViewById(R.id.txtSelectWallet);
        txtDealCreatedDate = findViewById(R.id.txtDealCreatedDate);
        txtGroup = findViewById(R.id.txtGroup);
        txtDesc = findViewById(R.id.txtDesc);
        txtDealValue = findViewById(R.id.txtDealValue);

        // toolbar
        toolbarAddDeal = findViewById(R.id.toolbarAddDeal);
        setSupportActionBar(toolbarAddDeal);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.addDeal);

        // deal to add
        dealToAdd = new Deal();
        walletRepo = new WalletRepo(getApplicationContext());

        // item click
        // wallet
        selectWalletLayout = findViewById(R.id.selectWalletLayout);
        selectWalletLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectWalletActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }
        });

        // created date
        datePickerlayout = findViewById(R.id.datePickerLayout);
        datePickerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerActivity();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });

        //group
        selectGrouplayout = findViewById(R.id.selectGroupLayout);
        selectGrouplayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectGroupActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }
        });


    }

    public TextView getTxtDealCreatedDate() {
        return txtDealCreatedDate;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT) {

            switch (resultCode) {
                case REQUEST_CODE_SELECT_WALLET:
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        wallet = (Wallet) bundle.getSerializable("wallet");
                        txtSelectWallet.setText(wallet.getWalletName());
                        Toast.makeText(getApplicationContext(), data.getStringExtra("walletName"), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case REQUEST_CODE_SELECT_GROUP:
                    Bundle bundle1 = data.getExtras();
                    if (bundle1 != null) {
                        group = (Group) bundle1.getSerializable("group");
                        txtGroup.setText(group.getGroupName());
                        Toast.makeText(getApplicationContext(), data.getStringExtra("groupName"), Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // handle arrow click here
        if (id == android.R.id.home) {
            onBackPressed();
        } else if (id == R.id.btnSaveDeal) {
            if ("".equals(txtDealValue.getText().toString()) || "".equals(txtDealCreatedDate.getText().toString())
                    || "".equals(txtGroup.getText().toString()) || "".equals(txtSelectWallet.getText().toString())) {
                Toast.makeText(getApplicationContext(), "không được để trống!", Toast.LENGTH_SHORT).show();
            } else {
                dealToAdd.setDesc(txtDesc.getText().toString());
                dealToAdd.setValue(Long.valueOf(txtDealValue.getText().toString()));
                dealToAdd.setCreatedDate(txtDealCreatedDate.getText().toString());
                dealToAdd.setIdWallet(wallet.getId());
                dealToAdd.setIdGroup(group.getId());
                walletRepo.addDeal(dealToAdd);
                wallet.setBalance(wallet.getBalance() - Double.valueOf(txtDealValue.getText().toString()));
                int res = walletRepo.updateBalanceWallet(wallet);
                Toast.makeText(this, "Save Deal: " + res + wallet.getBalance(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
