package com.example.lovermoneyptit;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lovermoneyptit.adapter.AdapterRecyclerDebt;
import com.example.lovermoneyptit.models.Debt;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NeedToCollect extends Fragment {


    Button btnAddCollect;
    View view;
    RecyclerView recyclerView;
    ArrayList<Debt> listDebts;
    public NeedToCollect() {
        listDebts=new ArrayList<>();
        listDebts.add(new Debt(1, 20000,  1,"20/03/2019", "Vay nang lai", "Hai", 1));
        listDebts.add(new Debt(1, 20000,  1,"20/03/2019", "Vay nang lai", "Hai", 1));
        listDebts.add(new Debt(1, 20000,  1,"20/03/2019", "Vay nang lai", "Hai", 1));
        listDebts.add(new Debt(1, 20000,  1,"20/03/2019", "Vay nang lai", "Hai", 1));
        listDebts.add(new Debt(1, 20000,  1,"20/03/2019", "Vay nang lai", "Hai", 1));
        listDebts.add(new Debt(1, 20000,  1,"20/03/2019", "Vay nang lai", "Hai", 1));
        listDebts.add(new Debt(1, 20000,  1,"20/03/2019", "Vay nang lai", "Hai", 1));
        listDebts.add(new Debt(1, 20000,  1,"20/03/2019", "Vay nang lai", "Hai", 1));
        listDebts.add(new Debt(1, 20000,  1,"20/03/2019", "Vay nang lai", "Hai", 1));
        listDebts.add(new Debt(1, 20000,  1,"20/03/2019", "Vay nang lai", "Hai", 1));
        listDebts.add(new Debt(1, 20000,  1,"20/03/2019", "Vay nang lai", "Hai", 1));

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_need_to_collect, container, false);
        recyclerView=view.findViewById(R.id.recycler_collect);
        btnAddCollect=view.findViewById(R.id.btn_add_collect);
        btnAddCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddPayActivity.class);
                startActivity(intent);
            }
        });
        AdapterRecyclerDebt adapter=new AdapterRecyclerDebt(listDebts,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
