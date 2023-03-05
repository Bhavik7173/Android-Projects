package com.gi.ginquiry.register;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.gi.ginquiry.FileResponse;
import com.gi.ginquiry.R;
import com.gi.ginquiry.Retro.RetroInterface;
import com.gi.ginquiry.Retro.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    int GALLERY_REQUEST = 0;
    EditText Name, DOB, Img, CN1, CN2, Address, Email, Hobby, password, cpassword;
    TextView g, s, ph;
    String Status, Gen, email, per_prof, image, stu_prof, pro_prof;
    RadioGroup RGG, RGS, RGS1;
    int y = 0;
    String imagePath;
    Context context;
    Button signup;
    int ind;
    Uri imageuri;
    MultipartBody.Part partImage;
    PersonalProfile personalProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Name = findViewById(R.id.Name);
        DOB = findViewById(R.id.DOB);
        CN1 = findViewById(R.id.CN1);
        CN2 = findViewById(R.id.CN2);
        Address = findViewById(R.id.Address);
        Email = findViewById(R.id.Email);
        Hobby = findViewById(R.id.Hobby);
        password = findViewById(R.id.Password);
        cpassword = findViewById(R.id.CPassword);
        signup = findViewById(R.id.signupBtn);

        ph = findViewById(R.id.ph);
        g = findViewById(R.id.g);

        Img = findViewById(R.id.Img);
        RGG = findViewById(R.id.Gen);
        RGS = findViewById(R.id.Status);

        ph.setText("   Password must contain minimum 8 characters\n   with atleast 1 uppercase alphabet(A-Z), 1 lower\n   case alphabet(a-z), 1 digit (0-9) and 1 special\n   character (@#$%_^&+=)");
        Gen = Status = "";

        CN1.setText("+91 6353539097");
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
        CN2.setText("+91 9898989898");
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
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Signup.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        DOB.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        Img.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Img.setFocusable(true);
                Img.setText("No Image Selected");

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
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
                if (Img.getText().toString().trim().isEmpty() || Img.getText().toString().equals("No Image Selected")) {
                    cnt++;
                    Img.setError("Choose your image!");
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


//                    per_prof = "'" + Name.getText().toString().trim() + "','" + DOB.getText().toString().trim() + "','" +
//                            Gen + "','" + CN1.getText().toString().trim() + "','" + CN2.getText().toString().trim() + "','" +
//                            Status + "','" + Address.getText().toString().trim() + "','" +
//                            Hobby.getText().toString().trim() + "','" + Email.getText().toString().trim() + "','"  + password.getText().toString() + "'";
////                    Log.d("per_prof", per_prof);
//
                    image = imageToString();


                }
            }
        });
    }

    public void personal_profile(String uid) {
        personalProfile = new PersonalProfile(uid, DOB.getText().toString(), image, Gen, CN1.getText().toString(), CN2.getText().toString(), Address.getText().toString(),
                Hobby.getText().toString());
        RetrofitClient.getClient(Signup.this).create(RetroInterface.class).personal_profile(uid, DOB.getText().toString(), image, Gen, CN1.getText().toString(), CN2.getText().toString(), Address.getText().toString(),
                        Hobby.getText().toString())
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
//            try {
//                Uri selectedImage = data.getData();
//                Log.d("ImageURI", selectedImage + "");
//                imageuri = selectedImage;
//                Img.setText("Image Selected");
//            } catch (Exception ex) {
//                Log.d("Whyred", ex.getMessage());
//            }
//
//        }
//    }

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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(context, "Unable to choose file", Toast.LENGTH_SHORT).show();
                return;
            }
            Uri imageuri = data.getData();
            imagePath = getRealPathFromUri(imageuri);
            uploadImage();
        }
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(context.getApplicationContext(), uri, projection, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    private void uploadImage() {
        File imageFile = new File(imagePath);
        Log.d("file", imageFile.getAbsolutePath());// Create a file using the absolute path of the image
        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imageFile);
        partImage = MultipartBody.Part.createFormData("file", imageFile.getName(), reqBody);
//        Call<FileResponse> upload = fileService.upload(partImage);
        RetrofitClient.getClient(context).create(RetroInterface.class).upload(partImage).enqueue(new Callback<FileResponse>() {
            @Override
            public void onResponse(Call<FileResponse> call, Response<FileResponse> response) {
                Log.d("response_success", response.body().toString());
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Image Uploaded", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FileResponse> call, Throwable t) {
                Log.d("response_fail", t.toString());
                Toast.makeText(context, "Request failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


//                    RetrofitClient.getClient(Signup.this).create(RetroInterface.class).signup(Name.getText().toString(), DOB.getText().toString(), image, Gen, CN1.getText().toString(), CN2.getText().toString(), Status, Address.getText().toString(),
//                            Hobby.getText().toString(), Email.getText().toString(), password.getText().toString())
//                            .enqueue(new Callback<String>() {
//                                @Override
//                                public void onResponse(Call<String> call, Response<String> response) {
//                                    Toast.makeText(Signup.this, "Data added to API", Toast.LENGTH_SHORT).show();
//                                    String responseFromAPI = response.body();
////                                    Toast.makeText(Signup.this, responseFromAPI, Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(Signup.this, Login.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//
//                                @Override
//                                public void onFailure(Call<String> call, Throwable t) {
//                                    Toast.makeText(Signup.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                                    Log.d("gisurat", t.toString());
//                                }
//                            });

//                    RetrofitClient.getClient(Signup.this).create(RetroInterface.class).signup(Name.getText().toString(),Status, Email.getText().toString(), password.getText().toString())
//        .enqueue(new Callback<String>() {
//@Override
//public void onResponse(Call<String> call, Response<String> response) {
//        String uid = response.body().toString();
//        personal_profile(uid);
//        }
//
//@Override
//public void onFailure(Call<String> call, Throwable t) {
//        Toast.makeText(Signup.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
//        Log.d("gisurat", t.toString());
//        }
//        });
//        }
//        }
//        });
//
