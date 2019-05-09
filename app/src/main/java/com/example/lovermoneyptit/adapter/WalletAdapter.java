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
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.utils.FormatUtils;

import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter {

    private List<Wallet> wallets;
    private Context mCOntext;
    private View.OnClickListener mOnClickListener;

    public WalletAdapter(List<Wallet> wallets, Context mCOntext){
        this.wallets = wallets;
        this.mCOntext = mCOntext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallet, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WalletAdapter.MyViewHolder myViewHolder = (WalletAdapter.MyViewHolder) holder;
        myViewHolder.txtItemWalletName.setText(wallets.get(position).getWalletName());
        myViewHolder.txtItemWalletBalance.setText(FormatUtils.formatVnCurrence(wallets.get(position).getBalance().toString()));
    }

    @Override
    public int getItemCount() {
        return wallets.size();
    }

    public View.OnClickListener getmOnClickListener() {
        return mOnClickListener;
    }

    public void setmOnClickListener(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public void updateWallets(List<Wallet> walletFromServer){
        this.wallets = walletFromServer;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItemWallet;
        TextView txtItemWalletName;
        TextView txtItemWalletBalance, textView5;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgItemWallet = itemView.findViewById(R.id.imgItemWallet);
            txtItemWalletName = itemView.findViewById(R.id.txtItemWalletName);
            txtItemWalletBalance = itemView.findViewById(R.id.txtItemWalletBalance);

            // onItemClickListener RecyclerView
            itemView.setTag(this);
            itemView.setOnClickListener(mOnClickListener);
        }
    }

}
