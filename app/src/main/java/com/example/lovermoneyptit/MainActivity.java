package com.example.lovermoneyptit;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

//import com.example.lovermoneyptit.api.APIService;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        borrowLoanFragment.OnFragmentInteractionListener,
        CashInFragment.OnFragmentInteractionListener,
        CashOutFragment.OnFragmentInteractionListener{

    public static Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    // call
    //private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ManageMoneyFragment()).commit();

    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawer_nav);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
//            case R.id.nav_home:
//                toolbar.setTitle("MoneyLover");
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();
//                break;
            case R.id.nav_manage_money:
                toolbar.setTitle("Giao Dịch");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ManageMoneyFragment()).commit();
                break;
            case R.id.nav_chart:
                toolbar.setTitle("Xu Hướng");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ChartFragment()).commit();
                break;
            case R.id.nav_wallet:
                toolbar.setTitle("Quản Lý Ví");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new WalletFragment()).commit();
                break;
            case R.id.nav_debt:
                toolbar.setTitle("Quản Lý Nợ");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new DebtFragment()).commit();
                break;
            case R.id.nav_account:
                toolbar.setTitle("Tài Khoản");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new AccountFragment()).commit();
                break;
            case R.id.nav_syncData:

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
