package com.astro.astro_guruvani_astro.OtherFiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astro.astro_guruvani_astro.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.ViewHolder> {

    List<NotifyClientModel> list;
    Context cc;

    public FollowersAdapter(List<NotifyClientModel> list, Context cc) {
        this.list = list;
        this.cc = cc;
    }

    @NonNull
    @Override
    public FollowersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.followers_lyout,null);
        return new FollowersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowersAdapter.ViewHolder holder, int position) {
        NotifyClientModel mm=list.get(position);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        holder.date.setText(currentDate);
        holder.id.setText(list.get(position).getFollowerid());
        Picasso.get().load(list.get(position).getPhoto()).into(holder.ci);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,date;
        CircleImageView ci;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.ID);
            ci=itemView.findViewById(R.id.imageView3);
            date=itemView.findViewById(R.id.textView10);
        }
    }
}

