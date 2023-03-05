package com.example.hibbub.SignUp;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.hibbub.Login.LoginActivity;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyTask extends AsyncTask {
    SignUpActivity signup;
    String user_name;
    public MyTask(SignUpActivity signup, String name)
    {
        this.signup = signup;
        this.user_name = name;
        ;
    }
    String msg="";
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            //String u  ="http://192.168.0.107/android_test/signup.php?" + objects[0];
            String u  ="http://10.0.2.2/android_test/signup.php?" + objects[0];
            Log.d("gilog",u);
            URL url = new URL(u);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            InputStream is = con.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            msg = dis.readLine();
        }
        catch (Exception e)
        {

        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        //Log.d("gilog",msg);
        if(msg.equals("Sucess..")) {
            Intent intent = new Intent(signup, LoginActivity.class);
            signup.startActivity(intent);
            intent.putExtra("name",user_name);
            Log.d("gilog",user_name);
            signup.finish();
        }
        else
        {
            Toast.makeText(signup, "id and password does not match...", Toast.LENGTH_SHORT).show();
        }
    }
}