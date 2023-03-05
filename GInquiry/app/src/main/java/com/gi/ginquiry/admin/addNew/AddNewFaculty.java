package com.gi.ginquiry.admin.addNew;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.ginquiry.R;
import com.gi.ginquiry.admin.DAO.FacultyDB;
import com.gi.ginquiry.admin.activity.AdminFaculty;

import java.util.Calendar;

public class AddNewFaculty extends AppCompatActivity {

    EditText fname, fdob, fdoj, fmobile, fwhatsapp, femail, fyoe, faddress, freferencename, freferenceno, ftechnology, fnote;
    Button fqualification, fresume, faadharcard, fphoto, fsumbit;
    Intent intent;
    public FacultyDB dbHandler;
    SQLiteDatabase sqLiteDatabase;
    int id = 0;

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
        dbHandler = new FacultyDB(AddNewFaculty.this);
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
                    String name = fname.getText().toString();
                    String dob = fdob.getText().toString();
                    String doj = fdoj.getText().toString();
                    String mobile = fmobile.getText().toString();
                    String whatsapp = fwhatsapp.getText().toString();
                    String email = femail.getText().toString();
                    String yoe = fyoe.getText().toString();
                    String address = faddress.getText().toString();
                    String referencename = freferencename.getText().toString();
                    String referenceno = freferenceno.getText().toString();
                    String technology = ftechnology.getText().toString();
                    String note = fnote.getText().toString();
                    String qualification = fqualification.getText().toString();
                    String resume = fresume.getText().toString();
                    String aadharcard = faadharcard.getText().toString();
                    String photo = fphoto.getText().toString();
                    long n = dbHandler.addNewFaculty(name, dob, doj, mobile, whatsapp, email, yoe, address, referencename, referenceno, technology, note, qualification, resume, aadharcard, photo);
//                    Toast.makeText(AddNewFaculty.this, "Faculty has been added.", Toast.LENGTH_SHORT).show();
                    if (n != -1) {
                        Toast.makeText(AddNewFaculty.this, "successfully insert", Toast.LENGTH_SHORT).show();
                        cleardata();
                        startActivity(new Intent(AddNewFaculty.this, AdminFaculty.class));
                        finish();
                    } else {
                        Toast.makeText(AddNewFaculty.this, "something wrong try again", Toast.LENGTH_SHORT).show();
                    }
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

                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 7);

            }
        });
        fresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 8);

            }
        });
        faadharcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 9);

            }
        });
        fphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 10);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 8:

                if (resultCode == RESULT_OK) {

                    String PathHolder = data.getData().getPath();
                    Log.d("mylog", PathHolder);
                    fresume.setText(PathHolder);
                    Toast.makeText(AddNewFaculty.this, PathHolder, Toast.LENGTH_LONG).show();

                }
                break;
            case 7:

                if (resultCode == RESULT_OK) {

                    String PathHolder = data.getData().getPath();
                    fqualification.setText(PathHolder);
                    Toast.makeText(AddNewFaculty.this, PathHolder, Toast.LENGTH_LONG).show();

                }
                break;
            case 9:

                if (resultCode == RESULT_OK) {

                    String PathHolder = data.getData().getPath();
                    faadharcard.setText(PathHolder);
                    Toast.makeText(AddNewFaculty.this, PathHolder, Toast.LENGTH_LONG).show();

                }
                break;
            case 10:

                if (resultCode == RESULT_OK) {

                    String PathHolder = data.getData().getPath();
                    fphoto.setText(PathHolder);
                    Toast.makeText(AddNewFaculty.this, PathHolder, Toast.LENGTH_LONG).show();

                }
                break;
        }
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