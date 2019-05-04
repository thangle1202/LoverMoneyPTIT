package com.example.lovermoneyptit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lovermoneyptit.adapter.DealAdapter;
import com.example.lovermoneyptit.api.APIUtils;
import com.example.lovermoneyptit.api.MoneyService;
import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.repository.WalletRepo;

import java.text.ParseException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageMoneyFragment extends Fragment {

    private RecyclerView recyclerView;
    private static List<Deal> deals;
    private DealAdapter dealAdapter;
    private ImageButton btnAddDeal;

    private WalletRepo walletRepo;
    private MoneyService moneyService;

    private SharedPreferences preferences;

    // item click
    static Deal thisItem = new Deal();
    private View.OnClickListener mOnClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int pos = viewHolder.getAdapterPosition();
            thisItem = deals.get(pos);

            Intent intent = new Intent(getActivity(), DealDetailActivity.class);
            intent.putExtra("deal", thisItem);
            startActivity(intent);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_manage_money, container, false);
        recyclerView = view.findViewById(R.id.rcvDeal);

        btnAddDeal = view.findViewById(R.id.btnAddDeal);
        // deal mac dinh
        walletRepo = new WalletRepo(getContext());
        moneyService = APIUtils.getAPIService();

        btnAddDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDealActivity.class);
                startActivity(intent);
            }
        });

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        try {
            deals = walletRepo.getAllDeal();
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
            recyclerView.setLayoutManager(layoutManager);
            dealAdapter = new DealAdapter(deals, this.getContext());
            if (deals.size() == 0) {
                getDealByUserId(preferences.getInt("userId", 1));
            }
            dealAdapter.setmOnClickListener(mOnClicklistener);
            recyclerView.setAdapter(dealAdapter);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // get deal from server
    public void getDealByUserId(int userId) {
        moneyService.getDealByUserId(userId).enqueue(new Callback<List<Deal>>() {
            @Override
            public void onResponse(Call<List<Deal>> call, Response<List<Deal>> response) {
                if (response.isSuccessful()) {
                    dealAdapter.updateDeals(response.body());
                    deals = response.body();
                    walletRepo.addBatchDeal(deals);
                    Toast.makeText(getActivity(), "đồng bộ thành công: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Deal>> call, Throwable t) {
                    Toast.makeText(getActivity(), "Không thể lấy dữ liệu!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
