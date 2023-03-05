package com.example.wallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Intent intent;
    TextView ctl, cl, fp;
    private String verificationId;
    Button sendOTP, confirmOTP;
    View view, rl, ll, so, co, gnp;
    private FirebaseAuth mAuth;
    EditText rm, fillOTP;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("MOBILENO", MODE_PRIVATE);
        if (sharedPreferences.contains("mobileno") && sharedPreferences.contains("password")) {
            String mobile = sharedPreferences.getString("MOBILENO", "");
            //String status = sharedPreferences.getString("status", "");
            Toast.makeText(this, mobile, Toast.LENGTH_LONG).show();
            if (mobile.equals("")) {

            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
            }
        } else {
            //Toast.makeText(this, "not contain", Toast.LENGTH_LONG).show();
        }
        rm = findViewById(R.id.RM);
        ctl = findViewById(R.id.ctl);
        cl = findViewById(R.id.cl);
        fp = findViewById(R.id.fp);
        rl = (View) findViewById(R.id.rl);
        ll = (View) findViewById(R.id.ll);
        so = (View) findViewById(R.id.so);
        co = (View) findViewById(R.id.co);
        gnp = (View) findViewById(R.id.gnp);
        sendOTP = findViewById(R.id.sendOTP);
        confirmOTP = findViewById(R.id.confirmOTP);
        fillOTP = findViewById(R.id.otpnumber);
        mAuth = FirebaseAuth.getInstance();

        ctl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("LayoutChange", "donedone");
                rl.setVisibility(View.GONE);
                ll.setVisibility(View.VISIBLE);


            }
        });
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("LayoutChange", "donedone");
                rl.setVisibility(View.VISIBLE);
                ll.setVisibility(View.GONE);
                //Intent i = new Intent(MainActivity.this, SignUp.class);
                //startActivity(i);

            }
        });
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LayoutChange", "donedone");
                ll.setVisibility(View.GONE);
                so.setVisibility(View.VISIBLE);

            }
        });
        confirmOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LayoutChange", "donedonedone");
                if (TextUtils.isEmpty(fillOTP.getText().toString())) {

                    Toast.makeText(MainActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    verifyCode(fillOTP.getText().toString());
                }
            }
        });
        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.progressdialog, viewGroup, false);
                builder.setView(dialogView);
                builder.setCancelable(false);
                final AlertDialog alertDialog = builder.create();
                final ProgressBar progressBar = dialogView.findViewById(R.id.progressbar);
                alertDialog.show();

                if (TextUtils.isEmpty(rm.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    String phone = "+91" + rm.getText().toString();
                    sendVerificationCode(phone);
                }
            }
        });
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void sendVerificationCode(String number) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                (Activity) TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }


        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                fillOTP.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void verifyCode(String code) {


        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    public void check(View view) {
        if (rm.getText().toString().trim().isEmpty()) {
            rm.setError("Enter your Mobile No!");
        } else {
            progressDialog.show();
            rl.setVisibility(View.GONE);
            so.setVisibility(View.VISIBLE);
        }
    }
}