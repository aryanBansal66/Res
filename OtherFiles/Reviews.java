package com.astro.astro_guruvani_astro.OtherFiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.astro.astro_guruvani_astro.Home.HomePage;
import com.astro.astro_guruvani_astro.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Reviews extends AppCompatActivity {
    ReviewAdapter adapter;
    List<ReviewModel>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        SharedPreferences sp=getSharedPreferences("Pref_forLogin", Context.MODE_PRIVATE);
        String phonenumber=sp.getString("emailph","0");

        RecyclerView recyclerView=findViewById(R.id.ReviewRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(Reviews.this,RecyclerView.VERTICAL,false));
        recyclerView.setHasFixedSize(true);

        list=new ArrayList<>();
        String url = "https://adhaan.org/APIs/WebClass1.asmx/GetReview?token=ATRWS556Tmarlao&astroid="+phonenumber+"";
        RequestQueue rq = Volley.newRequestQueue(Reviews.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        int id=jsonObject.getInt("id");
                        String username=jsonObject.getString("username");
                        String userid=jsonObject.getString("userid");
                        String service=jsonObject.getString("Comment");
                        String starrating=jsonObject.getString("Starrating");
                        String astroid=jsonObject.getString("astroid");
                        String Reply=jsonObject.getString("Reply");

                        ReviewModel mm=new ReviewModel();
                        mm.setUsername(username);
                        mm.setComment(service);
                        mm.setId(id);
                        mm.setStarRating(starrating);
                        mm.setAstroId(astroid);
                        mm.setReply(Reply);
                        list.add(mm);

                        adapter=new ReviewAdapter(list,Reviews.this);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });
        rq.add(stringRequest);

        TextView t16=findViewById(R.id.textView16);
        TextView t17=findViewById(R.id.textView17);
        TextView t18=findViewById(R.id.textView18);
        TextView t19=findViewById(R.id.textView19);
        TextView t20=findViewById(R.id.textView20);

        FilterClick(t16,"1");
        FilterClick(t17,"2");
        FilterClick(t18,"3");
        FilterClick(t19,"4");
        FilterClick(t20,"5");

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Reviews.this, HomePage.class));
        finish();
    }

    private void filter(String text) {
        ArrayList<ReviewModel> filteredList = new ArrayList<>();
        for (ReviewModel item : list) {
            if (item.getStarRating().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    void FilterClick(TextView tt,String count){
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter(count);
            }
        });
    }

}