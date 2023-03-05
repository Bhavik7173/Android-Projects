package com.example.hibbub.MySharedPre;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class MySharedPre {
    Context context;
    SharedPreferences spf;

    public MySharedPre(Context context) {
        this.context = context;
        spf = context.getSharedPreferences("login_shf", context.MODE_PRIVATE);
    }

    public String readData(String key, String def) {
        String value = spf.getString(key, def);
        return value;
    }

    public void writeData(String key, String value) {
        Log.d("mylog",key+value);
        SharedPreferences.Editor ed = spf.edit();
        ed.putString(key, value);
        ed.commit();
    }
}