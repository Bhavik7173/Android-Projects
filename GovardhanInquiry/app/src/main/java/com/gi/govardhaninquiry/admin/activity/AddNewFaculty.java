package com.gi.govardhaninquiry.admin.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.model.FileResponse;
import com.gi.govardhaninquiry.admin.model.MyFacultyData;
import com.gi.govardhaninquiry.retro.RetroInterface;
import com.gi.govardhaninquiry.retro.RetrofitClient;
import com.gi.govardhaninquiry.static_method.FilePathCreator;
import com.gi.govardhaninquiry.static_method.ImagePathCreator;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewFaculty extends AppCompatActivity {

    private static final int PICK_QUALIFICATION_CODE = 9533;
    private static final int PICK_RESUME_CODE = 9544;
    private static final int PICK_ADDHARCARD_CODE = 9555;
    private static final int PICK_PICTURE_CODE = 9566;
    EditText fname, fdob, fdoj, fmobile, fwhatsapp, femail, fyoe, faddress, freferencename, freferenceno, ftechnology, fnote;
    String name, dob, doj, mobile, whatsapp, email, yoe, address, referencename, referenceno, technology, note;
    Button fqualification, fresume, faadharcard, fphoto, fsumbit;
    Intent intent;
    int id = 0;
    String rPath, qPath, aPath, pPath;
    String rpdfname = "PDF_Resume/" + name;
    String apdfname = "PDF_AdharCard/" + name;
    String qpdfname = "PDF_Qualification/" + name;
    String ppdfname = "PDF_Photo/" + name;
    MultipartBody.Part rpdfupload, apdfupload, qpdfupload, ppdfupload;
    String rPdf, qPdf, aPdf, pPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_faculty);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Faculty</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        setid();
        staticfunction();
        editData();
        fsumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fname.getText().toString().isEmpty()) {
                    fname.setError("Please Enter Name..");
                    fname.requestFocus();
                } else if (fdob.getText().toString().isEmpty()) {
                    fdob.setError("Please Enter DOB..");
                    fdob.requestFocus();
                } else if (fdoj.getText().toString().isEmpty()) {
                    fdoj.setError("Please Enter DOJ..");
                    fdoj.requestFocus();
                } else if (fmobile.getText().toString().isEmpty()) {
                    fmobile.setError("Please Enter Mobile..");
                    fmobile.requestFocus();
                } else if (fwhatsapp.getText().toString().isEmpty()) {
                    fwhatsapp.setError("Please Enter Whatsapp No..");
                    fwhatsapp.requestFocus();
                } else if (freferencename.getText().toString().isEmpty()) {
                    freferencename.setError("Please Enter Reference Name..");
                    freferencename.requestFocus();
                } else if (freferenceno.getText().toString().isEmpty()) {
                    freferenceno.setError("Please Enter Reference No..");
                    freferenceno.requestFocus();
                } else if (ftechnology.getText().toString().isEmpty()) {
                    ftechnology.requestFocus();
                    ftechnology.setError("Please Enter Technology..");
                } else if (fqualification.getText().toString().isEmpty()) {
                    fqualification.requestFocus();
                    fqualification.setError("Please Enter Qaulification..");
                } else {
                    name = fname.getText().toString();
                    dob = fdob.getText().toString();
                    doj = fdoj.getText().toString();
                    mobile = fmobile.getText().toString();
                    whatsapp = fwhatsapp.getText().toString();
                    email = femail.getText().toString();
                    yoe = fyoe.getText().toString();
                    address = faddress.getText().toString();
                    referencename = freferencename.getText().toString();
                    referenceno = freferenceno.getText().toString();
                    technology = ftechnology.getText().toString();
                    note = fnote.getText().toString();

                    MyFacultyData faculty = new MyFacultyData(name, dob, doj, mobile, whatsapp, email, yoe, address, referencename, referenceno, technology, note);
                    RetrofitClient.getClient(AddNewFaculty.this).create(RetroInterface.class).addFaculty(faculty, qpdfupload, rpdfupload, apdfupload, ppdfupload).enqueue(new Callback<FileResponse>() {
                        @Override
                        public void onResponse(Call<FileResponse> call, Response<FileResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(AddNewFaculty.this, "File Uploaded", Toast.LENGTH_SHORT).show();
                                Intent main = new Intent(AddNewFaculty.this, AdminFaculty.class);
                                main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(main);
                            }
                        }

                        @Override
                        public void onFailure(Call<FileResponse> call, Throwable t) {
                            Log.d("gilog_faculty", t.getMessage() + "\n" + t.toString());
                            Toast.makeText(AddNewFaculty.this, "Request failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
    }

    public void staticfunction() {
        fdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewFaculty.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        fdob.setText(i + "/" + (i1 + 1) + "/" + i2);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();
            }
        });
        fdoj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewFaculty.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        fdoj.setText(i + "/" + (i1 + 1) + "/" + i2);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();
            }
        });
        fqualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(Intent.createChooser(intent, "Open File Manager"), PICK_QUALIFICATION_CODE);

            }
        });
        fresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(Intent.createChooser(intent, "Open File Manager"), PICK_RESUME_CODE);

            }
        });
        faadharcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(Intent.createChooser(intent, "Open File Manager"), PICK_ADDHARCARD_CODE);

            }
        });
        fphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Open File Manager"), PICK_PICTURE_CODE);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 9533:

                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String uriString = uri.toString();
                    File myFile = new File(uriString);
                    qPath = FilePathCreator.getFilePathFromURI(AddNewFaculty.this, uri);
                    fqualification.setText(qPath);
