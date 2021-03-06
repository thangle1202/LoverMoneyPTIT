package com.example.lovermoneyptit;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lovermoneyptit.adapter.SelectGroupPageAdapter;
import com.example.lovermoneyptit.repository.WalletRepo;

public class SelectGroupActivity extends AppCompatActivity implements
        borrowLoanFragment.OnFragmentInteractionListener,
        CashInFragment.OnFragmentInteractionListener,
        CashOutFragment.OnFragmentInteractionListener {

    static final int REQUEST_CODE_GROUP_BORROW = 4;
    static final int REQUEST_CODE_GROUP_CASH_IN = 5;
    static final int REQUEST_CODE_GROUP_CASH_OUT = 6;

    private Toolbar toolbar;
    private WalletRepo walletRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_group);

        walletRepo = new WalletRepo(this.getApplicationContext());

        toolbar = findViewById(R.id.toolbarSelectGroup);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.selectGroup);

        // view page
        ViewPager viewPager = findViewById(R.id.selectGroupPager);
        SelectGroupPageAdapter selectGroupPageAdapter = new SelectGroupPageAdapter(getSupportFragmentManager());
        selectGroupPageAdapter.add(new borrowLoanFragment(), "đi vay & cho vay");
        selectGroupPageAdapter.add(new CashInFragment(), "khoản thu");
        selectGroupPageAdapter.add(new CashOutFragment(), "khoản chi");
        viewPager.setAdapter(selectGroupPageAdapter);

        // tab layout
        TabLayout tab = findViewById(R.id.tabSelectGroup);
        tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorTabSelected));
        tab.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        tab.setupWithViewPager(viewPager);

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
        } else if (id == R.id.btnSearchGroup) {
            Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_select_group, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
