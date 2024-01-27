package com.astro.astro_guruvani_astro.OtherFiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.astro.astro_guruvani_astro.APIClass.APIs;
import com.astro.astro_guruvani_astro.Home.HomePage;
import com.astro.astro_guruvani_astro.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    TextInputEditText name, username, email,about, ph, language, expert, dob, tob, pob, experience, address, city, pin, highqualification, degree, college, learn, insta, fb, linkedin, yt;
    MaterialButton bb, ip;
    CircleImageView ii;
    String base64Image;
    ImageConvertor imageConvertor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ii = findViewById(R.id.imageView4);
        ip = findViewById(R.id.ImagePicker);
        name = findViewById(R.id.Astroname);
        username = findViewById(R.id.Username);
        email = findViewById(R.id.Email);
        about = findViewById(R.id.About);
        ph = findViewById(R.id.ph);
        dob = findViewById(R.id.DOB);
        tob = findViewById(R.id.TIME);
        pob = findViewById(R.id.Place);
        language = findViewById(R.id.language);
        expert = findViewById(R.id.Expert);
        address = findViewById(R.id.Address);
        experience = findViewById(R.id.Experience);
        city = findViewById(R.id.City);
        pin = findViewById(R.id.Pin);
        degree = findViewById(R.id.Degree);
        highqualification = findViewById(R.id.Highqualification);
        college = findViewById(R.id.college);
        learn = findViewById(R.id.Learn);
        insta = findViewById(R.id.Insta);
        fb = findViewById(R.id.fb);
        linkedin = findViewById(R.id.Linkedin);
        yt = findViewById(R.id.Youtube);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        tob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        imageConvertor = new ImageConvertor();
        SharedPreferences sp = getSharedPreferences("Pref_forLogin", Context.MODE_PRIVATE);
        String phonenumber = sp.getString("emailph", "0");
        APIs apIs = new APIs();
        bb = findViewById(R.id.buttonsubmit);

        ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        String url = "https://adhaan.org/APIs/WebClass1.asmx/GetProfile?token=ATRWS556Tmarlao&id=" + phonenumber + "";
        RequestQueue rq = Volley.newRequestQueue(Profile.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String phoneNumber = jsonObject.getString("Phonenumber");
                        String emailText = jsonObject.getString("Email");
                        String astroImage = "https://adhaan.org/Images/" + jsonObject.getString("astroimg");
                        String astroName = jsonObject.getString("Astroname");
                        String aboutText = jsonObject.getString("Aboutus");
                        String usernameText = jsonObject.getString("Username");
                        String dobText = jsonObject.getString("DOB");
                        String time = jsonObject.getString("Time");
                        String place = jsonObject.getString("Place");
                        String languageText = jsonObject.getString("language");
                        String experienceText = jsonObject.getString("Experience");
                        String expertIn = jsonObject.getString("Expertin");
                        String currentAddress = jsonObject.getString("CurrentAddress");
                        String cityText = jsonObject.getString("City");
                        String pinText = jsonObject.getString("Pin");
                        String qualification = jsonObject.getString("Qualification");
                        String degreeText = jsonObject.getString("Degree");
                        String collegeText = jsonObject.getString("College");
                        String astroLearn = jsonObject.getString("Astrolearn");
                        String instaLink = jsonObject.getString("Instalink");
                        String fbLink = jsonObject.getString("fblink");
                        String linkedinLink = jsonObject.getString("linkedinlink");
                        String ytLink = jsonObject.getString("ytlink");

                        if(astroImage.equals("https://adhaan.org/Images/")){
                                ii.setImageResource(R.drawable.baseline_person_24);
                        }else {
                            Picasso.get().load(astroImage).into(ii);
                        }
                        name.setText(astroName);
                        username.setText(usernameText);
                        about.setText(aboutText);
                        email.setText(emailText);
                        ph.setText(phoneNumber);
                        language.setText(languageText);
                        expert.setText(expertIn);
                        dob.setText(dobText);
                        tob.setText(time);
                        pob.setText(place);
                        experience.setText(experienceText);
                        address.setText(currentAddress);

                        city.setText(cityText);
                        pin.setText(pinText);
                        highqualification.setText(qualification);
                        degree.setText(degreeText);
                        college.setText(collegeText);
                        learn.setText(astroLearn);
                        insta.setText(instaLink);
                        fb.setText(fbLink);
                        linkedin.setText(linkedinLink);
                        yt.setText(ytLink);

                        if(phoneNumber.equals("")){
                            ph.setEnabled(true);
                            ph.setFocusable(true);
                            email.setEnabled(false);
                            email.setFocusable(false);
                        }else {
                            email.setEnabled(true);
                            email.setFocusable(true);
                            ph.setEnabled(false);
                            ph.setFocusable(false);
                        }

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

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(base64Image==null){
                    ii.setImageResource(R.drawable.baseline_person_24);
                    apIs.UpdateProfileWithoutimg(ph.getText().toString(), email.getText().toString(), name.getText().toString(), username.getText().toString(), dob.getText().toString(), tob.getText().toString(), pob.getText().toString(), language.getText().toString(), experience.getText().toString(), expert.getText().toString(), address.getText().toString(), city.getText().toString(), pin.getText().toString(), highqualification.getText().toString(), degree.getText().toString(), college.getText().toString(), learn.getText().toString(), insta.getText().toString(), fb.getText().toString(), linkedin.getText().toString(), yt.getText().toString(), phonenumber, about.getText().toString(),Profile.this);
                }else {
                    apIs.UpdateProfile(base64Image, ph.getText().toString(), email.getText().toString(), name.getText().toString(), username.getText().toString(), dob.getText().toString(), tob.getText().toString(), pob.getText().toString(), language.getText().toString(), experience.getText().toString(), expert.getText().toString(), address.getText().toString(), city.getText().toString(), pin.getText().toString(), highqualification.getText().toString(), degree.getText().toString(), college.getText().toString(), learn.getText().toString(), insta.getText().toString(), fb.getText().toString(), linkedin.getText().toString(), yt.getText().toString(), phonenumber,about.getText().toString(), Profile.this);
                }

            }
        });

    }

    private void pickImage() {
        ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            ii.setImageURI(uri);
            base64Image = ImageConvertor.convertImageToBase64(ii, uri);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String value=getIntent().getExtras().getString("Off");
        if(value.equals("OFF")){
            finishAffinity();
        }else {
            startActivity(new Intent(Profile.this, HomePage.class));
        }
        overridePendingTransition(0, 0);
        finish();
    }

    private void showDatePickerDialog() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Profile.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = String.format("%02d-%02d-%04d", day, month + 1, year);
                        dob.setText(selectedDate);
                    }
                },
                year, // Initial year
                month, // Initial month
                day // Initial day
        );

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                Profile.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                        tob.setText(selectedTime);
                    }
                },
                hour,
                minute,
                false
        );
        timePickerDialog.show();
    }

}