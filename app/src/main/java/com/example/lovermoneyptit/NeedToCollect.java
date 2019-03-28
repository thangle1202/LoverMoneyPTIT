package com.example.lovermoneyptit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class NeedToCollect extends Fragment {


    Button btnAddCollect;
    View view;
    public NeedToCollect() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_need_to_collect, container, false);
        btnAddCollect=view.findViewById(R.id.btn_add_collect);
        btnAddCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddPayActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
