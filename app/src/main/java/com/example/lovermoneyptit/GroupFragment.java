package com.example.lovermoneyptit;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.lovermoneyptit.R;
import com.example.lovermoneyptit.adapter.GroupAdapter;
import com.example.lovermoneyptit.models.Group;

import java.util.ArrayList;

import io.realm.Realm;

public class GroupFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Group> groups;
    private GroupAdapter dealAdapter;
    private Realm realm;
    private ImageButton btnAddGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        btnAddGroup = view.findViewById(R.id.btnAddGroup);
        realm = Realm.getDefaultInstance();
        recyclerView = view.findViewById(R.id.rcvGroup);
        groups = new ArrayList<Group>();
        groups.add(new Group("Bill",R.mipmap.ic_group_bill));
        groups.add(new Group("Education",R.mipmap.ic_group_education));
        groups.add(new Group("Food",R.mipmap.ic_group_food));
        groups.add(new Group("Chịch chịch",R.mipmap.ic_group_love));
        groups.add(new Group("Salary",R.mipmap.ic_group_salary));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        GroupAdapter groupAdapter = new GroupAdapter(groups,this.getContext());
        recyclerView.setAdapter(groupAdapter);
        return view;
    }
}
