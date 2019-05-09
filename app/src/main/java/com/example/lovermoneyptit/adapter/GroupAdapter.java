package com.example.lovermoneyptit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovermoneyptit.R;
import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    List<Group> groups;
    Context context;

    public GroupAdapter(ArrayList<Group> groups, Context context) {
        this.groups = groups;
        this.context = context;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.group_row,parent,false);
        return new GroupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        holder.txtGroup.setText(groups.get(position).getGroupName());
 //       holder.ivGroup.setImageResource(groups.get(position).getImage());
    }

    public void updateGroups(List<Group> groupFromServer){
        this.groups = groupFromServer;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {

        ImageView ivGroup;
        TextView txtGroup;

        public GroupViewHolder(View groupView) {
            super(groupView);
            ivGroup = groupView.findViewById(R.id.imgGroup);
            txtGroup = groupView.findViewById(R.id.txtGroupName);
        }
    }
}
