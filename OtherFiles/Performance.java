package com.astro.astro_guruvani_astro.OtherFiles;

import static com.google.android.material.color.utilities.MaterialDynamicColors.error;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class Performance extends AppCompatActivity {
ImageView star1,star2,star3,star4,star5;
ProgressBar pb1,pb2,pb3,pb4,pb5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);

        TextView review=findViewById(R.id.review);
        TextView rating=findViewById(R.id.rating);

        star1=findViewById(R.id.star1);
        star2=findViewById(R.id.star2);
        star3=findViewById(R.id.star3);
        star4=findViewById(R.id.star4);
        star5=findViewById(R.id.star5);

        pb1=findViewById(R.id.progressBar);
        pb2=findViewById(R.id.progressBar2);
        pb3=findViewById(R.id.progressBar3);
        pb4=findViewById(R.id.progressBar4);
        pb5=findViewById(R.id.progressBar5);


        SharedPreferences sp=getSharedPreferences("Pref_forLogin", Context.MODE_PRIVATE);
        String phonenumber=sp.getString("emailph","0");

        RecyclerView recyclerView=findViewById(R.id.ReviewRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(Performance.this,RecyclerView.VERTICAL,false));
        recyclerView.setHasFixedSize(true);

        List<ReviewModel> list=new ArrayList<>();
        String url = "https://adhaan.org/APIs/WebClass1.asmx/GetReview?token=ATRWS556Tmarlao&astroid="+phonenumber+"";
        RequestQueue rq = Volley.newRequestQueue(Performance.this);
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

                        review.setText(""+list.size()+" Reviews");

                        ReviewAdapter adapter=new ReviewAdapter(list,Performance.this);
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

        performRequest(Performance.this,rating,phonenumber);

    }
    private void performRequest(Context cc,TextView tt,String astroid) {
        RequestQueue rq = Volley.newRequestQueue(cc);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://adhaan.org/APIs/WebClass1.asmx/GetRatingAvgForAstros?token=ATRWS556Tmarlao&astroid="+astroid+"", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String msg = jsonObject.getString("message");
                    tt.setText(msg);
                    double msgvalue=Double.parseDouble(msg);
                    if(msgvalue>0.0 && msgvalue<0.6){
                        pb1.setProgress(50);
                        pb2.setProgress(0);
                        pb3.setProgress(0);
                        pb4.setProgress(0);
                        pb5.setProgress(0);

                        star1.setColorFilter(Color.GRAY);
                        star2.setColorFilter(Color.GRAY);
                        star3.setColorFilter(Color.GRAY);
                        star4.setColorFilter(Color.GRAY);
                        star5.setColorFilter(Color.GRAY);
                    }

                    if(msgvalue>0.6 && msgvalue<1.6){
                        pb1.setProgress(100);
                        pb2.setProgress(50);
                        pb3.setProgress(0);
                        pb4.setProgress(0);
                        pb5.setProgress(0);

                        star1.setColorFilter(Color.YELLOW);
                        star2.setColorFilter(Color.GRAY);
                        star3.setColorFilter(Color.GRAY);
                        star4.setColorFilter(Color.GRAY);
                        star5.setColorFilter(Color.GRAY);
                    }

                    if(msgvalue>1.6 && msgvalue<2.6){
                        pb1.setProgress(100);
                        pb2.setProgress(100);
                        pb3.setProgress(50);
                        pb4.setProgress(0);
                        pb5.setProgress(0);

                        star1.setColorFilter(Color.YELLOW);
                        star2.setColorFilter(Color.YELLOW);
                        star3.setColorFilter(Color.GRAY);
                        star4.setColorFilter(Color.GRAY);
                        star5.setColorFilter(Color.GRAY);
                    }

                    if(msgvalue>2.6 && msgvalue<3.6){
                        pb1.setProgress(100);
                        pb2.setProgress(100);
                        pb3.setProgress(100);
                        pb4.setProgress(50);
                        pb5.setProgress(0);

                        star1.setColorFilter(Color.YELLOW);
                        star2.setColorFilter(Color.YELLOW);
                        star3.setColorFilter(Color.YELLOW);
                        star4.setColorFilter(Color.GRAY);
                        star5.setColorFilter(Color.GRAY);
                    }
                    if(msgvalue>3.6 && msgvalue<4.6){
                        pb1.setProgress(100);
                        pb2.setProgress(100);
                        pb3.setProgress(100);
                        pb4.setProgress(100);
                        pb5.setProgress(0);

                        star1.setColorFilter(Color.YELLOW);
                        star2.setColorFilter(Color.YELLOW);
                        star3.setColorFilter(Color.YELLOW);
                        star4.setColorFilter(Color.YELLOW);
                        star5.setColorFilter(Color.GRAY);
                    }

                    if(msgvalue>4.6){
                        pb1.setProgress(100);
                        pb2.setProgress(100);
                        pb3.setProgress(100);
                        pb4.setProgress(100);
                        pb5.setProgress(100);

                        star1.setColorFilter(Color.YELLOW);
                        star2.setColorFilter(Color.YELLOW);
                        star3.setColorFilter(Color.YELLOW);
                        star4.setColorFilter(Color.YELLOW);
                        star5.setColorFilter(Color.YELLOW);
                    }


                } catch (JSONException e) {
                    Toast.makeText(cc, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                Toast.makeText(cc, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    startActivity(new Intent(Performance.this, HomePage.class));
    finish();
    }
}