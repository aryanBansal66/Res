package com.astro.astro_guruvani_astro.OtherFiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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

public class Training extends AppCompatActivity {
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        recyclerView=findViewById(R.id.recyTraining);
        recyclerView.setLayoutManager(new LinearLayoutManager(Training.this));
        recyclerView.setHasFixedSize(true);

            List<TrainingModel> list=new ArrayList<>();
            String url = "https://adhaan.org/APIs/WebClass1.asmx/Videos?token=ATRWS556Tmarlao";
            RequestQueue rq = Volley.newRequestQueue(Training.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            String title=jsonObject.getString("VideoTitle");
                            String videolink=jsonObject.getString("VideoLink");

                            TrainingModel mm=new TrainingModel();
                            mm.setLink(videolink);
                            mm.setTitle(title);
                            list.add(mm);

                            TrainingAdapter adapter=new TrainingAdapter(Training.this,list);
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

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String value=getIntent().getExtras().getString("Home");

        if(value.equals("Home")){
            startActivity(new Intent(Training.this, HomePage.class));

        }else {
            startActivity(new Intent(Training.this,Settings.class));
        }
        overridePendingTransition(0,0);
        finish();
    }
}