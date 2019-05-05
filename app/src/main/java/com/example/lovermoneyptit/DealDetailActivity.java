package com.example.lovermoneyptit;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovermoneyptit.api.APIUtils;
import com.example.lovermoneyptit.api.MoneyService;
import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.models.Group;
import com.example.lovermoneyptit.repository.WalletRepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealDetailActivity extends AppCompatActivity {

    private Toolbar toolbarDealDetail;
    private EditText txtDealValue, txtDealDesc;
    private TextView txtDealGroup, txtWallet, txtCreatedDate;
    private WalletRepo walletRepo;
    Deal deal = new Deal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail);

        walletRepo = new WalletRepo(getApplicationContext());

        // get intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        deal = (Deal) bundle.getSerializable("deal");

        // toolbar
        toolbarDealDetail = findViewById(R.id.toolbarDealDetail);
        setSupportActionBar(toolbarDealDetail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.updateDeal);

        // bind view
        txtDealValue = findViewById(R.id.txtDealValue);
        txtDealDesc = findViewById(R.id.txtDesc);
        txtDealGroup = findViewById(R.id.txtDealGroup);
        txtWallet = findViewById(R.id.txtSelectWallet);
        txtCreatedDate = findViewById(R.id.txtDealCreatedDate);

        // bind data
        txtDealValue.setText(String.valueOf(deal.getValue()));
        if(deal.getIdGroup() != null && deal.getIdWallet() != null){ // if sqlite have data
            txtDealGroup.setText(walletRepo.getGroupById(deal.getIdGroup()).getGroupName());
            txtWallet.setText(walletRepo.findWalletById(deal.getIdWallet()).getWalletName());
        } else{ // get data from server
            txtDealGroup.setText(deal.getGroup().getGroupName());
            txtWallet.setText(deal.getWallet().getWalletName());
        }
        txtDealDesc.setText(deal.getDesc());
        txtCreatedDate.setText(deal.getCreatedDate());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        } else {
            switch (id) {
                case R.id.btnUpdate:
                    deal.setValue(Long.valueOf(txtDealValue.getText().toString()));
                    deal.setCreatedDate(txtCreatedDate.getText().toString());
                    deal.setDesc(txtDealDesc.getText().toString());

                    int res = walletRepo.editDeal(deal);
                    Toast.makeText(getApplicationContext(), "edit result: " + res, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnDelete:
                    walletRepo.deleteDeal(deal);
                    Toast.makeText(getApplicationContext(), "xoa thanh cong!!", Toast.LENGTH_SHORT).show();

                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_deal_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
