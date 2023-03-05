package com.example.hibbub.Login;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.hibbub.Home.HomeActivity;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyTask extends AsyncTask {
    LoginActivity login;
    String user_name;
    public MyTask(LoginActivity login, String name)
    {
        this.login = login;
        this.user_name = name;
    }
    String msg="";
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            //String u  ="http://192.168.0.107/android_test/login_db.php?" + objects[0];
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
        if(msg.equals("success")) {
            Intent intent = new Intent(login, HomeActivity.class);
            login.startActivity(intent);
            intent.putExtra("name",user_name);
            Log.d("gilog",user_name);
            login.finish();
        }
        else
        {
            Toast.makeText(login, "id and password match...", Toast.LENGTH_SHORT).show();
        }
    }
}
