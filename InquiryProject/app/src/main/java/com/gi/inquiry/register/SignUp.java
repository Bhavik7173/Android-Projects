package com.gi.inquiry.register;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.gi.inquiry.R;
import com.google.android.gms.common.api.Api;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    EditText Name, DOB, Img, CN1, CN2, Address, Interest, Hobby, password, cpassword, C, Div, M, Qua, YOP, Exp, Link;
    TextView g, s, ph;
    int y = 0;
    Context context;
    String Status, Gen, email, per_prof, image, stu_prof, pro_prof;
    RadioGroup RGG, RGS, RGS1;
    View last;
    Api api;
    int ind;
    int GALLERY_REQUEST = 0;
    Uri imageuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        Toast.makeText(this, email + "", Toast.LENGTH_LONG).show();
//        ind = email.indexOf("@");
        int i = 0;
        context = this;
        Name = findViewById(R.id.Name);
        DOB = findViewById(R.id.DOB);
        CN1 = findViewById(R.id.CN1);
        CN2 = findViewById(R.id.CN2);
        Address = findViewById(R.id.Address);
        Interest = findViewById(R.id.Interest);
        Hobby = findViewById(R.id.Hobby);
        password = findViewById(R.id.Password);
        cpassword = findViewById(R.id.CPassword);
        ph = findViewById(R.id.ph);
        g = findViewById(R.id.g);
        s = findViewById(R.id.s);
        Img = findViewById(R.id.Img);
        RGG = findViewById(R.id.Gen);
        RGS = findViewById(R.id.Status);
        RGS1 = findViewById(R.id.Status1);
        C = findViewById(R.id.C);
        Div = findViewById(R.id.Div);
        M = findViewById(R.id.M);
        Qua = findViewById(R.id.Qua);
        YOP = findViewById(R.id.YOP);
        Exp = findViewById(R.id.Exp);
        Link = findViewById(R.id.Link);

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
                    CN1.setText("+91");
                    Selection.setSelection(CN1.getText(), CN1.getText().length());

                }

            }
        });
        CN2.setText("+91");
        Selection.setSelection(CN2.getText(), CN2.getText().length());


        CN2.addTextChangedListener(new TextWatcher() {

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
                    CN2.setText("+91");
                    Selection.setSelection(CN2.getText(), CN2.getText().length());

                }

            }
        });

        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener;
                listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        y = year;
                        DOB.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                };
                Date d = new Date();
                Calendar c1 = Calendar.getInstance();
                //Calendar c2 = Calendar.getInstance();
                Date d1 = new Date(d.getYear() - 18, 12, 31);
                // Date d2 = new Date(d.getYear()-1000,1,1);
                c1.setTime(d1);
                //c2.setTime(d2);
                DatePickerDialog dp;
                dp = new DatePickerDialog(context, R.style.DialogTheme, listener, d.getYear() + 1900 - 18, 12, 31);
                dp.getDatePicker().setMaxDate(c1.getTime().getTime());
                //dp.getDatePicker().setMinDate(c2.getTime().getTime());
                dp.show();
            }
        });

        Img.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Img.setFocusable(true);
                Img.setText("No Image Selected");

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
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
        RGS1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
        YOP.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                Log.d("WhyredPto", "hello");
                show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void show() {

        final Dialog d = new Dialog(this);
        d.setTitle("Year Of Passing");
        d.setContentView(R.layout.pickerdialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        Date date = new Date();

        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(date.getYear() + 1900); // max value 100
        np.setMinValue(y + 20);   // min value 0
        if (YOP.getText().toString().trim().isEmpty())
            np.setValue(date.getYear() + 1900);
        else
            np.setValue(Integer.parseInt(YOP.getText().toString().trim()));
        np.setWrapSelectorWheel(false);
        np.setTextColor(getResources().getColor(R.color.colorAccent));
        np.setTextColor(getResources().getColor(R.color.colorAccent));

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YOP.setText(String.valueOf(np.getValue())); //set the value to textview
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();


    }

    public void next(View view) {
        Toast.makeText(this, "NEXT", Toast.LENGTH_LONG).show();
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        g.setVisibility(View.GONE);
        s.setVisibility(View.GONE);

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
        if (CN2.getText().toString().trim().isEmpty() || CN2.getText().toString().equals("+91")) {
            cnt++;
            CN2.setError("Fill your contact number!");
        } else {
            if (!test(CN2))
                cnt++;
        }
        if (Address.getText().toString().trim().isEmpty()) {
            cnt++;
            Address.setError("Fill your address!");
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
        if (Img.getText().toString().trim().isEmpty() || Img.getText().toString().equals("No Image Selected")) {
            cnt++;
            Img.setError("Choose your image!");
        }

        if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, "p empty", Toast.LENGTH_LONG).show();
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
            Toast.makeText(this, "Cp empty", Toast.LENGTH_LONG).show();
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

            Toast.makeText(this, "PASSWORD DO NOT MATCH!", Toast.LENGTH_LONG).show();
        }
        if (cnt == 0) {
            Toast.makeText(this, "matched", Toast.LENGTH_LONG).show();


            per_prof = "'" + Name.getText().toString().trim() + "','" + DOB.getText().toString().trim() + "','" +
                    Gen + "','" + CN1.getText().toString().trim() + "','" + CN2.getText().toString().trim() + "','" +
                    Status + "','" + email.substring(0, ind) + "','" + Address.getText().toString().trim() + "','" +
                    Hobby.getText().toString().trim() + "','" + Interest.getText().toString().trim() + "','" + email + "','" + password.getText().toString() + "'";
            Log.d("per_prof", per_prof);

            image = imageToString();
            Log.d("image", image);
            Log.d("email", email);

            View lperp = (View) findViewById(R.id.lperp);
            lperp.setVisibility(View.GONE);
            if (Status.equalsIgnoreCase("Student")) {
                Toast.makeText(this, "Student", Toast.LENGTH_LONG).show();
                View lsp = (View) findViewById(R.id.lsp);
                lsp.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "Faculty", Toast.LENGTH_LONG).show();
                View lprop = (View) findViewById(R.id.lprop);
                lprop.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PERMISSION_GRANTED) {
            Toast.makeText(this, "GRANTED", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_REQUEST);
        } else {
            Toast.makeText(this, "PERMISSION NOT TAKEN", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            try {
                Uri selectedImage = data.getData();
                Log.d("ImageURI", selectedImage + "");
                imageuri = selectedImage;
                Img.setText("Image Selected");
            } catch (Exception ex) {
                Log.d("Whyred", ex.getMessage());
            }

        }
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

    private String imageToString() {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);

        } catch (Exception ex) {
            Log.d("Error", ex.toString());
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] image = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(image, Base64.DEFAULT);

    }

    public void signUp_S(View view) {
        int flag = 0;
        if (C.getText().toString().isEmpty()) {
            flag++;
            C.setError("Please fill the empty field!");
        }
        if (Div.getText().toString().isEmpty()) {
            flag++;
            Div.setError("Please fill the empty field!");
        }
        if (M.getText().toString().isEmpty()) {
            flag++;
            M.setError("Please fill the empty field!");
        }
        if (flag == 0) {
            stu_prof = "'" + C.getText().toString().trim() + "','" + Div.getText().toString().trim() + "','" +
                    M.getText().toString().trim() + "'";
            Log.d("stu_prof", stu_prof);
//            Retrofit instance=MyRetrofit.getRetrofit(getString(R.string.IP1));
//
//            api=instance.create(Api.class);
//            Call<ProfileResponse> call=api.signup(per_prof,stu_prof,"",email,Status,image);
//
//            call.enqueue(new Callback<ProfileResponse>() {
//                @Override
//                public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
//                    ProfileResponse profileResponse=response.body();
//                    Log.d("responseP",profileResponse.getStatus());
//                    finish(profileResponse.getStatus());
//                }
//
//                @Override
//                public void onFailure(Call<ProfileResponse> call, Throwable t) {
//                    Log.d("responseP","error : "+t.toString());
//
//
//                }
//            });
        }
    }

    public void finish(String check) {
        if (check.equals("Success")) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        } else if (check.equals("Failure")) {
            Toast.makeText(this, "Problem with Server", Toast.LENGTH_LONG).show();
        }
    }

    public void signUp_AF(View view) {

        int flag = 0;
        if (Qua.getText().toString().trim().isEmpty()) {
            flag++;
            Qua.setError("Please fill the empty field!");
        }
        if (Qua.getText().toString().trim().isEmpty()) {
            flag++;
            Qua.setError("Please fill the empty field!");
        }
        if (Exp.getText().toString().trim().isEmpty()) {
            flag++;
            Exp.setError("Please fill the empty field!");
        }
        if (Link.getText().toString().trim().isEmpty()) {
            flag++;
            Link.setError("Please fill the empty field!");
        }
        if (flag == 0) {
            pro_prof = "'" + Qua.getText().toString().trim() + "','" + YOP.getText().toString().trim() + "','" + Exp.getText().toString().trim() + "','" +
                    Link.getText().toString().trim() + "'";
            Log.d("pro_prof", pro_prof);
//            Retrofit instance=MyRetrofit.getRetrofit(getString(R.string.IP1));
//
//            Call<ProfileResponse> call;
//            api=instance.create(Api.class);
//            call=api.signup(per_prof,"",pro_prof,email,Status,image);
//
//            call.enqueue(new Callback<ProfileResponse>() {
//                @Override
//                public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
//                    ProfileResponse profileResponse=response.body();
//                    Log.d("responseP",profileResponse.getStatus());
//                    finish(profileResponse.getStatus());
//                }
//
//                @Override
//                public void onFailure(Call<ProfileResponse> call, Throwable t) {
//                    Log.d("responseP","error : "+t.toString());
//
//
//                }
//            });
        }
    }

    @Override
    public void onBackPressed() {
        View v = (View) findViewById(R.id.lperp);
        if (v.getVisibility() == View.VISIBLE) {
            //Intent intent = new Intent(this,MainActivity.class);
            //startActivity(intent);
        } else {
            if (Status.equals("Student")) {
                View view = (View) findViewById(R.id.lsp);
                view.setVisibility(View.GONE);
            } else {
                View view = (View) findViewById(R.id.lprop);
                view.setVisibility(View.GONE);
            }
            v.setVisibility(View.VISIBLE);
        }
        super.onBackPressed();
    }
}
