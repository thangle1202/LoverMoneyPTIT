package com.example.lovermoneyptit;

import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class AddDealActivity extends AppCompatActivity implements SelectWalletFragment.OnFragmentInteractionListener,
        SelectGroupFragment.OnFragmentInteractionListener,
        borrowLoanFragment.OnFragmentInteractionListener,
        CashOutFragment.OnFragmentInteractionListener,
        CashInFragment.OnFragmentInteractionListener,
        AddDealFragment.OnFragmentInteractionListener,
        AddGroupFragment.OnFragmentInteractionListener {

    private Toolbar toolbarAddDeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deal);

        // toolbar
        toolbarAddDeal = findViewById(R.id.toolbarAddDeal);
        setSupportActionBar(toolbarAddDeal);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.addDeal);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // default fragment in AddDealActivity
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.add_deal_container, new AddDealFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
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
        } else if(id == R.id.btnSaveDeal){
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
