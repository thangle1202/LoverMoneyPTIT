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
import com.example.lovermoneyptit.models.Wallet;

import java.util.List;

public class SelectWalletAdapter extends RecyclerView.Adapter{

    private List<Wallet> wallets;
    private Context mContext;

    public SelectWalletAdapter(List<Wallet> wallets, Context mContext) {
        this.wallets = wallets;
        this.mContext = mContext;
    }

    @Override
    public SelectWalletAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_row, parent, false);
        return new SelectWalletAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SelectWalletAdapter.MyViewHolder myViewHolder = (SelectWalletAdapter.MyViewHolder) holder;
        myViewHolder.txtWalletName.setText(wallets.get(position).getWalletName());
        myViewHolder.txtAmount.setText(String.valueOf(wallets.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return wallets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtWalletName;
        TextView txtAmount;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtWalletName = itemView.findViewById(R.id.txtWalletName);
            txtAmount = itemView.findViewById(R.id.txtAmount);
        }
    }

}
