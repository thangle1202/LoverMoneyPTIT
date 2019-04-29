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
import com.example.lovermoneyptit.models.Debt;

import java.util.ArrayList;

public class AdapterRecyclerDebt extends RecyclerView.Adapter{


    ArrayList<Debt> listDebt;
    Context context;

    public AdapterRecyclerDebt(ArrayList<Debt> list, Context context) {
        this.listDebt=list;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgView;
        public TextView name;
        public TextView amount;
        public TextView date;
        public ViewHolder(View itemView) {
            super(itemView);
            this.amount=itemView.findViewById(R.id.item_amount);
            this.date=itemView.findViewById(R.id.item_date);
            this.imgView=itemView.findViewById(R.id.item_image);
            this.name=itemView.findViewById(R.id.item_person_name);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_itemview_debt,parent,false
        );
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Debt debt=listDebt.get(position);
        AdapterRecyclerDebt.ViewHolder holder1= (ViewHolder) holder;
        holder1.name.setText(debt.getPersonName().toString());
        holder1.date.setText(debt.getCreatedDate());
        holder1.amount.setText(String.valueOf(debt.getValue()));

    }

    @Override
    public int getItemCount() {
        return listDebt.size();
    }
}
