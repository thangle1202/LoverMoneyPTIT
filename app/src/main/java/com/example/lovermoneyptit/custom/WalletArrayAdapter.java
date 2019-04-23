package com.example.lovermoneyptit.custom;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovermoneyptit.R;
import com.example.lovermoneyptit.models.Wallet;

import java.util.ArrayList;
import java.util.List;

public class WalletArrayAdapter extends ArrayAdapter<Wallet> {

    Activity context = null;
    List<Wallet> wallets = null;
    int layoutId;

    public WalletArrayAdapter(@NonNull Activity context, int resource, @NonNull List<Wallet> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutId = resource;
        this.wallets = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        convertView = inflater.inflate(layoutId, null);

        ImageView imgView = convertView.findViewById(R.id.imgItemWallet);
        imgView.setImageResource(R.drawable.ic_manage_money);

        TextView txtWalletName = convertView.findViewById(R.id.txtItemWalletName);
        TextView txtWalletBalance = convertView.findViewById(R.id.txtItemWalletBalance);

        final Wallet wallet = wallets.get(position);
        txtWalletName.setText(wallet.getWalletName());
        txtWalletBalance.setText(String.valueOf(wallet.getBalance()));

        return convertView;
    }
}
