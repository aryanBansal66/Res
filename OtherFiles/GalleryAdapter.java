package com.astro.astro_guruvani_astro.OtherFiles;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astro.astro_guruvani_astro.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    Context cc;
    List<GalleryModel> list;

    public GalleryAdapter(Context cc, List<GalleryModel> list) {
        this.cc = cc;
        this.list = list;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.gallery_image,null);
        return new GalleryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        GalleryModel mm=list.get(position);
        Picasso.get().load(list.get(position).getImage()).into(holder.ii);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               OpenDialog(list.get(position).getImage());
            }
        });
    }

    void OpenDialog(String link){
        Dialog dialog=new Dialog(cc);
        dialog.setContentView(R.layout.imagebox);
        dialog.show();
        ImageView image=dialog.findViewById(R.id.imageView5);
        Picasso.get().load(link).into(image);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       ImageView ii;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ii=itemView.findViewById(R.id.Image);
        }
    }
}
