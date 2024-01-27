package com.astro.astro_guruvani_astro.OtherFiles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astro.astro_guruvani_astro.APIClass.APIs;
import com.astro.astro_guruvani_astro.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<ReviewModel> reviewList;
    Context cc;

    public ReviewAdapter(List<ReviewModel> reviewList,Context cc) {
        this.reviewList = reviewList;
        this.cc=cc;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.review, parent, false); // Assuming you have a layout file named review_item_layout.xml
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ReviewModel review = reviewList.get(position);

        holder.name.setText(reviewList.get(position).getUsername());
        holder.service.setText(reviewList.get(position).getComment());

        if(reviewList.get(position).getStarRating().equals("1")) {
            holder.i1.setColorFilter(Color.YELLOW);
            holder.i2.setColorFilter(Color.GRAY);
            holder.i3.setColorFilter(Color.GRAY);
            holder.i4.setColorFilter(Color.GRAY);
            holder.i5.setColorFilter(Color.GRAY);
        }

        if(reviewList.get(position).getStarRating().equals("2")) {
            holder.i1.setColorFilter(Color.YELLOW);
            holder.i2.setColorFilter(Color.YELLOW);
            holder.i3.setColorFilter(Color.GRAY);
            holder.i4.setColorFilter(Color.GRAY);
            holder.i5.setColorFilter(Color.GRAY);
        }

        if(reviewList.get(position).getStarRating().equals("3")) {
            holder.i1.setColorFilter(Color.YELLOW);
            holder.i2.setColorFilter(Color.YELLOW);
            holder.i3.setColorFilter(Color.YELLOW);
            holder.i4.setColorFilter(Color.GRAY);
            holder.i5.setColorFilter(Color.GRAY);
        }
        if(reviewList.get(position).getStarRating().equals("4")) {
            holder.i1.setColorFilter(Color.YELLOW);
            holder.i2.setColorFilter(Color.YELLOW);
            holder.i3.setColorFilter(Color.YELLOW);
            holder.i4.setColorFilter(Color.YELLOW);
            holder.i5.setColorFilter(Color.GRAY);
        }
        if(reviewList.get(position).getStarRating().equals("5")) {
            holder.i1.setColorFilter(Color.YELLOW);
            holder.i2.setColorFilter(Color.YELLOW);
            holder.i3.setColorFilter(Color.YELLOW);
            holder.i4.setColorFilter(Color.YELLOW);
            holder.i5.setColorFilter(Color.YELLOW);
        }
        holder.replyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reply(reviewList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,service,replyText;
        ImageView i1,i2,i3,i4,i5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            i1 = itemView.findViewById(R.id.star1);
            i2 = itemView.findViewById(R.id.star2);
            i3 = itemView.findViewById(R.id.star3);
            i4 = itemView.findViewById(R.id.star4);
            i5 = itemView.findViewById(R.id.star5);

            name = itemView.findViewById(R.id.Name);
            service = itemView.findViewById(R.id.Service);
            replyText = itemView.findViewById(R.id.Reply);
        }
    }
    void Reply(int id){

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(cc);
        bottomSheetDialog.setContentView(R.layout.replybox);
        bottomSheetDialog.show();
        TextInputEditText editText=bottomSheetDialog.findViewById(R.id.reply);
        MaterialButton Post = bottomSheetDialog.findViewById(R.id.post);
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIs apIs=new APIs();
                apIs.GiveRply(""+id,editText.getText().toString(),cc);
                bottomSheetDialog.dismiss();
            }
        });
    }

    public void filterList(ArrayList<ReviewModel> filteredList) {
        reviewList = filteredList;
        notifyDataSetChanged();
    }
}
