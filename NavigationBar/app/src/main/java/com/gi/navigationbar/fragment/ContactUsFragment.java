package com.gi.navigationbar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.gi.navigationbar.R;

public class ContactUsFragment extends Fragment {
    Context context;
    String email;
    String time = "";
    public ContactUsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        View rootView = inflater.inflate(R.layout.fragment_contactus, container, false);

        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_problem_signup, viewGroup, false);
        final Button[] ok = new Button[2];
        ok[0] = (Button) dialogView.findViewById(R.id.submit);
        ok[1] = (Button) dialogView.findViewById(R.id.cancel);
        builder.setView(dialogView);
        builder.setCancelable(false);
        final int[] cnt = {0};
        final EditText email1 = (dialogView).findViewById(R.id.email);
        final EditText name = (dialogView).findViewById(R.id.name);
        final EditText CN = (dialogView).findViewById(R.id.CN);
        final EditText problem = (dialogView).findViewById(R.id.problem);
        final TextView error_time = (dialogView).findViewById(R.id.error_time);
        final Spinner spinner = (Spinner) (dialogView).findViewById(R.id.time_spinner);

        email1.setText(email);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.time_spinner));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                error_time.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();


        ok[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnt[0] = 0;

                time = spinner.getSelectedItem().toString();
                if (name.getText().toString().trim().isEmpty()) {
                    cnt[0]++;
                    name.setError("Fill your name!");
                }
                if (CN.getText().toString().trim().isEmpty()) {
                } else {
                    if (!test(CN)) {
                        cnt[0]++;
                    }
                }
                if (time.isEmpty() || time.equals("Select Time")) {
                    cnt[0]++;
                    error_time.setVisibility(View.VISIBLE);
                }
                if (problem.getText().toString().trim().isEmpty()) {
                    cnt[0]++;
                    problem.setError("Fill your problem!");
                }
                if (cnt[0] == 0) {

                }
//                String msg = ;
            }
        });

        ok[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });

        return rootView;
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
}
/*
    public void submit_feedback(View view) {
        final int[] cnt = {0};
        final EditText email1 = context.findViewById(R.id.email);
        final EditText name = findViewById(R.id.name);
        final EditText CN = findViewById(R.id.CN);
        final EditText problem = findViewById(R.id.problem);
        final TextView error_time = findViewById(R.id.error_time);
        final Spinner spinner = (Spinner)findViewById(R.id.time_spinner);
        time = spinner.getSelectedItem().toString();

        email1.setText(email);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.time_spinner));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                error_time.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (name.getText().toString().trim().isEmpty()) {
            cnt[0]++;
            name.setError("Fill your name!");
        }
        if (CN.getText().toString().trim().isEmpty()) {
        } else {
            if (!test(CN)) {
                cnt[0]++;
            }
        }
        if (time.isEmpty() || time.equals("Select Time")) {
            cnt[0]++;
            error_time.setVisibility(View.VISIBLE);
        }
        if (problem.getText().toString().trim().isEmpty()) {
            cnt[0]++;
            problem.setError("Fill your problem!");
        }
        if (cnt[0] == 0) {

        }
    }


    public void setid(View view) {
        frameLayout = view.findViewById(R.id.flFragment);
        inquiry_admin = view.findViewById(R.id.inquiry_cardview);
        course_admin = view.findViewById(R.id.course_cardview);
        admission_admin = view.findViewById(R.id.admission_cardview);
        faculty_admin = view.findViewById(R.id.faculty_cardview);
    }
}*/
