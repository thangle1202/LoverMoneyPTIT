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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovermoneyptit.api.APIService;
import com.example.lovermoneyptit.api.APIUtils;
import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.repository.WalletRepo;

import java.text.ParseException;
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
    private APIService mAPIService;
    private WalletRepo walletRepo;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();

        // set current user
        View headerLayout = navigationView.getHeaderView(0);
        txtCurrentUser = headerLayout.findViewById(R.id.txtCurrentUser);
        preferences = getSharedPreferences("user", MODE_PRIVATE);
        String currentUsername = preferences.getString("username", "");
        if(currentUsername != null){
            txtCurrentUser.setText(currentUsername);
        }

        // sync data
        walletRepo = new WalletRepo(getApplicationContext());
        mAPIService = APIUtils.getAPIService();

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
            case R.id.nav_home:
                toolbar.setTitle("MoneyLover");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();
                break;
            case R.id.nav_manage_money:
                toolbar.setTitle("giao dich");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ManageMoneyFragment()).commit();
                break;
            case R.id.nav_chart:
                toolbar.setTitle("xu hướng");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ChartFragment()).commit();
                break;
            case R.id.nav_wallet:
                toolbar.setTitle("quản lý ví");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new WalletFragment()).commit();
                break;
            case R.id.nav_debt:
                toolbar.setTitle("quản lý nợ");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new DebtFragment()).commit();
                break;
            case R.id.nav_syncData:
                try {
                    List<Deal> deals = walletRepo.getAllDealForSyncData();
                    if (deals.size() > 0) {
                        syncData(deals);
                    } else {
                        Toast.makeText(getApplicationContext(), "không có dữ liệu!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case R.id.nav_syncData:

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    // sync data
    public void syncData(List<Deal> dealList) {
        mAPIService.syncDeal(dealList).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "đồng bộ thành công: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "đồng bộ thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
