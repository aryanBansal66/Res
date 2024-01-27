package com.astro.astro_guruvani_astro.APIClass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.astro.astro_guruvani_astro.Home.HomePage;
import com.astro.astro_guruvani_astro.OtherFiles.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIs {

    private static final String BASE_URL = "https://adhaan.org/APIs/WebClass1.asmx/";
    public void Register(String token, String loginType, String emailph,Context cM) {
        String url = BASE_URL + "Register?token=" + token + "&LoginType=" + loginType + "&emailph=" + emailph;
        RequestQueue rq = Volley.newRequestQueue(cM);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String msg = jsonObject.getString("message");
                    SharedPreferences sp=cM.getSharedPreferences("Pref_forLogin",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("emailph",msg);
                    editor.commit();
                    Intent in=new Intent(cM,Profile.class);
                    in.putExtra("Off","OFF");
                    cM.startActivity(in);
                    ((AppCompatActivity)cM).finish();


                } catch (JSONException e) {
                    Toast.makeText(cM, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(cM, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(stringRequest);
    }

    public void AddFeedback(String token, String type, String phoneNumber, String feedback,Context cc) {
        String url = BASE_URL + "AddFeedback?token=" + token + "&type=" + type + "&phonenumber=" + phoneNumber + "&feedback=" + feedback;
        performRequest(url,cc);
    }

    public void InsertNotificationClient(String token, String title, String message, String astroid,String clientid,Context cc) {
        String url = BASE_URL + "InsertNotification?token=" + token + "&title=" + title + "&msg=" + message + "&astroid=" + astroid + "&clientid=" + clientid;
        performRequest(url,cc);
    }
    public void AddGallery(String token, String type, String images, String astroId,Context cc) {
        String url = BASE_URL + "AddGallery";
        RequestQueue rq = Volley.newRequestQueue(cc);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String msg = jsonObject.getString("message");

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                //Toast.makeText(cc, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String >map=new HashMap<>();
                map.put("token","ATRWS556Tmarlao");
                    map.put("type",type);
                    map.put("images",images);
                    map.put("astroid",astroId);

                return map;
            }
        };
        rq.add(stringRequest);
    }

    public void GiveRply(String id, String reply,Context cc) {
        String url = BASE_URL + "GiveReply?token=ATRWS556Tmarlao&id="+id+"&reply="+reply+"";
        performRequest(url,cc);
    }

    public void UpdateProfile(String img,String phone, String email,String name,String username,String dob,String time,String place,String language,String experience,String expertin,String currentaddress,String city,String pincode,String qualification,String degree,String college,String learn,String insta,String fb,String linkedin,String yt,String id,String about,Context cc) {
        String url = "https://adhaan.org/APIs/WebClass1.asmx/UpdateProfile";
        RequestQueue rq = Volley.newRequestQueue(cc);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String msg = jsonObject.getString("message");
                    Toast.makeText(cc, ""+msg, Toast.LENGTH_SHORT).show();
                    cc.startActivity(new Intent(cc,HomePage.class));
                    ((AppCompatActivity)cc).finish();
                } catch (JSONException e) {
                    Toast.makeText(cc, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                Toast.makeText(cc, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(cc, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String >map=new HashMap<>();
                    map.put("token","ATRWS556Tmarlao");
                    map.put("phone",phone);
                map.put("about",about);
                    map.put("email",email);
                    map.put("img",img);
                    map.put("astroname",name);
                    map.put("username",username);
                    map.put("dob",dob);
                    map.put("time",time);
                    map.put("place",place);
                    map.put("language",language);
                    map.put("experience",experience);
                    map.put("expetin",expertin);
                    map.put("currentAddress",currentaddress);
                    map.put("city",city);
                    map.put("pin",pincode );
                    map.put("qualification",qualification);
                    map.put("degree",degree);
                    map.put("college",college);
                    map.put("learn",learn);
                    map.put("link",insta);
                    map.put("fb",fb);
                    map.put("linkedin",linkedin);
                    map.put("yt",yt);
                    map.put("id",id);
                return map;
            }
        };
        rq.add(stringRequest);
    }

    public void UpdateProfileWithoutimg(String phone, String email,String name,String username,String dob,String time,String place,String language,String experience,String expertin,String currentaddress,String city,String pincode,String qualification,String degree,String college,String learn,String insta,String fb,String linkedin,String yt,String id,String about,Context cc) {
        String url = "https://adhaan.org/APIs/WebClass1.asmx/UpdateProfilewithoutimage";
        RequestQueue rq = Volley.newRequestQueue(cc);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String msg = jsonObject.getString("message");
                    Toast.makeText(cc, ""+msg, Toast.LENGTH_SHORT).show();
                    cc.startActivity(new Intent(cc,HomePage.class));
                    ((AppCompatActivity)cc).finish();
                } catch (JSONException e) {
                    Toast.makeText(cc, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                Toast.makeText(cc, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(cc, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String >map=new HashMap<>();
                map.put("token","ATRWS556Tmarlao");
                map.put("phone",phone);
                map.put("about",about);
                map.put("img","0000");
                map.put("email",email);
                map.put("astroname",name);
                map.put("username",username);
                map.put("dob",dob);
                map.put("time",time);
                map.put("place",place);
                map.put("language",language);
                map.put("experience",experience);
                map.put("expetin",expertin);
                map.put("currentAddress",currentaddress);
                map.put("city",city);
                map.put("pin",pincode );
                map.put("qualification",qualification);
                map.put("degree",degree);
                map.put("college",college);
                map.put("learn",learn);
                map.put("link",insta);
                map.put("fb",fb);
                map.put("linkedin",linkedin);
                map.put("yt",yt);
                map.put("id",id);
                return map;
            }
        };
        rq.add(stringRequest);
    }


    public void AddReview(String token, String star, String astroId, String followerPhoto, String userId, String viewsUsername, String comment,Context cc) {
        String url = BASE_URL + "AddReview?token=" + token + "&star=" + star + "&astroid=" + astroId + "&followerphoto=" + followerPhoto + "&userid=" + userId + "&viewsusername=" + viewsUsername + "&comment=" + comment;
        performRequest(url,cc);
    }

    public void AddTransactions(String token, String astroId, String userId, String transactionName, String amt,Context cc) {
        String url = BASE_URL + "AddTransactions?token=" + token + "&astroid=" + astroId + "&userid=" + userId + "&transactionname=" + transactionName + "&amt=" + amt;
        performRequest(url,cc);
    }

    public void AddAstroStatus(String token, String astroid, String Chattime, String ChatStatus, String CallTime, String CallStatus, String VideocallTime, String VideocallStatus, String emergencychat, String emergencychatstatus, String emergencycall, String emergencycallstatus,Context cc) {

        String url = "https://adhaan.org/APIs/WebClass1.asmx/AstroStatus";
        RequestQueue rq = Volley.newRequestQueue(cc);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String msg = jsonObject.getString("message");

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                Toast.makeText(cc, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String >map=new HashMap<>();
                map.put("token",token);
                map.put("Chat",Chattime);
                map.put("Chatstatus",ChatStatus);
                map.put("Call",CallTime);
                map.put("CallStatus",CallStatus);
                map.put("Videocall",VideocallTime);
                map.put("videostatus",VideocallStatus);
                map.put("emergencychat",emergencychat);
                map.put("emergencychatstatus",emergencychatstatus);
                map.put("emergencycallstatus",emergencycallstatus);
                map.put("emergencycall",emergencycall);
                map.put("astroid",astroid);
                return map;
            }
        };
        rq.add(stringRequest);
    }
    public void AddAndUpdateLive(String token, String isLive, String astroId, String roomCode, String astroName, String astroImg,Context cc,String livetype) {
        String url = BASE_URL + "AddandUpdateLive?token=" + token + "&IsLive=" + isLive + "&astroid=" + astroId + "&roomcode=" + roomCode + "&astroname=" + astroName + "&livetype=" + livetype + "&astroimg=" + astroImg;
        performRequest(url,cc);
    }

    public void DeleteFollowers(String token, String followerId, String astroId,Context cc) {
        String url = BASE_URL + "DeleteFollowers?token=" + token + "&followerid=" + followerId + "&astroid=" + astroId;
        performRequest(url,cc);
    }

    public void AddandUpdateRates(String chatp, String callp, String vcallp, String astroid,Context cc,String ecall,String echat) {
        String url = BASE_URL + "AddandUpdateRates?token=ATRWS556Tmarlao&chatp="+chatp+"&callp="+callp+"&vcallp="+vcallp+"&astroid="+astroid+"&ecall="+ecall+"&echat="+echat+"";
        performRequest(url,cc);
    }

    private void performRequest(String url,Context cc) {
        RequestQueue rq = Volley.newRequestQueue(cc);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String msg = jsonObject.getString("message");
//                    Toast.makeText(cc, ""+msg, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(cc, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                Toast.makeText(cc, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(stringRequest);
    }

    public void Rates(Context cc, String astroid, TextView t1,TextView t2,TextView t3){
        String url = "https://adhaan.org/APIs/WebClass1.asmx/GetRates?token=ATRWS556Tmarlao&astroid="+astroid+"";
        RequestQueue rq = Volley.newRequestQueue(cc);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String chat = jsonObject.getString("Chatprice");
                        String call = jsonObject.getString("CallPrice");
                        String videocall = jsonObject.getString("VideoCallprice");

                        t1.setText(chat);
                        t2.setText(call);
                        t3.setText(videocall);
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

}