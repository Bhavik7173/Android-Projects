package com.gi.brainproject.fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.gi.brainproject.R;
import com.gi.brainproject.activity.BrainHome;
import com.gi.brainproject.model.FileResponse;
import com.gi.brainproject.model.PatientData;
import com.gi.brainproject.retro.RetroInterface;
import com.gi.brainproject.retro.RetrofitClient;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PatientFragment extends Fragment {

    FrameLayout frameLayout;
    String name, age, gender, contact, image, userid;
    EditText pname, page, pgender, pcontact, pimage;
    Context context;
    ImageView imgview;
    String imagePath, Gen;
    RadioGroup RGG;
    Button submit, reset;
    RetrofitClient retroInterface;
    SharedPreferences prf;
    PatientData patientdetail;
    File imageFile;

    public PatientFragment(Context context, String uid) {
        this.context = context;
        Gen = "";
//        this.userid = uid;
//        Log.d("Patient-uid", userid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_patient, container, false);
        frameLayout = view.findViewById(R.id.flFragment);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Patient</font>"));
        pname = view.findViewById(R.id.pnameEdt);
        page = view.findViewById(R.id.ageEdt);
        RGG = view.findViewById(R.id.Gen);
        pcontact = view.findViewById(R.id.pmobnoEdt);
        pimage = view.findViewById(R.id.image);
        imgview = view.findViewById(R.id.pimageView);
        submit = view.findViewById(R.id.psubmitBtn);
        reset = view.findViewById(R.id.presetbtn);

        prf = context.getSharedPreferences("user_details", MODE_PRIVATE);
        userid = prf.getString("uid", null);
        pimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
        RGG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);

                boolean isChecked = checkedRadioButton.isChecked();

                if (isChecked) {
                    Gen = checkedRadioButton.getText().toString();
                    Log.d("Patient__Detail", Gen);
                }
            }

        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = pname.getText().toString();
                age = page.getText().toString();
                contact = pcontact.getText().toString();
                int cnt = 0;
                if (pname.getText().toString().trim().isEmpty()) {
                    cnt++;
                    pname.setError("Fill Patient name!");
                }
                if (page.getText().toString().trim().isEmpty()) {
                    cnt++;
                    page.setError("Fill Patient Age!");
                }
                if (pcontact.getText().toString().trim().isEmpty() || pcontact.getText().toString().equals("+91")) {
                    cnt++;
                    pcontact.setError("Fill Patient contact number!");
                } else {
                    if (!test(pcontact))
                        cnt++;
                }
                if (Gen.isEmpty()) {
                    cnt++;
                    Toast.makeText(context, "Please Select patient Gender!", Toast.LENGTH_SHORT).show();
                }
//                gender = pgender.getText().toString();
                Log.d("Patient__Detail", userid);
                if (cnt == 0) {
                    uploadImage(userid, name, age, contact, Gen);
                }
            }
        });

        return view;
    }

    public boolean test(EditText edt) {
        String pattern = "^((\\+){0,1}91(\\s){0,1}(\\-){0,1}(\\s){0,1}){0,1}[6-9]{1}[0-9](\\s){0,1}(\\-){0,1}(\\s){0,1}[1-9]{1}[0-9]{7}$";
        if ((edt.getText().toString().matches(pattern))) {
            Toast.makeText(context, "Accepted", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(context, "declined", Toast.LENGTH_LONG).show();
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
            pimage.setText(imagePath);
            imgview.setImageURI(imageuri);
        }
    }

    private void uploadImage(String userid, String name, String age, String contact, String Gen) {
        Log.d("file__Path", imagePath);// Create a file using the absolute path of the image
        imageFile = new File(imagePath);
        Log.d("file__", imageFile.getAbsolutePath());// Create a file using the absolute path of the image
        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imageFile);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("file", imageFile.getName(), reqBody);
//        Call<FileResponse> upload = fileService.upload(partImage);

        patientdetail = new PatientData(userid, name, age, contact, Gen);
        Log.d("Patient__Detail", patientdetail.toString());
        retroInterface.getClient(context).create(RetroInterface.class).upload(patientdetail, partImage).enqueue(new Callback<FileResponse>() {
            @Override
            public void onResponse(Call<FileResponse> call, Response<FileResponse> response) {
                Log.d("response_success", response.body().toString());
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Image Uploaded", Toast.LENGTH_SHORT).show();
                    Intent main = new Intent(context, BrainHome.class);
                    main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(main);
                }
            }

            @Override
            public void onFailure(Call<FileResponse> call, Throwable t) {
                Log.d("response_fail", t.toString());
//                pimage.setText(t.toString());
                Toast.makeText(context, "Request failed", Toast.LENGTH_SHORT).show();
            }
        });
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
}