package com.example.lovermoneyptit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovermoneyptit.R;
import com.example.lovermoneyptit.models.Collect;
import com.example.lovermoneyptit.models.Debt;
import com.example.lovermoneyptit.repository.WalletRepo;

import java.util.ArrayList;

public class AdapterRecyclerPay extends RecyclerView.Adapter{
    ArrayList<Collect> listDebt;
    Context context;
    WalletRepo walletRepo;

    public AdapterRecyclerPay(ArrayList<Collect> list, Context context) {
        this.listDebt=list;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgView;
        public TextView name;
        public TextView amount;
        public TextView date;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.amount=itemView.findViewById(R.id.item_amount);
            this.date=itemView.findViewById(R.id.item_date);
            this.imgView=itemView.findViewById(R.id.item_image);
            this.name=itemView.findViewById(R.id.item_person_name);
            this.linearLayout=itemView.findViewById(R.id.re_linear_adapt);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_itemview_debt,parent,false
        );
        walletRepo=new WalletRepo(context);
        final AdapterRecyclerPay.ViewHolder holder=new AdapterRecyclerPay.ViewHolder(view);

        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                int x=listDebt.get(holder.getAdapterPosition()).getId();
                walletRepo.deleteDebtById(x);
                listDebt.remove(holder.getAdapterPosition());
                notifyDataSetChanged();

                Toast.makeText(context, "Click! jjjjjj "+x, Toast.LENGTH_SHORT).show();

                return true;
            }
        });
//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int x=listDebt.get(holder.getAdapterPosition()).getId();
//                walletRepo.deleteDebtById(x);
//                listDebt.remove(holder.getAdapterPosition());
//                notifyDataSetChanged();
//
//                Toast.makeText(context, "Click! "+x, Toast.LENGTH_SHORT).show();
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Collect debt=listDebt.get(position);
        AdapterRecyclerPay.ViewHolder holder1= (AdapterRecyclerPay.ViewHolder) holder;
        holder1.name.setText(debt.getPersonName());
        holder1.date.setText(debt.getCreatedDate());
        holder1.amount.setText(String.valueOf(debt.getValue()));

    }

    @Override
    public int getItemCount() {
        return listDebt.size();
    }
}
