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

public class Waitlist extends AppCompatActivity {
List<WaitlistModel> list;
RecyclerView waitrv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitlist);

       // list=new ArrayList<>();

        /*waitrv=findViewById(R.id.waitrv);
        waitrv.setHasFixedSize(true);
        waitrv.setLayoutManager(new LinearLayoutManager(Waitlist.this));*/


      /*  String url = "";
        RequestQueue rq = Volley.newRequestQueue(Waitlist.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String astroid=jsonObject.getString("astroid");
                        String username=jsonObject.getString("username");
                        String userid=jsonObject.getString("userid");
                        String time=jsonObject.getString("time");
                        String type=jsonObject.getString("type");
                        String status=jsonObject.getString("status");
                        String duration=jsonObject.getString("duration");
                        String Gender=jsonObject.getString("Gender");
                        String orderid=jsonObject.getString("orderid");
                        String dob=jsonObject.getString("dob");
                        String tob=jsonObject.getString("tob");
                        String pob=jsonObject.getString("pob");
                        String problem=jsonObject.getString("problem");

                       *//* WaitlistModel mm=new WaitlistModel();
                        mm.set(des);
                        mm.setTitle(title);
                        list.add(mm);
*//*
                        WaitlistAdapter adapter=new WaitlistAdapter(list);
                        waitrv.setAdapter(adapter);
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
*/
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Waitlist.this, HomePage.class));
        finish();
    }
}