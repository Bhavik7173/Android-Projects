package com.example.sample.account.accountSecurity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sample.Api;
import com.example.sample.MyRetrofit;
import com.example.sample.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePasswordActivity extends AppCompatActivity {

    Button changeP;
    EditText cp,np,ncp;
    String TAG = "gilog";
    String password="";
    String email;
    View view;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        view = getWindow().getDecorView().findViewById(android.R.id.content);

        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);

        changeP = findViewById(R.id.changeP);
        cp = findViewById(R.id.cp);
        np = findViewById(R.id.np);
        ncp = findViewById(R.id.ncp);

        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<String> call;
        api=instance.create(Api.class);

        call = api.getPassword(email);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.body().equals("fail")) {
                    password = response.body();
                    Log.d("WhyredP",password);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("WhyredP",t.toString());

            }
        });
        changeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cnt = 0;
                StringBuilder hexString = null;
                String s = cp.getText().toString();
                String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_^&+=])(?=\\S+$).{8,}$";
                Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

                final String MD5 = "MD5";
                try {
                    // Create MD5 Hash
                    MessageDigest digest = MessageDigest.getInstance(MD5);
                    digest.update(s.getBytes());
                    byte messageDigest[] = digest.digest();

                    // Create Hex String
                    hexString = new StringBuilder();
                    for (byte aMessageDigest : messageDigest) {
                        String h = Integer.toHexString(0xFF & aMessageDigest);
                        while (h.length() < 2)
                            h = "0" + h;
                        hexString.append(h);
                    }
                    Log.d("Whyredhex",hexString.toString());

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                if (cp.getText().toString().isEmpty()) {
                    cnt++;
                    cp.setError("Enter your current password!");
                }
                else if (!hexString.toString().equals(password) || cp.getText().toString().equals("no password")) {
                    cnt++;
                    cp.setError("Current password is not correct!");
                }
                if (np.getText().toString().isEmpty()) {
                    cnt++;
                    np.setError("Enter new password!");
                } else {
                    Matcher m = pattern.matcher(np.getText().toString());

                    if (!m.matches()) {
                        cnt++;
                        np.setError("Password is invalid!");
                    } else {
                        np.setError(null);
                    }

                }
                if (ncp.getText().toString().isEmpty()) {
                    cnt++;
                    ncp.setError("Confirm new password!");
                } else {
                    ncp.setError(null);
                }
                if (np.getText().toString().equals(cp.getText().toString()) && !np.getText().toString().isEmpty() && !cp.getText().toString().isEmpty()) {
                    cnt++;
                    final AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
                    ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                    final Button[] ok = new Button[1];
                    View dialogView = LayoutInflater.from(ChangePasswordActivity.this).inflate(R.layout.successdialog, viewGroup, false);
                    ok[0] = (Button) dialogView.findViewById(R.id.ok);
                    builder.setView(dialogView);
                    builder.setCancelable(false);
                    final TextView msg1 = (dialogView).findViewById(R.id.msg);
                    final TextView header = (dialogView).findViewById(R.id.header);
                    msg1.setText("Create a password which you\nhaven't used in past.\n");
                    header.setText("Error");
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    ok[0].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.cancel();
                            np.setText("");
                            ncp.setText("");
                            cp.setText("");
                        }
                    });
                }
                if (!np.getText().toString().equals(ncp.getText().toString())) {
                    cnt++;
                    ncp.setError("Password do not match!");
                    np.setError("Password do not match!");
                } else {
                    if(!np.getText().toString().isEmpty() && !ncp.getText().toString().isEmpty()) {
                        Matcher m = pattern.matcher(np.getText().toString());

                        if (!m.matches()) {
                            cnt++;
                            np.setError("Password is invalid!");
                        } else {
                            np.setError(null);

                            ncp.setError(null);}
                        //np.setError(null);
                    }
                    else
                    {
                        np.setError("Enter new password!");
                        ncp.setError("Confirm new password!");
                    }
                }
                if (cnt == 0) {
                    changePassword();
                }
            }
        });
    }

    private void changePassword() {

        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP));
        Call<String> call;
        api=instance.create(Api.class);

        call = api.change_password(email,np.getText().toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.body().toString().equals("fail")) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
                    ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                    final Button[] ok = new Button[1];
                    View dialogView = LayoutInflater.from(ChangePasswordActivity.this).inflate(R.layout.successdialog, viewGroup, false);
                    ok[0] = (Button) dialogView.findViewById(R.id.ok);
                    builder.setView(dialogView);
                    builder.setCancelable(false);
                    final TextView msg1 = (dialogView).findViewById(R.id.msg);
                    msg1.setText("Your password has been\nsuccessfully changed.");
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    ok[0].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.cancel();
                            np.setText("");
                            ncp.setText("");
                            cp.setText("");
                            onBackPressed();
                        }
                    });
                }
                else
                {
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });

    }
}