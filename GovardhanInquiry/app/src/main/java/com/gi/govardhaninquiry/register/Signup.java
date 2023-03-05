package com.gi.govardhaninquiry.register;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.retro.RetroInterface;
import com.gi.govardhaninquiry.retro.RetrofitClient;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    EditText Name, DOB, CN1, Email, password, cpassword;
    TextView g, s, ph;
    RadioGroup RGG, RGS;
    Button signup;
    String Status, Gen, name, email, pass, contact1, dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setid();

        ph.setText("   Password must contain minimum 8 characters\n   with atleast 1 uppercase alphabet(A-Z), 1 lower\n   case alphabet(a-z), 1 digit (0-9) and 1 special\n   character (@#$%_^&+=)");
        Gen = Status = "";

        CN1.setText("+91");
        Selection.setSelection(CN1.getText(), CN1.getText().length());

        CN1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("+91")) {
                    CN1.setText("+91 ");
                    Selection.setSelection(CN1.getText(), CN1.getText().length());

                }

            }
        });

        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Signup.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
//                        DOB.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        DOB.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        RGS.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);

                boolean isChecked = checkedRadioButton.isChecked();

                if (isChecked) {
                    Status = checkedRadioButton.getText().toString();
                }
            }

        });

        RGG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);

                boolean isChecked = checkedRadioButton.isChecked();

                if (isChecked) {
                    Gen = checkedRadioButton.getText().toString();
                }
            }

        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_^&+=])(?=\\S+$).{8,}$";
                Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
                int cnt = 0;

                if (Name.getText().toString().trim().isEmpty()) {
                    cnt++;
                    Name.setError("Fill your name!");
                }
                if (DOB.getText().toString().trim().isEmpty()) {
                    cnt++;
                    DOB.setError("Fill your date of birth!");
                }
                if (CN1.getText().toString().trim().isEmpty() || CN1.getText().toString().equals("+91")) {
                    cnt++;
                    CN1.setError("Fill your contact number!");
                } else {
                    if (!test(CN1))
                        cnt++;
                }

                if (Email.getText().toString().trim().isEmpty()) {
                    cnt++;
                    Email.setError("Fill your email!");
                }
                if (Gen.trim().isEmpty()) {
                    cnt++;
                    g.setVisibility(View.VISIBLE);
                    g.setError("Choose your gender!");
                }
                if (Status.trim().isEmpty()) {
                    cnt++;
                    s.setVisibility(View.VISIBLE);
                    s.setError("Choose your status!");
                }

                if (password.getText().toString().isEmpty()) {
                    Toast.makeText(Signup.this, "p empty", Toast.LENGTH_LONG).show();
                    cnt++;
                    password.setError("Enter your password!");
                    ph.setVisibility(View.VISIBLE);
                } else {
                    Matcher m = pattern.matcher(password.getText().toString());

                    if (!m.matches()) {
                        cnt++;
                        password.setError("Password is invalid!");
                        ph.setVisibility(View.VISIBLE);
                    } else {
                        ph.setVisibility(View.GONE);
                    }

                }
                if (cpassword.getText().toString().isEmpty()) {
                    cnt++;
                    Toast.makeText(Signup.this, "Cp empty", Toast.LENGTH_LONG).show();
                    cpassword.setError("Enter your password!");
                } else {
                    Matcher m = pattern.matcher(cpassword.getText().toString());
                    if (!m.matches()) {
                        cnt++;
                        cpassword.setError("Password is invalid!");
                    }

                }
                if (!password.getText().toString().equals(cpassword.getText().toString())) {
                    cnt++;
                    cpassword.setError("Password do not match!");

                    Toast.makeText(Signup.this, "PASSWORD DO NOT MATCH!", Toast.LENGTH_LONG).show();
                }
                if (cnt == 0) {
                    Toast.makeText(Signup.this, "matched", Toast.LENGTH_LONG).show();
                    name = Name.getText().toString();
                    dob = DOB.getText().toString();
//                            Gen; Status;
                    contact1 = CN1.getText().toString();
                    email = Email.getText().toString();
                    pass = password.getText().toString();
                    RetrofitClient.getClient(Signup.this).create(RetroInterface.class).signup(name,dob,Status,Gen,contact1,email,pass)
                            .enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    Toast.makeText(Signup.this, "Data added to API", Toast.LENGTH_SHORT).show();
                                    String responseFromAPI = response.body();
                                    //Toast.makeText(Signup.this, responseFromAPI, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Signup.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Toast.makeText(Signup.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d("gisurat", t.toString());
                                }
                            });
                }
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

    public void setid() {
        Name = findViewById(R.id.Name);
        DOB = findViewById(R.id.DOB);
        CN1 = findViewById(R.id.CN1);
        Email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        cpassword = findViewById(R.id.CPassword);
        signup = findViewById(R.id.signupBtn);
        ph = findViewById(R.id.ph);
        g = findViewById(R.id.g);
        RGG = findViewById(R.id.Gen);
        RGS = findViewById(R.id.Status);
    }
}