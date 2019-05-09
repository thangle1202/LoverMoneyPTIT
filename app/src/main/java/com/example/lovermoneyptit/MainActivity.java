package com.example.lovermoneyptit;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovermoneyptit.api.MoneyService;
import com.example.lovermoneyptit.api.APIUtils;
import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.models.Group;
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.repository.WalletRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        borrowLoanFragment.OnFragmentInteractionListener,
        CashInFragment.OnFragmentInteractionListener,
        CashOutFragment.OnFragmentInteractionListener {

    public static Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView txtCurrentUser;

    // call service
    private MoneyService mMoneyService;
    private WalletRepo walletRepo;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        setTitle("Giao Dịch");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ManageMoneyFragment()).commit();

        // set current user
        View headerLayout = navigationView.getHeaderView(0);
        txtCurrentUser = headerLayout.findViewById(R.id.txtCurrentUser);
        preferences = getSharedPreferences("user", MODE_PRIVATE);
        String currentUsername = preferences.getString("username", "");
        if (currentUsername != null) {
            txtCurrentUser.setText(currentUsername);
        }

        // sync data
        walletRepo = new WalletRepo(getApplicationContext());
        mMoneyService = APIUtils.getAPIService();

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
            case R.id.nav_syncData:
                try {
                    List<Wallet> wallets = walletRepo.getAllWallets();
                    for(Wallet wallet : wallets){
                        Log.d("id wallet", "" + wallet.getId());
                    }
                    if(wallets.size() > 0){
                        syncWallet(wallets);
                    }
                    List<Group> groups = walletRepo.getAllGroup();
                    if(groups.size() > 0){
                        syncGroup(groups);
                    }
//                    List<Deal> deals = walletRepo.getAllDealForSyncData();
                    List<Deal> deals = walletRepo.getAllDeal();
                    if (deals.size() > 0) {
                        syncData(deals);
                    } else {
                        Toast.makeText(getApplicationContext(), "không có dữ liệu!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    // sync data
    public void syncData(List<?> dealList) {

        // sync deal
        mMoneyService.syncDeal((List<Deal>) dealList).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "đồng bộ thành công: " + response.code(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(), "đồng bộ thành công: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "đồng bộ thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void syncGroup(List<Group> groups) {
        // sync group
        mMoneyService.syncGroup(groups).enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "đồng bộ thành công: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "đồng bộ thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void syncWallet(List<Wallet> wallets) {
        // sync wallet
        mMoneyService.syncWallet(wallets).enqueue(new Callback<Wallet>() {
            @Override
            public void onResponse(Call<Wallet> call, Response<Wallet> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "đồng bộ thành công: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Wallet> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "đồng bộ thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
