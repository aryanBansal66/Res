package com.astro.astro_guruvani_astro.OtherFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.astro.astro_guruvani_astro.Auth.Login;
import com.astro.astro_guruvani_astro.Home.HomePage;
import com.astro.astro_guruvani_astro.R;

public class Settings extends AppCompatActivity {

    LinearLayout uph,yt,terms,gallery;
Button b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences sp=getSharedPreferences("Pref_forLogin", Context.MODE_PRIVATE);

        b3=findViewById(R.id.button3);
        uph=findViewById(R.id.updateph);
        yt=findViewById(R.id.ytvideos);
        terms=findViewById(R.id.terms);
        gallery=findViewById(R.id.Gallery);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sp.edit();
                editor.remove("emailph");
                editor.commit();
                Intent in=new Intent(Settings.this, Login.class);
                startActivity(in);
                finish();
            }
        });
        uph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, com.astro.astro_guruvani_astro.OtherFiles.UpdatePhone.class));
                finish();
            }
        });

        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Settings.this,Training.class);
                in.putExtra("Home","Settings");
                startActivity(in);
                finish();
            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Settings.this,Terms.class);
                in.putExtra("Home","Settings");
                startActivity(in);
                finish();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, com.astro.astro_guruvani_astro.OtherFiles.Gallery.class));
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(Settings.this, HomePage.class));
        finish();
    }
}