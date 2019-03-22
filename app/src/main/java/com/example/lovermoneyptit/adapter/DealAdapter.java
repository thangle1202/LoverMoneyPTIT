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

import java.util.List;

/**
 * Created by nguye on 3/20/2019.
 */

public class DealAdapter extends RecyclerView.Adapter {

    private List<Deal> deals;
    private Context mContext;

    public DealAdapter(List<Deal> deals, Context mContext) {
        this.deals = deals;
        this.mContext = mContext;
    }

    @Override
    public DealAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deal_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DealAdapter.MyViewHolder myViewHolder = (DealAdapter.MyViewHolder) holder;
        myViewHolder.txtCreatedDate.setText(deals.get(position).getCreatedDate().toString());
        myViewHolder.txtValue.setText(String.valueOf(deals.get(position).getValue()));
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivGroup;
        TextView txtCreatedDate;
        TextView txtValue;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivGroup = itemView.findViewById(R.id.ivGroup);
            txtCreatedDate = itemView.findViewById(R.id.txtCreatedDate);
            txtValue = itemView.findViewById(R.id.txtValue);
        }
    }
}
