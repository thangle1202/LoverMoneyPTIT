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
import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.models.Debt;
import com.example.lovermoneyptit.repository.WalletRepo;

import java.util.ArrayList;

public class AdapterRecyclerDebt extends RecyclerView.Adapter{


    ArrayList<Debt> listDebt;
    Context context;
    WalletRepo walletRepo;
    public AdapterRecyclerDebt(ArrayList<Debt> list, Context context) {
        this.listDebt=list;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout linearLayout_item;
        public ImageView imgView;
        public TextView name;
        public TextView amount;
        public TextView date;
        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout_item=itemView.findViewById(R.id.re_linear_adapt);
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
        walletRepo=new WalletRepo(context);
        final ViewHolder holder=new ViewHolder(view);
        holder.linearLayout_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int x=holder.getAdapterPosition();
                listDebt.remove(x);
                Toast.makeText(context, "Just Click: "+x, Toast.LENGTH_SHORT).show();
                notifyItemRemoved(x);
                notifyItemRangeChanged(x, listDebt.size());
                walletRepo.deleteDebtById(x);
                return true;
            }
        });
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
