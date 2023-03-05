package com.gi.jsonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SetDataActivity extends AppCompatActivity {

    EditText name,email,mobile;
    Button submit;
    String n,e,m;
    ArrayList<String> personNames = new ArrayList<>();
    ArrayList<String> emailIds = new ArrayList<>();
    ArrayList<String> mobileNumbers = new ArrayList<>();
    String path = "users.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_data);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n = name.getText().toString();
                e = email.getText().toString();
                m = mobile.getText().toString();
                try{
                JSONObject obj = new JSONObject(loadJSONFromAsset());
                JSONArray userArray = obj.getJSONArray("users");
                for (int i = 0; i < userArray.length(); i++) {
                    JSONObject userDetail = userArray.getJSONObject(i);
                    personNames.add(userDetail.getString("name"));
                    emailIds.add(userDetail.getString("email"));
                    JSONObject contact = userDetail.getJSONObject("contact");
                    mobileNumbers.add(contact.getString("mobile"));
                }
                }catch(Exception e)
                {
                    e.printStackTrace();
                }

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name", n);
                    jsonObject.put("email", e);
                    jsonObject.put("mobile", m);
                    String userString = jsonObject.toString();
                    Log.d("mylog",userString);
                    try {
                        FileWriter file = new FileWriter(path);
                        file.write(jsonObject.toString());
                        file.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                
            }
        });
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("users_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}