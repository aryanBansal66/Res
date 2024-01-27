package com.astro.astro_guruvani_astro.OtherFiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astro.astro_guruvani_astro.R;

import java.util.List;

public class CallHistoryAdapter extends RecyclerView.Adapter<CallHistoryAdapter.ViewHolder> {

    List<CallHistoryModel> list;
    Context cc;

    public CallHistoryAdapter(List<CallHistoryModel> list, Context cc) {
        this.list = list;
        this.cc = cc;
    }

    @NonNull
    @Override
    public CallHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.callhistorylayout,null);
        return new CallHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallHistoryAdapter.ViewHolder holder, int position) {
        CallHistoryModel mm = list.get(position);
        holder.t2.setText(list.get(position).getOrderid());
        holder.t3.setText(list.get(position).getUsername()+" ("+list.get(position).getUserid()+")");
        holder.t4.setText(list.get(position).getGender());
        holder.t5.setText(list.get(position).getDob());
        holder.t6.setText(list.get(position).getPob());
        holder.t7.setText(list.get(position).getProblem());
        holder.t8.setText(list.get(position).getTime());
        holder.t9.setText(list.get(position).getDuration());
        holder.t10.setText(list.get(position).getRate());
        holder.t11.setText(list.get(position).getStatus());
        holder.t12.setText("Rs "+Calculate(position));
    }

    double Calculate(int position){
        double d= Double.parseDouble(list.get(position).getDuration());
        double r= Double.parseDouble(list.get(position).getRate());
        return d*r;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            t2=itemView.findViewById(R.id.orderid);
            t3=itemView.findViewById(R.id.clientnameid);
            t4=itemView.findViewById(R.id.clientgender);
            t5=itemView.findViewById(R.id.dob);
            t6=itemView.findViewById(R.id.pob);
            t7=itemView.findViewById(R.id.problem);
            t8=itemView.findViewById(R.id.time);
            t9=itemView.findViewById(R.id.duration);
            t10=itemView.findViewById(R.id.rate);
            t11=itemView.findViewById(R.id.status);
            t12=itemView.findViewById(R.id.totalbill);

        }
    }
}

