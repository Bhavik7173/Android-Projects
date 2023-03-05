package com.gi.govardhaninquiry.admin.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.model.CourseModel;
import com.gi.govardhaninquiry.admin.model.MyFacultyData;
import com.gi.govardhaninquiry.retro.RetroInterface;
import com.gi.govardhaninquiry.retro.RetrofitClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAdmissionFragment extends Fragment {

    Context context;
    ArrayList<MyFacultyData> modelArrayList;
    ArrayList<CourseModel> cmodelArrayList;
    List<String> faculty;
    List<String> course;
    ArrayAdapter<String> fdataAdapter, cdataAdapter;
    Spinner fspinner, cspinner, astatus;
    String hour, time, admission_date, status, cname, fname, userID;
    Integer fid;
    Integer cid;

    EditText ihour, itime, iadmission_date, istatus;
    TextView submit, reset;
    SharedPreferences sharedPre;
    private int mYear, mMonth, mDay, mHour, mMinute;

    public NewAdmissionFragment(Context myContext) {
        context = myContext;
        faculty = new ArrayList<String>();
        course = new ArrayList<String>();
        sharedPre = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        userID = sharedPre.getString("userID", null);

        modelArrayList = new ArrayList<>();
        cmodelArrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_admission, container, false);
        fspinner = view.findViewById(R.id.fspinner);
        cspinner = view.findViewById(R.id.cspinner);
        astatus = view.findViewById(R.id.astatus);
        ihour = view.findViewById(R.id.ahour);
        itime = view.findViewById(R.id.time);
        iadmission_date = view.findViewById(R.id.adate);

        submit = view.findViewById(R.id.submitBtn);
        reset = view.findViewById(R.id.resetBtn);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetCall();


            }
        });

        modelArrayList = getFacultyData();
        cmodelArrayList = getCourseData();
        fspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                fname = fspinner.getSelectedItem().toString();
                for (int i = 0; i < modelArrayList.size(); i++) {
                    try {
                        if (fname.equals(modelArrayList.get(i).getName())) {
                            fid = Integer.valueOf(modelArrayList.get(i).getId());
                            Log.d("gilog_fspinner_id", fid + "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//                Toast.makeText(context, name, Toast.LENGTH_SHORT)
//                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        astatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                status = astatus.getSelectedItem().toString();
                Toast.makeText(context, status, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                cname = cspinner.getSelectedItem().toString();
                for (int i = 0; i < cmodelArrayList.size(); i++) {
                    try {
                        if (cname.equals(cmodelArrayList.get(i).getCname())) {
                            cid = cmodelArrayList.get(i).getId();
                            Log.d("gilog_cspinner_id", cid + "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//                Toast.makeText(context, name, Toast.LENGTH_SHORT)
//                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        iadmission_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
//                        DOB.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        iadmission_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        itime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                itime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hour = ihour.getText().toString();
                time = itime.getText().toString();
                admission_date = iadmission_date.getText().toString();
                int cnt = 0;
                if (ihour.getText().toString().trim().isEmpty()) {
                    cnt++;
                    ihour.setError("Fill your batch hour!");
                }
                if (itime.getText().toString().trim().isEmpty()) {
                    cnt++;
                    itime.setError("Fill your batch timing!");
                }
                if (iadmission_date.getText().toString().trim().isEmpty()) {
                    cnt++;
                    iadmission_date.setError("Fill your date of admission!");
                }
                if (cnt == 0) {

                    Log.d("fid & cid", fid + "\n" + cid);
                    RetrofitClient.getClient(context).create(RetroInterface.class).applybatch(userID, String.valueOf(fid), String.valueOf(cid), hour, time, admission_date, status)
                            .enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    String responseFromAPI = response.body();
                                    if (responseFromAPI.equals("Success")) {
                                        Toast.makeText(context, "Successfully Apply", Toast.LENGTH_SHORT).show();
                                        resetCall();
                                    }

                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Toast.makeText(context, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d("gisurat", t.toString());
                                }
                            });
                }
            }
        });
        return view;
    }

    private void resetCall() {
        fspinner.setSelection(0);
        cspinner.setSelection(0);
        astatus.setSelection(0);
        ihour.setText("");
        itime.setText("");
        iadmission_date.setText("");
    }

    public ArrayList<MyFacultyData> getFacultyData() {
        RetrofitClient.getClient(context).create(RetroInterface.class).facultyDetail()
                .enqueue(new Callback<ArrayList<MyFacultyData>>() {
                    @Override
                    public void onResponse(Call<ArrayList<MyFacultyData>> call, Response<ArrayList<MyFacultyData>> response) {
                        modelArrayList = response.body();
                        Log.d("gilog_model", modelArrayList.toString());
                        for (int i = 0; i < modelArrayList.size(); i++) {
                            try {
                                Log.d("gilog_model", modelArrayList.get(i).getName());
                                faculty.add(modelArrayList.get(i).getName());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d("gilog_model", faculty + "");
                        fdataAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, faculty);
                        fdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        fspinner.setAdapter(fdataAdapter);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<MyFacultyData>> call, Throwable t) {
                        Toast.makeText(context, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("gisurat", t.toString());
                    }
                });
        return modelArrayList;
    }

    public ArrayList<CourseModel> getCourseData() {
        RetrofitClient.getClient(context).create(RetroInterface.class).courseDetail()
                .enqueue(new Callback<ArrayList<CourseModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CourseModel>> call, Response<ArrayList<CourseModel>> response) {
                        cmodelArrayList = response.body();
                        Log.d("gilog", cmodelArrayList.toString());
                        for (int i = 0; i < cmodelArrayList.size(); i++) {
                            try {
                                Log.d("gilog_model", cmodelArrayList.get(i).getCname());
                                course.add(cmodelArrayList.get(i).getCname());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d("gilog_model", course + "");
//                        cspinner.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, course));
                        cdataAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, course);
                        cdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        cspinner.setAdapter(cdataAdapter);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<CourseModel>> call, Throwable t) {
                        Toast.makeText(context, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("gisurat", t.toString());
                    }
                });
        return cmodelArrayList;
    }
}
