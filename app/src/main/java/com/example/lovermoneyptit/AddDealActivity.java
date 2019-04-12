package com.example.lovermoneyptit;

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

public class AddDealActivity extends AppCompatActivity implements
        borrowLoanFragment.OnFragmentInteractionListener,
        CashOutFragment.OnFragmentInteractionListener,
        CashInFragment.OnFragmentInteractionListener {

    // Views
    private TextView txtDealCreatedDate, txtDesc, txtDealValue, txtGroup;

    private TextView txtSelectWallet;

    private LinearLayout selectWalletLayout, datePickerlayout, selectGrouplayout;

    private static Toolbar toolbarAddDeal;

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

        // item click
        // wallet
        selectWalletLayout = findViewById(R.id.selectWalletLayout);
        selectWalletLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectWalletActivity.class);
                startActivity(intent);
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
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = this.getIntent();
        // select wallet
        txtSelectWallet.setText(intent.getStringExtra("walletName"));
        // select group
        txtGroup.setText(intent.getStringExtra("groupName"));
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

            Toast.makeText(this, "Save Deal", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
