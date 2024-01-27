package com.astro.astro_guruvani_astro.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.astro.astro_guruvani_astro.Offers;
import com.astro.astro_guruvani_astro.OtherFiles.Performance;
import com.astro.astro_guruvani_astro.OtherFiles.Profile;
import com.astro.astro_guruvani_astro.OtherFiles.Training;
import com.astro.astro_guruvani_astro.OtherFiles.Waitlist;
import com.astro.astro_guruvani_astro.R;
import com.astro.astro_guruvani_astro.APIClass.APIs;
import com.astro.astro_guruvani_astro.OtherFiles.CallHistory;
import com.astro.astro_guruvani_astro.OtherFiles.Notifications;
import com.astro.astro_guruvani_astro.OtherFiles.Notify;
import com.astro.astro_guruvani_astro.OtherFiles.Terms;
import com.astro.astro_guruvani_astro.OtherFiles.Wallet;
import com.astro.astro_guruvani_astro.OtherFiles.followers;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;
import com.zegocloud.zimkit.common.ZIMKitRouter;
import com.zegocloud.zimkit.common.enums.ZIMKitConversationType;
import com.zegocloud.zimkit.services.ZIMKit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import im.zego.zim.enums.ZIMErrorCode;

public class HomePage extends AppCompatActivity {
ImageView bell,walleticon,contact;
LinearLayout Call,Chatbox,Report,Wallet,Waitlist,Reviews,Support,Offers,Warnings,Profile,Followers,Settings;
RelativeLayout live,performance,notify;
Button bb;
EditText ed;
Switch s1,s2,s3,s4,s5;
TextView tt1,tt2,tt3,tt4,tt5,vp,cp,callp,echat,ecall;
LinearLayout chat1,call2,video3,ecalllayout,echatlayout;
    APIs apIs;
private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        SharedPreferences sp=getSharedPreferences("Pref_forLogin", Context.MODE_PRIVATE);
        String phonenumber=sp.getString("emailph","0");
        Toast.makeText(this, ""+phonenumber , Toast.LENGTH_SHORT).show();

        Application application = getApplication();
        long appID = 53739149;
        String appSign ="2672188a0f50f74e4b116eacd325d79ffe67fb22c02f80a9f239ee2ad63f249c";

        callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, "1", "1",callInvitationConfig);

        apIs=new APIs();
        TextView t3=findViewById(R.id.textView3);
        call2=findViewById(R.id.CallLayout);
        chat1=findViewById(R.id.ChatLayout);
        video3=findViewById(R.id.VideoCallLayout);
        ecalllayout=findViewById(R.id.ecalllayout);
        echatlayout=findViewById(R.id.echatlayout);

        s1=findViewById(R.id.ChatSwitch);
        s2=findViewById(R.id.CallSwitch);
        s3=findViewById(R.id.VideoSwitch);
        s4=findViewById(R.id.EmergencyChatSwitch);
        s5=findViewById(R.id.EmergencyCallSwitch);

        Switch(s1,phonenumber);
        Switch(s2,phonenumber);
        Switch(s3,phonenumber);
        Switch(s4,phonenumber);
        Switch(s5,phonenumber);

        tt1=findViewById(R.id.ChatTime);
        tt2=findViewById(R.id.CallTime);
        tt3=findViewById(R.id.VideoTime);
        tt4=findViewById(R.id.EmergencyChatTime);
        tt5=findViewById(R.id.EmergencyCallTime);

        vp=findViewById(R.id.vp);
        cp=findViewById(R.id.cp);
        callp=findViewById(R.id.callp);
        echat=findViewById(R.id.echat);
        ecall=findViewById(R.id.ecall);

        GetAstroStatus(phonenumber);

        showDateTimePickerDialog(tt1,phonenumber);
        showDateTimePickerDialog(tt2,phonenumber);
        showDateTimePickerDialog(tt3,phonenumber);
        showDateTimePickerDialog(tt4,phonenumber);
        showDateTimePickerDialog(tt5,phonenumber);

        notify=findViewById(R.id.notify);

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,Notify.class));
                finish();
            }
        });

        live=findViewById(R.id.live);

        ImageSlider imageSlider=findViewById(R.id.image_slider);
        ImageSliderAPI(imageSlider);
        Permission();
        Chatbox=findViewById(R.id.Chat);

        String url = "https://adhaan.org/APIs/WebClass1.asmx/GetProfile?token=ATRWS556Tmarlao&id="+phonenumber+"";
        RequestQueue rq = Volley.newRequestQueue(HomePage.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String phoneNumber = jsonObject.getString("Phonenumber");
                        String email = jsonObject.getString("Email");
                        String astroImage = "https://adhaan.org/Images/"+jsonObject.getString("astroimg");
                        String astroName = jsonObject.getString("Astroname");
                        String username = jsonObject.getString("Username");
                        apIs.AddAndUpdateLive("ATRWS556Tmarlao","false",phonenumber,"",astroName,astroImage,HomePage.this,"");

                        Chatbox.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                buttonClick(phonenumber,astroImage);
                            }
                        });

                        if(username.equals("")){
                            if(phoneNumber.equals("")){
                                t3.setText(astroName+" ("+id+")\n"+email+"");
                            }else {
                                t3.setText(astroName+" ("+id+")\n"+phoneNumber+"");
                            }

                        }else {
                            if(phoneNumber.equals("")){
                                t3.setText(username+" ("+id+")\n"+email+"");
                            }else {
                                t3.setText(username+" ("+id+")\n"+phoneNumber+"");
                            }
                        }
                        live.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(astroName.equals("")){
                                    Toast.makeText(HomePage.this, "Please Add your Photo and name in your profile.", Toast.LENGTH_SHORT).show();
                                } else if (astroImage.equals("https://adhaan.org/Images/")) {
                                    Toast.makeText(HomePage.this, "Please Add your Photo and name in your profile.", Toast.LENGTH_SHORT).show();
                                } else{
                                    Dialog dialog=new Dialog(HomePage.this);
                                    dialog.show();
                                    dialog.setContentView(R.layout.live);
                                    LinearLayout normallive=dialog.findViewById(R.id.livenormal);
                                    LinearLayout poojalive=dialog.findViewById(R.id.PoojaLive);

                                    normallive.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            int randomNumber = generateRandomNumber();
                                            apIs.AddAndUpdateLive("ATRWS556Tmarlao","true",phonenumber,""+randomNumber,""+astroName,""+astroImage,HomePage.this,"NormalLive");
                                            Intent in=new Intent(HomePage.this,LiveActivity.class);
                                            in.putExtra("Roomcode",""+randomNumber);
                                            in.putExtra("Name",""+astroName);
                                            in.putExtra("ID",""+id);
                                            startActivity(in);
                                       dialog.dismiss();
                                        }
                                    });

                                    poojalive.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            int randomNumber = generateRandomNumber();
                                            apIs.AddAndUpdateLive("ATRWS556Tmarlao","true",phonenumber,""+randomNumber,""+astroName,""+astroImage,HomePage.this,"PoojaLive");
                                            Intent in=new Intent(HomePage.this,LiveActivity.class);
                                            in.putExtra("Roomcode",""+randomNumber);
                                            in.putExtra("Name",""+astroName);
                                            in.putExtra("ID",""+id);
                                            startActivity(in);
                                            finish();
                                            dialog.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        DialogforRates(""+phonenumber,chat1);
                        DialogforRates(""+phonenumber,call2);
                        DialogforRates(""+phonenumber,video3);

                        apIs.Rates(HomePage.this,phonenumber,cp,callp,vp);
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

        bb=findViewById(R.id.button2);
        ed=findViewById(R.id.feedbackEditText);
        TextView tt=findViewById(R.id.ShowPolicies);

        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent in=new Intent(HomePage.this,Terms.class);
              in.putExtra("Home","Home");
              startActivity(in);
              finish();
            }
        });

        bell=findViewById(R.id.Notification);
        walleticon=findViewById(R.id.walleticon);
        contact=findViewById(R.id.contact);

        Call=findViewById(R.id.CallHistory);

        Report=findViewById(R.id.Report);

        Wallet=findViewById(R.id.Wallet);
        Waitlist=findViewById(R.id.Waitlist);
        Reviews=findViewById(R.id.MyReviews);

        Support=findViewById(R.id.Support);
        Offers=findViewById(R.id.Offers);
        Warnings=findViewById(R.id.WARNINGS);

        Profile=findViewById(R.id.profile);
        Followers=findViewById(R.id.followers);
        Settings=findViewById(R.id.Settings);

       /* performance=findViewById(R.id.Performance);

        performance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, Performance.class));
                finish();
            }
        });*/

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(HomePage.this,Notifications.class);
                in.putExtra("Status","Notifications");
                startActivity(in);
                finish();
            }
        });
        walleticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, Wallet.class));
                finish();
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });

        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, CallHistory.class));
                finish();
            }
        });
        Wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, Wallet.class));
                finish();
            }
        });

        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  startActivity(new Intent(HomePage.this, Notifications.class));
                finish();*/
            }
        });
        Waitlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(HomePage.this, com.astro.astro_guruvani_astro.OtherFiles.Waitlist.class);
               //
                // in.putExtra("Home","Home");
                startActivity(in);
                finish();
            }
        });
        Reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, com.astro.astro_guruvani_astro.OtherFiles.Reviews.class));
                finish();
            }
        });

        Support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Dialog();
            }
        });
        Offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(HomePage.this, com.astro.astro_guruvani_astro.Offers.class);
                startActivity(in);
                finish();
            }
        });
        Warnings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(HomePage.this,Notifications.class);
                in.putExtra("Status","Warning");
                startActivity(in);
                finish();
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(HomePage.this, com.astro.astro_guruvani_astro.OtherFiles.Profile.class);
                in.putExtra("Off","Home");
                startActivity(in);
            }
        });
        Followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, followers.class));
                finish();
            }
        });

        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, com.astro.astro_guruvani_astro.OtherFiles.Settings.class));
                finish();
            }
        });

        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, com.astro.astro_guruvani_astro.OtherFiles.Performance.class));
                finish();
            }
        });

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               APIs apis=new APIs();
               apis.AddFeedback("ATRWS556Tmarlao","Astrologer",phonenumber,ed.getText().toString(),HomePage.this);
               ed.setText("");
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
    void Dialog(){
        Dialog dialog=new Dialog(HomePage.this);
        dialog.show();
        dialog.setContentView(R.layout.support);
        MaterialButton call=dialog.findViewById(R.id.Callus);
        MaterialButton email=dialog.findViewById(R.id.Mail);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
        }
    }
    private int generateRandomNumber() {
        return (int) (Math.random() * 90000) + 10000;
    }
    void ImageSliderAPI(ImageSlider imageSlider){
        List<SlideModel> imageList = new ArrayList<>(); // Create image list

        String url = "https://adhaan.org/APIs/WebClass1.asmx/ImageSlider?token=ATRWS556Tmarlao";
        RequestQueue rq = Volley.newRequestQueue(HomePage.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String Sliderimg="https://adhaan.org/Images/"+jsonObject.getString("Sliderimg");

                        imageList.add(new SlideModel(Sliderimg, ScaleTypes.FIT));
                    }
                    imageSlider.setImageList(imageList);

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
    void Permission(){
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                        } else if (report.isAnyPermissionPermanentlyDenied()) {

                        } else {

                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .check();
    }
    private void showDateTimePickerDialog(TextView tt,String phone) {
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentDateTime = Calendar.getInstance();

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        HomePage.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Calendar selectedDate = new GregorianCalendar(year, month, dayOfMonth);

                                TimePickerDialog timePickerDialog = new TimePickerDialog(
                                        HomePage.this,
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                                selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                                selectedDate.set(Calendar.MINUTE, minute);

                                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                                                String formattedDateTime = dateFormat.format(selectedDate.getTime());
                                                tt.setText(formattedDateTime);
new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {
        apIs.AddAstroStatus("ATRWS556Tmarlao",phone,""+tt1.getText().toString(),""+s1.isChecked(),""+tt2.getText().toString(),""+s2.isChecked(),""+tt3.getText().toString(),""+s3.isChecked(),""+tt4.getText().toString(),""+s4.isChecked(),""+tt5.getText().toString(),""+s5.isChecked(),HomePage.this);
    }
},3000);
                                            }
                                        },
                                        currentDateTime.get(Calendar.HOUR_OF_DAY),
                                        currentDateTime.get(Calendar.MINUTE),
                                        false
                                );

                                timePickerDialog.show();
                            }
                        },
                        currentDateTime.get(Calendar.YEAR),
                        currentDateTime.get(Calendar.MONTH),
                        currentDateTime.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });

    }
    void Switch(Switch s,String phone){
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                apIs.AddAstroStatus("ATRWS556Tmarlao",phone,""+tt1.getText().toString(),""+s1.isChecked(),""+tt2.getText().toString(),""+s2.isChecked(),""+tt3.getText().toString(),""+s3.isChecked(),""+tt4.getText().toString(),""+s4.isChecked(),""+tt5.getText().toString(),""+s5.isChecked(),HomePage.this);
            }
        });
    }
    void GetAstroStatus(String phone){
        String url = "https://adhaan.org/APIs/WebClass1.asmx/GetAstrosStatus?token=ATRWS556Tmarlao&astroid="+phone+"";
        RequestQueue rq = Volley.newRequestQueue(HomePage.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String ChatTime = jsonObject.getString("Chat");
                        String ChatStatus = jsonObject.getString("ChatStatus");
                        String Call = jsonObject.getString("Call");
                        String CallStatus = jsonObject.getString("CallStatus");
                        String Videocall = jsonObject.getString("Videocall");
                        String VideoStatus = jsonObject.getString("VideoStatus");
                        String EmergencyChat = jsonObject.getString("EmergencyChat");
                        String EmergencyChatStatus = jsonObject.getString("EmergencyChatStatus");
                        String EmergencyChatCall = jsonObject.getString("EmergencyCallStatus");
                        String EmergencyCall = jsonObject.getString("EmergencyCall");

                        tt1.setText(ChatTime);
                        tt2.setText(Call);
                        tt3.setText(Videocall);
                        tt4.setText(EmergencyChat);
                        tt5.setText(EmergencyCall);
                        Condition(ChatStatus,s1);
                        Condition(CallStatus,s2);
                        Condition(VideoStatus,s3);
                        Condition(EmergencyChatStatus,s4);
                        Condition(EmergencyChatCall,s5);
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
    void Condition(String Status,Switch s1){
        if(Status.equals("true")){
            s1.setChecked(true);
        }else {
            s1.setChecked(false);
        }
    }
    void DialogforRates(String astroid,LinearLayout ll){
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll.equals(chat1)){
                    Dialog dialog=new Dialog(HomePage.this);
                    dialog.show();
                    dialog.setContentView(R.layout.ratesdialog);
                    TextInputEditText rate=dialog.findViewById(R.id.Rate);
                    Button send=dialog.findViewById(R.id.button4);

                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            apIs.AddandUpdateRates("India : "+rate.getText().toString()+" RS",callp.getText().toString(),vp.getText().toString(),astroid,HomePage.this,ecall.getText().toString(),echat.getText().toString());
                            dialog.dismiss();
                        }
                    });

                }

                else if(ll.equals(call2)){
                        Dialog dialog=new Dialog(HomePage.this);
                        dialog.show();
                        dialog.setContentView(R.layout.ratesdialog);
                        TextInputEditText rate=dialog.findViewById(R.id.Rate);
                        Button send=dialog.findViewById(R.id.button4);
                        send.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                apIs.AddandUpdateRates(cp.getText().toString(),"India : "+rate.getText().toString()+" RS",vp.getText().toString(),astroid,HomePage.this,ecall.getText().toString(),echat.getText().toString());
                            dialog.dismiss();
                            }
                        });
                }

                else if(ll.equals(video3)){
                    Dialog dialog=new Dialog(HomePage.this);
                    dialog.show();
                    dialog.setContentView(R.layout.ratesdialog);
                    TextInputEditText rate=dialog.findViewById(R.id.Rate);
                    Button send=dialog.findViewById(R.id.button4);

                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            apIs.AddandUpdateRates(cp.getText().toString(),callp.getText().toString(),"India : "+rate.getText().toString()+" RS",astroid,HomePage.this,ecall.getText().toString(),echat.getText().toString());
                            dialog.dismiss();
                        }
                    });
                }

                else if(ll.equals(echatlayout)){
                    Dialog dialog=new Dialog(HomePage.this);
                    dialog.show();
                    dialog.setContentView(R.layout.ratesdialog);
                    TextInputEditText rate=dialog.findViewById(R.id.Rate);
                    Button send=dialog.findViewById(R.id.button4);
                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            apIs.AddandUpdateRates(cp.getText().toString(),callp.getText().toString(),vp.getText().toString(),astroid,HomePage.this,ecall.getText().toString(),"India : "+rate.getText().toString()+" RS");
                            dialog.dismiss();
                        }
                    });
                }

                else{
                    Dialog dialog=new Dialog(HomePage.this);
                    dialog.show();
                    dialog.setContentView(R.layout.ratesdialog);
                    TextInputEditText rate=dialog.findViewById(R.id.Rate);
                    Button send=dialog.findViewById(R.id.button4);

                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            apIs.AddandUpdateRates(cp.getText().toString(),callp.getText().toString(),vp.getText().toString(),astroid,HomePage.this,"India : "+rate.getText().toString()+" RS",echat.getText().toString());
                        dialog.dismiss();
                        }
                    });
                }
            }
        });
    }
    public void buttonClick(String userid,String userAvatar) {
        long appID = 53739149;
        String appSign ="2672188a0f50f74e4b116eacd325d79ffe67fb22c02f80a9f239ee2ad63f249c";

        ZIMKit.initWith(getApplication(),appID,appSign);
        ZIMKit.initNotifications();
        connectUser(userid, userid,userAvatar);
    }
    public void connectUser(String userId, String userName,String userAvatar) {
        // Logs in.
        ZIMKit.connectUser(userId,userName,userAvatar, errorInfo -> {
            if (errorInfo.code == ZIMErrorCode.SUCCESS) {
           Intent in=new Intent(HomePage.this,ConversationActivity.class);
           startActivity(in);
            } else {

            }
        });
    }
}