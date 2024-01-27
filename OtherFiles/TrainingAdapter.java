package com.astro.astro_guruvani_astro.OtherFiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astro.astro_guruvani_astro.R;

import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {
    Context cc;
    List<TrainingModel> list;

    public TrainingAdapter(Context cc, List<TrainingModel> list) {
        this.cc = cc;
        this.list = list;
    }

    @NonNull
    @Override
    public TrainingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.traininglayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingAdapter.ViewHolder holder, int position) {
        TrainingModel mm=list.get(position);
        holder.t5.setText(list.get(position).getTitle());
        holder.webView.loadData(list.get(position).getLink(),"text/html","utf-8");
        holder.webView.getSettings().setJavaScriptEnabled(true);
        holder.webView.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        WebView webView;
        TextView t5;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            webView=itemView.findViewById(R.id.WebView);
            t5=itemView.findViewById(R.id.textView5);
        }
    }
}