//                    qpdfupload = FilePathCreator.createMultiPart(qPath, qpdfname,"qPdf");
                    File sfile = new File(qPath);// Create a file using the absolute path of the image
                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("*/*"), sfile);
                    qpdfupload = MultipartBody.Part.createFormData("qPdf", sfile.getName(), requestBody1);
                    RequestBody qpdfile_request = RequestBody.create(MediaType.parse("text/plain"), qpdfname);
                    Log.d("gilog_faculty", qpdfupload + "");
                }
                break;
            case 9544:

                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String uriString = uri.toString();
                    File myFile = new File(uriString);
                    rPath = FilePathCreator.getFilePathFromURI(AddNewFaculty.this, uri);
                    fresume.setText(rPath);
//                    rpdfupload = FilePathCreator.createMultiPart(rPath, rpdfname,"rePdf");
                    File rfile = new File(rPath);// Create a file using the absolute path of the image
                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("*/*"), rfile);
                    rpdfupload = MultipartBody.Part.createFormData("resume", rfile.getName(), requestBody1);
                    RequestBody spdfile_request = RequestBody.create(MediaType.parse("text/plain"), rpdfname);
                    Log.d("gilog_faculty1", rpdfupload + "" + spdfile_request);
                }
                break;
            case 9555:

                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String uriString = uri.toString();
                    File myFile = new File(uriString);
                    aPath = FilePathCreator.getFilePathFromURI(AddNewFaculty.this, uri);
                    faadharcard.setText(aPath);
//                    apdfupload = FilePathCreator.createMultiPart(aPath, apdfname,"aPdf");
                    File sfile = new File(aPath);// Create a file using the absolute path of the image
                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("*/*"), sfile);
                    rpdfupload = MultipartBody.Part.createFormData("aPdf", sfile.getName(), requestBody1);
                    RequestBody apdfile_request = RequestBody.create(MediaType.parse("text/plain"), apdfname);
                    Log.d("gilog_faculty", apdfupload + "");
                }
                break;
            case 9566:

                if (resultCode == RESULT_OK) {
                    if (data == null) {
                        Toast.makeText(this, "Unable to choose file", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Uri imageuri = data.getData();
                    pPath = getRealPathFromUriImage(imageuri);
                    fphoto.setText(pPath);
                    ppdfupload = ImagePathCreator.createMultiPartImg(pPath, ppdfname, "pPdf");

                }
                break;
        }
    }

    private String getRealPathFromUriImage(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this, uri, projection, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    public void setid() {
        fname = findViewById(R.id.fnameEdt);
        fdoj = findViewById(R.id.dojEdt);
        fdob = findViewById(R.id.dobEdt);
        fmobile = findViewById(R.id.fmobileEdt);
        fwhatsapp = findViewById(R.id.fwhatsappEdt);
        femail = findViewById(R.id.femailEdt);
        fyoe = findViewById(R.id.fyoeEdt);
        faddress = findViewById(R.id.faddressEdt);
        freferencename = findViewById(R.id.frefernecenameEdt);
        freferenceno = findViewById(R.id.frefernecenameEdt);
        ftechnology = findViewById(R.id.ftechnologyEdt);
        fnote = findViewById(R.id.fnoteEdt);
        fqualification = findViewById(R.id.fqualificationEdt);
        fresume = findViewById(R.id.fresumeEdt);
        faadharcard = findViewById(R.id.faadharEdt);
        fphoto = findViewById(R.id.fphotoEdt);
        fsumbit = findViewById(R.id.fsubmit_button);
    }

    private void cleardata() {
        fname.setText("");
        fdob.setText("");
        fdoj.setText("");
        fmobile.setText("");
        fwhatsapp.setText("");
        femail.setText("");
        fyoe.setText("");
        faddress.setText("");
        freferencename.setText("");
        freferenceno.setText("");
        ftechnology.setText("");
        fnote.setText("");
        fqualification.setText("");
        fresume.setText("");
        faadharcard.setText("");
        fphoto.setText("");

    }

    private void editData() {

        if (getIntent().getBundleExtra("userdata") != null) {
            Bundle bundle = getIntent().getBundleExtra("userdata");
            id = bundle.getInt("faculty_id");
            fname.setText(bundle.getString("faculty_name"));
            fdob.setText(bundle.getString("faculty_dob"));
            fdoj.setText(bundle.getString("faculty_doj"));
            fmobile.setText(bundle.getString("faculty_mobile"));
            fwhatsapp.setText(bundle.getString("faculty_whatsapp"));
            femail.setText(bundle.getString("faculty_email"));
            fyoe.setText(bundle.getString("faculty_yoe"));
            faddress.setText(bundle.getString("faculty_address"));
            freferencename.setText(bundle.getString("faculty_reference_name"));
            freferenceno.setText(bundle.getString("faculty_reference_no"));
            ftechnology.setText(bundle.getString("faculty_technology"));
            fnote.setText(bundle.getString("faculty_note"));
            fqualification.setText(bundle.getString("faculty_qaulification"));
            fresume.setText(bundle.getString("faculty_aadhar_card"));
            faadharcard.setText(bundle.getString("faculty_resume"));
            fphoto.setText(bundle.getString("faculty_photo"));
            fsumbit.setText("Edit");
            //submit.setVisibility(View.GONE);
        }
    }
}