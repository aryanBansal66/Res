package com.astro.astro_guruvani_astro.OtherFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.astro.astro_guruvani_astro.Home.HomePage;
import com.astro.astro_guruvani_astro.R;

public class Terms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

    }
    @Override
    public void onBackPressed() {

        String Home=getIntent().getExtras().getString("Home");

        if(Home.equals("Home")){
            startActivity(new Intent(Terms.this, HomePage.class));
            finish();
        }else {
            startActivity(new Intent(Terms.this, com.astro.astro_guruvani_astro.OtherFiles.Settings.class));
            finish();
        }

    }
}