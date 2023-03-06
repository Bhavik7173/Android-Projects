package com.gi.inquiry.admin.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gi.inquiry.R;

public class AddSkill extends AppCompatActivity {

    EditText Skill, Time;
    Context context;
    String email;
    View view;
    String skill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skill);
        //getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Skill</font>"));
        view = getWindow().getDecorView().getRootView();

        Skill = findViewById(R.id.Sk);
        Time = findViewById(R.id.ST);
        context = this;
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email", null);

    }

    public void addSkill(final View view) {
        int flag = 0;
        if (Skill.getText().toString().isEmpty()) {
            flag++;
            Skill.setError("Please fill the empty field!");
        }
        if (Time.getText().toString().isEmpty()) {
            flag++;
            Time.setError("Please fill the empty field!");
        }

        if (flag == 0) {
            skill = "'" + Skill.getText().toString().trim() + "','" + Time.getText().toString().trim() + "'";
            Log.d("skill", skill);


        }
    }

    public void finish(String check) {
        if (check.equals("Success")) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.successdialog, viewGroup, false);
            final Button[] ok = new Button[1];
            ok[0] = (Button) dialogView.findViewById(R.id.ok);
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            msg.setText("Your skill have been \nsuccessfully submitted!");
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    onBackPressed();


                }
            });
        } else if (check.equals("Failure")) {
            Toast.makeText(this, "Problem with Server", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


