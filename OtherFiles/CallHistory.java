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

public class CallHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_history);

        SharedPreferences sp = getSharedPreferences("Pref_forLogin", Context.MODE_PRIVATE);
        String phonenumber = sp.getString("emailph", "0");

        RecyclerView call = findViewById(R.id.callrv);
        call.setHasFixedSize(true);
        call.setLayoutManager(new LinearLayoutManager(CallHistory.this));

        String url = "https://adhaan.org/APIs/WebClass1.asmx/GetCallHistory?token=ATRWS556Tmarlao&astroid=" + phonenumber + "";
        RequestQueue rq = Volley.newRequestQueue(CallHistory.this);
        List<CallHistoryModel> list = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String astroid = jsonObject.getString("astroid");
                        String username = jsonObject.getString("username");
                        String userid = jsonObject.getString("userid");
                        String time = jsonObject.getString("time");
                        String type = jsonObject.getString("type");
                        String status = jsonObject.getString("status");
                        String duration = jsonObject.getString("duration");
                        String gender = jsonObject.getString("Gender"); // Note: Corrected the case for "Gender"
                        String orderid = jsonObject.getString("orderid");
                        String dob = jsonObject.getString("dob");
                        String tob = jsonObject.getString("tob");
                        String pob = jsonObject.getString("pob");
                        String problem = jsonObject.getString("problem");
                        String rate = jsonObject.getString("rate");

                        CallHistoryModel mm = new CallHistoryModel();
                        mm.setAstroid(astroid);
                        mm.setUserid(userid);
                        mm.setUsername(username);
                        mm.setTime(time);
                        mm.setType(type);
                        mm.setStatus(status);
                        mm.setDuration(duration);
                        mm.setGender(gender);
                        mm.setOrderid(orderid);
                        mm.setTob(tob);
                        mm.setPob(pob);
                        mm.setDob(dob);
                        mm.setProblem(problem);
                        mm.setRate(rate);

                        list.add(mm);
                        CallHistoryAdapter adapter = new CallHistoryAdapter(list, CallHistory.this);
                        call.setAdapter(adapter);
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
        startActivity(new Intent(CallHistory.this, HomePage.class));
        overridePendingTransition(0,0);
        finish();
    }
}