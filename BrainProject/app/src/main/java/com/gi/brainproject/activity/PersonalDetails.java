package com.gi.brainproject.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.brainproject.R;
import com.gi.brainproject.model.FileResponse;
import com.gi.brainproject.model.User;
import com.gi.brainproject.retro.RetroInterface;
import com.gi.brainproject.retro.RetrofitClient;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalDetails extends AppCompatActivity {

    SharedPreferences prf;
    Intent intent;
    Button edit, reset;
    String name, email, password;
    EditText nameEdt, emailEdt, dobEdt, mobileEdt, passwordEdt;
    List<User> myListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        prf = getSharedPreferences("user_details", MODE_PRIVATE);
        name = prf.getString("username", null);
        email = prf.getString("email", null);
        password = prf.getString("password", null);

        nameEdt = findViewById(R.id.unameEdt);
        emailEdt = findViewById(R.id.EmailEdt);
        mobileEdt = findViewById(R.id.mobnoEdt);
        dobEdt = findViewById(R.id.birthEdt);
        passwordEdt = findViewById(R.id.passEdt);
        edit = findViewById(R.id.editBtn);
        reset = findViewById(R.id.resetbtn);

        getDetail();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit.getText().toString().equalsIgnoreCase("Update Detail")) {
                    nameEdt.setEnabled(true);
                    dobEdt.setEnabled(true);
                    mobileEdt.setEnabled(true);
                    passwordEdt.setEnabled(true);
                    edit.setText("Save");
                    reset.setVisibility(View.VISIBLE);

                    dobEdt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final Calendar c = Calendar.getInstance();

                            // on below line we are getting
                            // our day, month and year.
                            int year = c.get(Calendar.YEAR);
                            int month = c.get(Calendar.MONTH);
                            int day = c.get(Calendar.DAY_OF_MONTH);

                            DatePickerDialog datePickerDialog = new DatePickerDialog(
                                    PersonalDetails.this,
                                    new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year,
                                                              int monthOfYear, int dayOfMonth) {

                                            dobEdt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                        }
                                    }, year, month, day);
                            datePickerDialog.show();
                        }

                    });
                } else if (edit.getText().toString().equalsIgnoreCase("Save")) {
                    checkEmail();
                    getDetail();
                    edit.setText("Update Detail");
                    reset.setVisibility(View.INVISIBLE);
                    onBackPressed();
                }
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameEdt.setText("");
                dobEdt.setText("");
                mobileEdt.setText("");
                passwordEdt.setText("");
            }
        });
    }

    public void checkEmail() {
        String uemail = emailEdt.getText().toString();
        String udob = dobEdt.getText().toString();
        String uname = nameEdt.getText().toString();
        String upass = passwordEdt.getText().toString();
        String umob = mobileEdt.getText().toString();

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        int cnt = 0;
        if (nameEdt.getText().toString().trim().isEmpty()) {
            cnt++;
            nameEdt.setError("Fill your name!");
        }
        if (dobEdt.getText().toString().trim().isEmpty()) {
            cnt++;
            dobEdt.setError("Fill your date of birth!");
        }
        if (mobileEdt.getText().toString().trim().isEmpty() || mobileEdt.getText().toString().equals("+91")) {
            cnt++;
            mobileEdt.setError("Fill your contact number!");
        } else {
            if (!test(mobileEdt))
                cnt++;
        }
        if (emailEdt.getText().toString().isEmpty()) {
            Toast.makeText(PersonalDetails.this, "email empty", Toast.LENGTH_LONG).show();
            cnt++;
            emailEdt.setError("Enter your email!");
            emailEdt.setVisibility(View.VISIBLE);
        }
        if (passwordEdt.getText().toString().isEmpty()) {
            Toast.makeText(PersonalDetails.this, "password empty", Toast.LENGTH_LONG).show();
            cnt++;
            passwordEdt.setError("Enter your password!");
            passwordEdt.setVisibility(View.VISIBLE);
        } else {
            Matcher m = pattern.matcher(passwordEdt.getText().toString());

            if (!m.matches()) {
                cnt++;
                passwordEdt.setError("Password is invalid!");
                passwordEdt.setVisibility(View.VISIBLE);
            } else {
                passwordEdt.setVisibility(View.GONE);
            }

        }
        if (cnt == 0) {
            RetrofitClient.getClient(PersonalDetails.this).create(RetroInterface.class).updateDetail(uname, udob, umob, uemail, upass).enqueue(new Callback<FileResponse>() {

                @Override
                public void onResponse(Call<FileResponse> call, Response<FileResponse> response) {
                    Log.d("response_Change", response.body().toString());
                    if (response.body().getSuccess() == "true") {
                        Toast.makeText(PersonalDetails.this, "Update Your Detail Successfully", Toast.LENGTH_SHORT).show();
                        getDetail();
                        edit.setText("Update Details");
                        reset.setVisibility(View.INVISIBLE);
                    } else {
                        Log.d("response_change_failur", response.body().toString());
                    }
                }

                @Override
                public void onFailure(Call<FileResponse> call, Throwable t) {
                    Log.d("response_fail", t.toString());
                }
            });
        }
    }

    public void getDetail() {
        nameEdt.setEnabled(false);
        dobEdt.setEnabled(false);
        emailEdt.setEnabled(false);
        mobileEdt.setEnabled(false);
        passwordEdt.setEnabled(false);

        RetrofitClient.getClient(PersonalDetails.this).create(RetroInterface.class).getPersonalDetail(email)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        myListData = response.body();
                        Log.d("gilog", myListData + "");
                        for (int i = 0; i < myListData.size(); i++) {
                            nameEdt.setText(myListData.get(i).getName());
                            emailEdt.setText(myListData.get(i).getEmail());
                            passwordEdt.setText(myListData.get(i).getPassword());
                            dobEdt.setText(myListData.get(i).getDob());
                            mobileEdt.setText(myListData.get(i).getMob());
                            Log.d("gilog", myListData.get(i).getPassword() + "");
                            Log.d("gilog", myListData.get(i).getEmail() + "");
                        }

//
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(PersonalDetails.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("gisurat", t.toString());
                    }
                });

    }

    public boolean test(EditText edt) {
        String pattern = "^((\\+){0,1}91(\\s){0,1}(\\-){0,1}(\\s){0,1}){0,1}[6-9]{1}[0-9](\\s){0,1}(\\-){0,1}(\\s){0,1}[1-9]{1}[0-9]{7}$";
        if ((edt.getText().toString().matches(pattern))) {
            Toast.makeText(this, "Accepted", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(this, "declined", Toast.LENGTH_LONG).show();
            edt.setError("Invalid number!");
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}