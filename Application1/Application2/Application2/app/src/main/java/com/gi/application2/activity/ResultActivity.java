package com.gi.application2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.application2.R;

public class ResultActivity extends AppCompatActivity {

    TextView result, setTitle, nonAttemptText, attemptText;
    String count, title, totalQuestion, nonAttempt, Attempt;
    Button viewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Result</font>"));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        result = findViewById(R.id.result);
        nonAttemptText = findViewById(R.id.nonAttemptText);
        attemptText = findViewById(R.id.attemptText);
        setTitle = findViewById(R.id.setTitle);
        viewResult = findViewById(R.id.viewResult);

        count = getIntent().getStringExtra("count");
        Log.d("gilog",count+"");

        title = getIntent().getStringExtra("title");
        Log.d("gilog",title+"");
        totalQuestion = getIntent().getStringExtra("totalQuestion");
        Log.d("gilog",totalQuestion+"");
        nonAttempt = getIntent().getStringExtra("nonAttempt");
        Log.d("gilog",nonAttempt+"");
        Attempt = Integer.parseInt(totalQuestion) - Integer.parseInt(nonAttempt) + "";
        Log.d("gilog",Attempt+"");

        result.setText(count + "/" + totalQuestion);
        setTitle.setText(title);
        nonAttemptText.setText(nonAttempt);
        attemptText.setText(Attempt);


        viewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ViewSummary.class);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
    }
}