package com.gi.govardhaninquiry.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.AdminHome;
import com.gi.govardhaninquiry.admin.activity.AddNewCourse;
import com.gi.govardhaninquiry.retro.RetroInterface;
import com.gi.govardhaninquiry.retro.RetrofitClient;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText email, password;
    String uemail, upass, spinnerText, umode;
    Spinner mode;
    SharedPreferences sharedPre;
    TextView loginBtn, resetBtn, forgotpassword, signup;
    Intent intent;
    List<User> user;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.usernameEdt);
        password = findViewById(R.id.pass);
        loginBtn = findViewById(R.id.loginBtn);
        resetBtn = findViewById(R.id.resetBtn);
        mode = findViewById(R.id.spinner);
        forgotpassword = findViewById(R.id.forgotpassword);
        signup = findViewById(R.id.cl);

        verifyStoragePermissions(Login.this);
        requestMultiplePermissions();

        sharedPre = getSharedPreferences("login", Context.MODE_PRIVATE);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setText("");
                password.setText("");
                mode.setSelection(0);
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(Login.this);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
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
                Intent i = new Intent(Login.this, Signup.class);
                startActivity(i);
                finishAffinity();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uemail = email.getText().toString();
                upass = password.getText().toString();
                umode = spinnerText;
                String msg = uemail + ":" + upass + ":" + umode;
                Log.d("gilog", msg);
//                Toast.makeText(Login.this, msg,Toast.LENGTH_SHORT).show();
                checkMode(uemail, upass, umode);
            }
        });
    }
    public void checkMode(String uemail, String upass, String umode) {

        RetrofitClient.getClient(Login.this).create(RetroInterface.class).login(uemail, upass, umode)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (umode.equals("Admin")) {
                            user = response.body();
                            for (int i = 0; i < user.size(); i++) {
                                if (uemail.equals(user.get(i).getUemail()) && upass.equals(user.get(i).getUpassword()) && umode.equals(user.get(i).getUstatus())) {
                                    intent = new Intent(Login.this, AdminHome.class);
                                    SharedPreferences.Editor ed = sharedPre.edit();
                                    ed.putString("userID", user.get(i).getUid());
                                    ed.putBoolean("status1", true);
                                    ed.putString("username", user.get(i).getUname());
                                    ed.putString("email", uemail);
                                    ed.putString("password", upass);
                                    ed.putString("status", umode);
                                    ed.apply();
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            }
                            //Toast.makeText(Signup.this, responseFromAPI, Toast.LENGTH_SHORT).show();

                        } else if (umode.equals("Consellor")) {
                            Log.d("gilog", "Consellor");
                            user = response.body();
                            for (int i = 0; i < user.size(); i++) {
                                if (uemail.equals(user.get(i).getUemail()) && upass.equals(user.get(i).getUpassword()) && umode.equals(user.get(i).getUstatus())) {
                                    intent = new Intent(Login.this, AdminHome.class);
                                    SharedPreferences.Editor ed = sharedPre.edit();
                                    ed.putString("userID", user.get(i).getUid());
                                    ed.putBoolean("status1", true);
                                    ed.putString("username", user.get(i).getUname());
                                    ed.putString("email", uemail);
                                    ed.putString("password", upass);
                                    ed.putString("status", umode);
                                    ed.apply();
                                    Toast.makeText(Login.this, "Conseller Login", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            }

                        } else if (umode.equals("Manager")) {
                            Log.d("gilog", "Manager");
                            user = response.body();
                            for (int i = 0; i < user.size(); i++) {
                                if (uemail.equals(user.get(i).getUemail()) && upass.equals(user.get(i).getUpassword()) && umode.equals(user.get(i).getUstatus())) {
                                    intent = new Intent(Login.this, AdminHome.class);
                                    SharedPreferences.Editor ed = sharedPre.edit();
                                    ed.putString("userID", user.get(i).getUid());
                                    ed.putString("username", user.get(i).getUname());
                                    ed.putString("email", uemail);
                                    ed.putBoolean("status1", true);
                                    ed.putString("password", upass);
                                    ed.putString("status", umode);
                                    ed.apply();
                                    Toast.makeText(Login.this, "Conseller Login", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            }
                        } else if (umode.equals("Faculty")) {
                            Log.d("gilog", "Faculty Mode");
                            user = response.body();
                            for (int i = 0; i < user.size(); i++) {
                                if (uemail.equals(user.get(i).getUemail()) && upass.equals(user.get(i).getUpassword()) && umode.equals(user.get(i).getUstatus())) {
                                    intent = new Intent(Login.this, AdminHome.class);
                                    SharedPreferences.Editor ed = sharedPre.edit();
                                    ed.putString("userID", user.get(i).getUid());
                                    ed.putBoolean("status1", true);
                                    ed.putString("username", user.get(i).getUname());
                                    ed.putString("email", uemail);
                                    ed.putString("password", upass);
                                    ed.putString("status", umode);
                                    ed.apply();
                                    Toast.makeText(Login.this, "Conseller Login", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(Login.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("gisurat", t.toString());
                    }
                });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerText = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(this, spinnerText+" ", Toast.LENGTH_LONG).show();
        Log.d("gilog", spinnerText + " ");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }
}