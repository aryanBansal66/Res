package com.astro.astro_guruvani_astro.OtherFiles;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astro.astro_guruvani_astro.APIClass.APIs;
import com.astro.astro_guruvani_astro.Home.HomePage;
import com.astro.astro_guruvani_astro.R;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.ViewHolder> {

    List<NotifyClientModel> list;
    Context cc;

    public NotifyAdapter(List<NotifyClientModel> list, Context cc) {
        this.list = list;
        this.cc = cc;
    }

    @NonNull
    @Override
    public NotifyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.notify_layout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SharedPreferences sp = cc.getSharedPreferences("Pref_forLogin", Context.MODE_PRIVATE);
        String phonenumber = sp.getString("emailph", "0");

        NotifyClientModel mm=list.get(position);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        holder.date.setText(currentDate);
        holder.id.setText(list.get(position).getFollowerid());
        Picasso.get().load(list.get(position).getPhoto()).into(holder.ci);
        APIs apIs=new APIs();
        holder.ii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(cc);
                dialog.show();
                dialog.setContentView(R.layout.notificationbox);
                TextInputEditText title=dialog.findViewById(R.id.Title);
                TextInputEditText msg=dialog.findViewById(R.id.Message);
                Button send=dialog.findViewById(R.id.button4);
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        apIs.InsertNotificationClient("ATRWS556Tmarlao",title.getText().toString(),msg.getText().toString(),phonenumber,list.get(position).getFollowerid(),cc);
                        dialog.dismiss();
                        Toast.makeText(cc, "Notification sent!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

void Dialog(){

}

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,date;
        CircleImageView ci;
        ImageView ii;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ci=itemView.findViewById(R.id.imageView3);
            id=itemView.findViewById(R.id.clientid);
            date=itemView.findViewById(R.id.textView10);
            ii=itemView.findViewById(R.id.notify);
        }
    }
}
