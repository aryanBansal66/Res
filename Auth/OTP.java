package com.astro.astro_guruvani_astro.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.astro.astro_guruvani_astro.APIClass.APIs;
import com.astro.astro_guruvani_astro.OtherFiles.Profile;
import com.astro.astro_guruvani_astro.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class OTP extends AppCompatActivity {

    MaterialButton bb;
    TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        String getPH=getIntent().getExtras().getString("Phone");
        String TYPE=getIntent().getExtras().getString("TYPE");
        String Status=getIntent().getExtras().getString("Status");

        TextInputEditText otp=findViewById(R.id.OTP);
        phone=findViewById(R.id.textViewphone);
        phone.setText("OTP sent to "+getPH);
        bb=findViewById(R.id.buttonsubmit);

        if(Status.equals("UpdatePhone")){
            String url = "https://2factor.in/API/V1/d95d758b-92a4-11ed-9158-0200cd936042/SMS/+91"+getPH+"/AUTOGEN2/OTP1";
            RequestQueue rq = Volley.newRequestQueue(OTP.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String OTP =jsonObject.getString("OTP");

                        bb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(OTP.equals(otp.getText().toString())){
                                    String url = "https://adhaan.org/APIs/WebClass1.asmx/UpdatePhoneAstro?token=ATRWS556Tmarlao&phone="+getPH+"";
                                    RequestQueue rq = Volley.newRequestQueue(OTP.this);
                                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                String msg = jsonObject.getString("message");
                                                SharedPreferences sp=getSharedPreferences("Pref_forLogin",Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor=sp.edit();
                                                editor.putString("emailph",msg);
                                                editor.commit();
                                                Intent in=new Intent(OTP.this,Profile.class);
                                                in.putExtra("Off","OFF");
                                                startActivity(in);
                                                finish();
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(OTP.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    rq.add(stringRequest);
                                }else {
                                    Toast.makeText(OTP.this, "Invalid OTP!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(OTP.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            rq.add(stringRequest);

        }

        else {
            APIs apis=new APIs();
            String url = "https://2factor.in/API/V1/d95d758b-92a4-11ed-9158-0200cd936042/SMS/+91"+getPH+"/AUTOGEN2/OTP1";
            RequestQueue rq = Volley.newRequestQueue(OTP.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String OTP =jsonObject.getString("OTP");
                        bb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(TYPE.equals("PHONE")) {
                                    if (OTP.equals(otp.getText().toString())) {
                                        apis.Register("ATRWS556Tmarlao", "Phone", getPH, OTP.this);
                                    } else {
                                        Toast.makeText(OTP.this, "Invalid OTP!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(OTP.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            rq.add(stringRequest);

        }


        }
}