package com.gi.application3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyActivity extends Activity  implements View.OnClickListener
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);


    }


    @Override
    public void onClick(View view)
    {
       // mpaudio.pause();
        finish();
        moveTaskToBack(true);
    }

    public void onNew(View view)
    {
        setContentView(R.layout.second);
        Intent intent=new Intent(MyActivity.this, SecondActivity.class);
        startActivity(intent);
        MyActivity.this.finish();

    }



}
