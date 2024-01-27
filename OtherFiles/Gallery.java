package com.astro.astro_guruvani_astro.OtherFiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.astro.astro_guruvani_astro.APIClass.APIs;
import com.astro.astro_guruvani_astro.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Gallery extends AppCompatActivity {
RecyclerView gallery_RV;
String base64Image,phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        SharedPreferences sp=getSharedPreferences("Pref_forLogin", Context.MODE_PRIVATE);
        phonenumber=sp.getString("emailph","0");

        gallery_RV=findViewById(R.id.Gallery_RV);
        gallery_RV.setLayoutManager(new GridLayoutManager(Gallery.this,4));
        gallery_RV.setHasFixedSize(true);

        ImageView imageView=findViewById(R.id.imageView6);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pickImage();
      }
        });

        GetImages();
    }

    private void pickImage() {
        ImagePicker.with(this)
                .galleryOnly()
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();
    }

    void GetImages(){
        List<GalleryModel>list=new ArrayList<>();

        String url = "https://adhaan.org/APIs/WebClass1.asmx/Gallery?token=ATRWS556Tmarlao&type=&astroid="+phonenumber+"";
        RequestQueue rq = Volley.newRequestQueue(Gallery.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String Images = "https://adhaan.org/Images/"+jsonObject.getString("Images");
                        GalleryModel mm=new GalleryModel();
                        mm.setImage(Images);
                        list.add(mm);
                        GalleryAdapter adapter=new GalleryAdapter(Gallery.this,list);
                        gallery_RV.setAdapter(adapter);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            base64Image = convertImageToBase64Gallery(Gallery.this,uri);
            APIs apis=new APIs();
            apis.AddGallery("ATRWS556Tmarlao","",base64Image,phonenumber,Gallery.this);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    GetImages();
                }
            },2000);

        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Gallery.this, com.astro.astro_guruvani_astro.OtherFiles.Settings.class));
        finish();
    }

    public static String convertImageToBase64Gallery(Context context, Uri uri) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            if (inputStream != null) {
                inputStream.close();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}