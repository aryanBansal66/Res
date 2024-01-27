package com.astro.astro_guruvani_astro.OtherFiles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astro.astro_guruvani_astro.APIClass.NotificationModel;
import com.astro.astro_guruvani_astro.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    List<NotificationModel> list;

    public NotificationAdapter(List<NotificationModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.notificationlayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        NotificationModel mm=list.get(position);
        holder.nottitle.setText(list.get(position).getTitle());
        holder.notdes.setText(list.get(position).getDes());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nottitle;
        TextView notdes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nottitle=itemView.findViewById(R.id.Nottitle);
            notdes=itemView.findViewById(R.id.Notdes);
        }
    }
}
