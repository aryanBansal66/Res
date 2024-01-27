package com.astro.astro_guruvani_astro.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.astro.astro_guruvani_astro.R;
import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingConfig;
import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingFragment;

public class LiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        String name=getIntent().getExtras().getString("Name");
        String roomcode=getIntent().getExtras().getString("Roomcode");
        String userid=getIntent().getExtras().getString("ID");

        addFragment(name,userid,roomcode);
    }
    public void addFragment(String name,String userid,String roomcode) {
        boolean isHost = true;
        ZegoUIKitPrebuiltLiveStreamingConfig config;
        if (isHost) {
            config = ZegoUIKitPrebuiltLiveStreamingConfig.host();
        } else {
            config = ZegoUIKitPrebuiltLiveStreamingConfig.audience();
        }
        ZegoUIKitPrebuiltLiveStreamingFragment fragment = ZegoUIKitPrebuiltLiveStreamingFragment.newInstance(
                53739149, "2672188a0f50f74e4b116eacd325d79ffe67fb22c02f80a9f239ee2ad63f249c", userid, name, roomcode, config);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(LiveActivity.this,HomePage.class));
        overridePendingTransition(0,0);
        finish();
    }
}