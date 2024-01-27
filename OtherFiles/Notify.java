package com.astro.astro_guruvani_astro.OtherFiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Notify extends AppCompatActivity {
    RecyclerView notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        SharedPreferences sp=getSharedPreferences("Pref_forLogin", Context.MODE_PRIVATE);
        String phonenumber=sp.getString("emailph","0");

        notify=findViewById(R.id.NotifyRV);
        notify.setHasFixedSize(true);
        notify.setLayoutManager(new LinearLayoutManager(Notify.this));

        String url = "https://adhaan.org/APIs/WebClass1.asmx/GetFollowers?token=ATRWS556Tmarlao&astroid="+phonenumber+"";
        RequestQueue rq = Volley.newRequestQueue(Notify.this);
        List<NotifyClientModel>list=new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String Followerid=jsonObject.getString("Followerid");
                        String Photo="https://adhaan.org/Images/"+jsonObject.getString("Photo");

                        NotifyClientModel mm=new NotifyClientModel();
                        mm.setFollowerid(Followerid);
                        mm.setPhoto(Photo);
                        list.add(mm);
                        NotifyAdapter adapter=new NotifyAdapter(list,Notify.this);
                        notify.setAdapter(adapter);
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
        startActivity(new Intent(Notify.this, HomePage.class));
        overridePendingTransition(0,0);
        finish();
    }
}