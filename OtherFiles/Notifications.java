package com.astro.astro_guruvani_astro.OtherFiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.astro.astro_guruvani_astro.APIClass.NotificationModel;
import com.astro.astro_guruvani_astro.Home.HomePage;
import com.astro.astro_guruvani_astro.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity {
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        String Status=getIntent().getExtras().getString("Status");

        SharedPreferences sp=getSharedPreferences("Pref_forLogin", Context.MODE_PRIVATE);
        String phonenumber=sp.getString("emailph","0");


        recyclerView=findViewById(R.id.recynot);
        TextView t3 =findViewById(R.id.textView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(Notifications.this));
        recyclerView.setHasFixedSize(true);


        if(Status.equals("Notifications")){
            t3.setText("Notifications");
            List<NotificationModel>list=new ArrayList<>();

            String url = "https://adhaan.org/APIs/WebClass1.asmx/GetNotifications?token=ATRWS556Tmarlao&fortype="+"Astrologer"+"";
            RequestQueue rq = Volley.newRequestQueue(Notifications.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            String title=jsonObject.getString("Notification");
                            String des=jsonObject.getString("MSG");

                            NotificationModel mm=new NotificationModel();
                            mm.setDes(des);
                            mm.setTitle(title);
                            list.add(mm);

                            NotificationAdapter adapter=new NotificationAdapter(list);
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

        else {
            t3.setText("Warnings");
            List<NotificationModel>list=new ArrayList<>();
            String url = "https://adhaan.org/APIs/WebClass1.asmx/GetWarnings?token=ATRWS556Tmarlao&astroid="+phonenumber+"";
            RequestQueue rq = Volley.newRequestQueue(Notifications.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            String title=jsonObject.getString("WarningNotification");
                            String des=jsonObject.getString("WarningMSG");

                            NotificationModel mm=new NotificationModel();
                            mm.setDes(des);
                            mm.setTitle(title);
                            list.add(mm);

                            NotificationAdapter adapter=new NotificationAdapter(list);
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
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Notifications.this, HomePage.class));
        overridePendingTransition(0,0);
        finish();
    }
}