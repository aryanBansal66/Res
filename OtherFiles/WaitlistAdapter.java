package com.astro.astro_guruvani_astro.OtherFiles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astro.astro_guruvani_astro.R;

import java.util.List;

public class WaitlistAdapter extends RecyclerView.Adapter<WaitlistAdapter.ViewHolder> {

    private List<WaitlistModel> list;

    public WaitlistAdapter(List<WaitlistModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WaitlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.waitlistlayout, null); // Assuming you have a layout file named waitlist_layout.xml
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaitlistAdapter.ViewHolder holder, int position) {
        WaitlistModel model = list.get(position);

        //holder.astroidText.setText("Astroid: " + model.getAstroid());
        holder.usernameText.setText("Username: " + model.getUsername());
        //holder.useridText.setText("User ID: " + model.getUserid());
        holder.timeText.setText("Time: " + model.getTime());
        holder.typeText.setText("Type: " + model.getType());
        holder.statusText.setText("Status: " + model.getStatus());
        holder.durationText.setText("Duration: " + model.getDuration());
       /* holder.genderText.setText("Gender: " + model.getGender());
        holder.orderidText.setText("Order ID: " + model.getOrderid());
        holder.dobText.setText("DOB: " + model.getDob());
        holder.tobText.setText("TOB: " + model.getTob());
        holder.pobText.setText("POB: " + model.getPob());
        holder.problemText.setText("Problem: " + model.getProblem());*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView astroidText;
        private TextView usernameText;
        private TextView useridText;
        private TextView timeText;
        private TextView typeText;
        private TextView statusText;
        private TextView durationText;
        private TextView genderText;
        private TextView orderidText;
        private TextView dobText;
        private TextView tobText;
        private TextView pobText;
        private TextView problemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize TextViews
            usernameText = itemView.findViewById(R.id.NameandID);
            timeText = itemView.findViewById(R.id.Date);
            typeText = itemView.findViewById(R.id.Type);
            statusText = itemView.findViewById(R.id.status);
            durationText = itemView.findViewById(R.id.duration);
        }
    }
}
