package com.astro.astro_guruvani_astro.OtherFiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.List;

public class Wallet extends AppCompatActivity {
RecyclerView wallettransactions;
TextView avlbalance,pg,sub,tds,GST,payable;
TextView t4;
List<WalletModel>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        avlbalance=findViewById(R.id.avlbalance);
        pg=findViewById(R.id.pg);
        sub=findViewById(R.id.sub);
        tds=findViewById(R.id.tds);
        payable=findViewById(R.id.payable);
        GST=findViewById(R.id.GST);
        wallettransactions=findViewById(R.id.WalletTransactions);
        list=new ArrayList<>();
        t4=findViewById(R.id.textView4);
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Wallet.this, HomePage.class));
        overridePendingTransition(0,0);
        finish();
    }

    public void WalletBalance(TextView tt,TextView tt2){
        String url = "";
        RequestQueue rq = Volley.newRequestQueue(Wallet.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String Balance=jsonObject.getString("Balance");
                        String Payable=jsonObject.getString("Payable");
                        tt.setText(Balance);
                        tt2.setText(Payable);
  }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(stringRequest);


    }

    public void WalletTransactions(){
        String url = "";
        RequestQueue rq = Volley.newRequestQueue(Wallet.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String Tid=jsonObject.getString("Transactionid");
                        String Tamt=jsonObject.getString("Transactionamt");
                        String Tname=jsonObject.getString("TransactionName");
                        String userid=jsonObject.getString("userid");
                        String Tdate=jsonObject.getString("transactiondate");

                        WalletModel mm=new WalletModel();
                        mm.setDate(Tdate);
                        mm.setMoney(Tamt);
                        mm.setMoneyreason(Tname);
                        mm.setOrderid(Tid);
                        mm.setUserid(userid);
                        list.add(mm);

                        WalletAdapter walletAdapter=new WalletAdapter(list);
                        wallettransactions.setAdapter(walletAdapter);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(stringRequest);
    }

    private void showDatePickerDialog() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Wallet.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = String.format("%02d-%02d-%04d", day, month + 1, year);
                        t4.setText(selectedDate);
                    }
                },
                year, // Initial year
                month, // Initial month
                day // Initial day
        );

        // Show the DatePickerDialog
        datePickerDialog.show();
    }
}