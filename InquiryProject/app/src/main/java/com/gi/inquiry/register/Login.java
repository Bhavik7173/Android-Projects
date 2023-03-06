package com.gi.inquiry.register;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.inquiry.Network.RetroInterface;
import com.gi.inquiry.R;
import com.gi.inquiry.admin.AdminHome;
import com.gi.inquiry.consellor.ConsellorHome;
import com.gi.inquiry.faculty.FacultyHome;
import com.gi.inquiry.manager.ManagerHome;
import com.gi.inquiry.pojo.UserPojo;
import com.gi.inquiry.sharedPreference.SharedPre;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Retrofit;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AuthenticationListener {

    ImageView visible, facebook, instagram;
    EditText emailEdt, passwordEdt;
    ProgressDialog dialog;
    TextView forgotPass, signup, login;
    SharedPre sharedPre;
    AlertDialog.Builder builder;
    Retrofit retro;
    String spinnerText;
    RetroInterface retroInterface;
    public String token = null;
    public AppPreferences appPreferences = null;
    public AuthenticationDialog authenticationDialog = null;
    public Button button = null;
    public View info = null;

    //String host[]={"Admin","Consellor","Manager","Faculty"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEdt = findViewById(R.id.usernameEdt);
        passwordEdt = findViewById(R.id.pass);
        forgotPass = findViewById(R.id.forgotpassword);
        login = findViewById(R.id.loginBtn);
        visible = findViewById(R.id.password_visible);
        facebook = findViewById(R.id.facebook);
        instagram = findViewById(R.id.instagram);
        signup = findViewById(R.id.cl);
        button = findViewById(R.id.btn_login);
        info = findViewById(R.id.info);
        appPreferences = new AppPreferences(this);

        //check already have access token
        token = appPreferences.getString(AppPreferences.TOKEN);
        if (token != null) {
            getUserInfoByAccessToken(token);
        }

        UserPojo user = new UserPojo();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mylog", "forgot password");
                Intent i = new Intent(Login.this, ForgotPassword.class);
                startActivity(i);
                finishAffinity();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, SignUp.class);
                startActivity(i);
                finishAffinity();
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PackageInfo info = getPackageManager().getPackageInfo(
                            "com.facebook.samples.hellofacebook",
                            PackageManager.GET_SIGNATURES);
                    for (Signature signature : info.signatures) {
                        MessageDigest md = MessageDigest.getInstance("SHA");
                        md.update(signature.toByteArray());
                        Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                    }
                } catch (PackageManager.NameNotFoundException e) {

                } catch (NoSuchAlgorithmException e) {

                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = 0;
                if (emailEdt.getText().toString().isEmpty()) {
                    emailEdt.setError("Enter Email id");
                    c++;
                } else if (passwordEdt.getText().toString().isEmpty()) {
                    passwordEdt.setError("Enter Password");
                    c++;
                } else {
                    String name = emailEdt.getText().toString();
                    String pass = passwordEdt.getText().toString();
                    String status = spinnerText;
                    Log.d("mylog", spinnerText + "");
//                    dialog = AlertMessage.showProgressDialog(Login.this);
//
//                    retro = Retro.getRetrofit();
//                    retroInterface = retro.create(RetroInterface.class);
//                    Call call = retroInterface.login(name, pass, status);
//
//                    call.enqueue(new Callback<UserPojo>() {
//
//                        @Override
//                        public void onResponse(Call<UserPojo> call, Response<UserPojo> response) {
//
//                            UserPojo pojo = response.body();
//                            try {
//                                if (pojo.getStatus().equals("success")) {
////                                    sharedPre.writeData("userID", pojo.getUser_id());
////                                    sharedPre.writeData("status", "LoggedIn");
//                                    Intent intent = new Intent(Login.this, AdminHome.class);
//                                    startActivity(intent);
//                                    dialog.dismiss();
//                                } else {
//                                    dialog.dismiss();
//                                    Toast.makeText(Login.this, "Wrong Credential", Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (Exception e) {
//                                builder = ErrorDialog.showBuilder(Login.this);
//                                builder.show();
//                                dialog.dismiss();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<UserPojo> call, Throwable t) {
//                            builder = ErrorDialog.showBuilder(Login.this);
//                            builder.show();
//                            dialog.dismiss();
//                        }
//                    });
                    if (spinnerText.equals("Admin")) {

                        if (name.equals("Admin") && pass.equals("Admin@123")) {
                            Intent i = new Intent(Login.this, AdminHome.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(Login.this, "Please Check the Username or Password!!", Toast.LENGTH_LONG).show();
                        }
                    } else if (spinnerText.equals("Consellor")) {
                        if (name.equals("Admin") && pass.equals("Admin@123")) {
                            Intent i = new Intent(Login.this, ConsellorHome.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(Login.this, "Please Check the Username or Password!!", Toast.LENGTH_LONG).show();
                        }
                    } else if (spinnerText.equals("Manager")) {
                        if (name.equals("Admin") && pass.equals("Admin@123")) {
                            Intent i = new Intent(Login.this, ManagerHome.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(Login.this, "Please Check the Username or Password!!", Toast.LENGTH_LONG).show();
                        }
                    } else if (spinnerText.equals("Faculty")) {
                        if (name.equals("Admin") && pass.equals("Admin@123")) {
                            Intent i = new Intent(Login.this, FacultyHome.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(Login.this, "Please Check the Username or Password!!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerText = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(this, spinnerText+" ", Toast.LENGTH_LONG).show();
        Log.d("mylog", spinnerText + " ");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onTokenReceived(String auth_token) {
        if (auth_token == null)
            return;
        appPreferences.putString(AppPreferences.TOKEN, auth_token);
        token = auth_token;
        getUserInfoByAccessToken(token);
    }

    public void instaLogin() {
        button.setText("LOGOUT");
        info.setVisibility(View.VISIBLE);
        ImageView pic = findViewById(R.id.pic);
        Picasso.with(this).load(appPreferences.getString(AppPreferences.PROFILE_PIC)).into(pic);
        TextView id = findViewById(R.id.id);
        id.setText(appPreferences.getString(AppPreferences.USER_ID));
        TextView name = findViewById(R.id.name);
        name.setText(appPreferences.getString(AppPreferences.USER_NAME));
    }

    public void logout() {
        button.setText("INSTAGRAM LOGIN");
        token = null;
        info.setVisibility(View.GONE);
        appPreferences.clear();
    }

    public void instaBtn(View view) {
        if (token != null) {
            logout();
        } else {
            authenticationDialog = new AuthenticationDialog(this, this);
            authenticationDialog.setCancelable(true);
            authenticationDialog.show();
        }
    }

    public void getUserInfoByAccessToken(String token) {
        new RequestInstagramAPI().execute();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public class RequestInstagramAPI extends AsyncTask<Void, String, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(getResources().getString(R.string.get_user_info_url) + token);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                return EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("response", jsonObject.toString());
                    JSONObject jsonData = jsonObject.getJSONObject("data");
                    if (jsonData.has("id")) {
                        //сохранение данных пользователя
                        appPreferences.putString(AppPreferences.USER_ID, jsonData.getString("id"));
                        appPreferences.putString(AppPreferences.USER_NAME, jsonData.getString("username"));
                        appPreferences.putString(AppPreferences.PROFILE_PIC, jsonData.getString("profile_picture"));

                        instaLogin();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Ошибка входа!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}


//            Retro.getRetrofit(this).create(RetroInterface.class).login1(email,pass).enqueue(new Callback<String>() {
//                @Override
//                public void onResponse(retrofit2.Call<String> call, Response<String> response) {
//                    //UserPojo pojo = response.body();
//                    Log.d("mylog", "response body");
//                    System.out.println(response.body().toString());
//                    try {
//                        if (pojo.getStatus().equals("success")) {
//                            sharedPre.writeData("userID", pojo.getUser_id());
//                            sharedPre.writeData("status", "LoggedIn");
//                            Intent intent = new Intent(Login.this, Home.class);
//                            startActivity(intent);
//                            dialog.dismiss();
//                        } else {
//                            dialog.dismiss();
//                            Toast.makeText(Login.this, "Wrong Credential", Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (Exception e) {
//                        builder = ErrorDialog.showBuilder(Login.this);
//                        builder.show();
//                        dialog.dismiss();
//                    }
//                }
//
//                @Override
//                public void onFailure(retrofit2.Call<UserPojo> call, Throwable t) {
//                    Log.d("mylog", "response body1");
//                    builder = ErrorDialog.showBuilder(Login.this);
//                    builder.show();
//                    dialog.dismiss();
//                }
//            });
//            Call<String> c2 = retroInterface.login(email,pass);
//            c2.enqueue(new Callback<String>() {
//                @Override
//                public void onResponse(Call<String> call, Response<String> response) {
//                    String pojo = response.body();
//                    Log.d("mylog", "response body");
//                    if(pojo.equals("success"))
//                    {
//                        Toast.makeText(Login.this,"Login Page...",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        Toast.makeText(Login.this,"Database Related Query...",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<String> call, Throwable t) {
//                    Toast.makeText(Login.this,"Contact Admin....",Toast.LENGTH_SHORT).show();
//                    Log.d("mylog","Contact Admin...");
//                }
//            });