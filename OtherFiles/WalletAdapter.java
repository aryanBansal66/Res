package com.astro.astro_guruvani_astro.OtherFiles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astro.astro_guruvani_astro.R;

import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {

    List<WalletModel>list;

    public WalletAdapter(List<WalletModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WalletAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.wallet,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletAdapter.ViewHolder holder, int position) {

        WalletModel wm=list.get(position);

        holder.orderid.setText(list.get(position).getOrderid());
        holder.moneytype.setText(list.get(position).getMoneytype());
        holder.money.setText(list.get(position).getMoney());
        holder.moneyreason.setText(list.get(position).getMoneyreason());
        holder.userid.setText(list.get(position).getUserid());
        holder.date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderid,moneytype,money,moneyreason,userid,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderid=itemView.findViewById(R.id.orderid);
            moneytype=itemView.findViewById(R.id.moneytype);
            money=itemView.findViewById(R.id.money);
            moneyreason=itemView.findViewById(R.id.moneyreason);
            userid=itemView.findViewById(R.id.userid);
            date=itemView.findViewById(R.id.date);
        }
    }
}
