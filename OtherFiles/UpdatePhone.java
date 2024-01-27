package com.astro.astro_guruvani_astro.OtherFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.astro.astro_guruvani_astro.Auth.OTP;
import com.astro.astro_guruvani_astro.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class UpdatePhone extends AppCompatActivity {

    TextInputEditText oldph,newph;
    MaterialButton bb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phone);

        SharedPreferences sp=getSharedPreferences("Pref_forLogin", Context.MODE_PRIVATE);

        oldph=findViewById(R.id.phoneold);
        newph=findViewById(R.id.phonenew);
        bb=findViewById(R.id.button);

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent in=new Intent(UpdatePhone.this, OTP.class);
               in.putExtra("Status","UpdatePhone");
               in.putExtra("Phone",newph.getText().toString());
               in.putExtra("TYPE","PHONE");
               startActivity(in);
               finish();

                SharedPreferences.Editor editor=sp.edit();
                editor.remove("emailph");
                editor.commit();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UpdatePhone.this, com.astro.astro_guruvani_astro.OtherFiles.Settings.class));
        finish();
    }
}