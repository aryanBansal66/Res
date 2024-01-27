package com.astro.astro_guruvani_astro.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.astro.astro_guruvani_astro.R;
import com.google.android.material.tabs.TabLayout;

public class Login extends AppCompatActivity {
    TabLayout tt;
    ViewPager vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login
        );

        tt=findViewById(R.id.tablayout);
        vv=findViewById(R.id.viewpager);
        setup(vv);
        tt.setupWithViewPager(vv);

    }
    private void setup(ViewPager viewPager){
        Tabadapter tabadapter=new Tabadapter(getSupportFragmentManager());
        tabadapter.addfrag("Phone",new Phone());
        tabadapter.addfrag("Email",new Email());
        vv.setAdapter(tabadapter);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}