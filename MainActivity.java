package com.astro.astro_guruvani_astro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.astro.astro_guruvani_astro.R;
import com.astro.astro_guruvani_astro.Auth.Login;
import com.astro.astro_guruvani_astro.Home.HomePage;
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;
import com.zegocloud.zimkit.services.ZIMKit;

public class MainActivity extends AppCompatActivity {

    //1640571651
    //3d213f4a3755054ec1e9610d169747066d05e726456f68105d87dab888015c97

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp=getSharedPreferences("Pref_forLogin", Context.MODE_PRIVATE);
        String value=sp.getString("emailph","0");

        long appId = 895689081;    // The AppID you get from ZEGOCLOUD Admin Console.
        String appSign = "5a9cafa4bcc5d6e52ecfbeb93968cdc9d0c0caf69114c068308a407937b0702f";    // The App Sign you get from ZEGOCLOUD Admin Console.
        ZIMKit.initWith(getApplication(),appId,appSign);
        // Online notification for the initialization (use the following code if this is needed).
        ZIMKit.initNotifications();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(value!="0"){
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                    finish();
                }else {
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish();
                }

            }
        },3000);

    }
}