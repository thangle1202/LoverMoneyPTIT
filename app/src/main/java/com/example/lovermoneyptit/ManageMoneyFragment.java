package com.example.lovermoneyptit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ManageMoneyFragment extends Fragment {
    private Button btnRevenues,btnExpenses;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_manage_money, container, false);
            btnRevenues=view.findViewById(R.id.btn_revenues);
            btnExpenses=view.findViewById(R.id.btn_expenses);
            btnRevenues.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new RevenuesFragment()).commit();
                }
            });
            btnExpenses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new ExpensesFragment()).commit();
                }
            });
        return view;

    }


}
