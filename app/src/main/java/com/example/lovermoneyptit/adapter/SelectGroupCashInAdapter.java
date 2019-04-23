package com.example.lovermoneyptit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovermoneyptit.R;
import com.example.lovermoneyptit.models.Group;

import java.util.List;

public class SelectGroupCashInAdapter extends RecyclerView.Adapter {

    private List<Group> groups;
    private Context mContext;
    private View.OnClickListener mOnClickListener;

    public SelectGroupCashInAdapter(List<Group> groups, Context mContext) {
        this.groups = groups;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SelectGroupCashInAdapter.MyViewHolder myViewHolder = (SelectGroupCashInAdapter.MyViewHolder) holder;
        myViewHolder.txtGroupName.setText(groups.get(position).getGroupName());
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public View.OnClickListener getmOnClickListener() {
        return mOnClickListener;
    }

    public void setmOnClickListener(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgGroup;
        TextView txtGroupName;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgGroup = itemView.findViewById(R.id.imgGroup);
            txtGroupName = itemView.findViewById(R.id.txtGroupName);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnClickListener);
        }
    }
}
