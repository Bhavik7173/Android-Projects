package com.gi.millioners.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gi.millioners.R;
import com.gi.millioners.model.QuestionPojo;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity{

    TextView result, setTitle, nonAttemptText, attemptText, price;
    String count, title, totalQuestion, nonAttempt, Attempt,winning_price;

    ArrayList<QuestionPojo> questionPojos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Result</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        questionPojos = getIntent().getParcelableArrayListExtra("list");
        result = findViewById(R.id.result);
        nonAttemptText = findViewById(R.id.nonAttemptText);
        attemptText = findViewById(R.id.attemptText);
        price = findViewById(R.id.price);
        setTitle = findViewById(R.id.setTitle);


        count = getIntent().getStringExtra("count");
        Log.d("gilog", count + "");

        title = getIntent().getStringExtra("title");
        Log.d("gilog", title + "");
        totalQuestion = getIntent().getStringExtra("totalQuestion");
        Log.d("gilog", totalQuestion + "");
        winning_price = getIntent().getStringExtra("winning_price");
        Log.d("gilog", price + "");
        nonAttempt = getIntent().getStringExtra("nonAttempt");
        Log.d("gilog", nonAttempt + "");
        Attempt = Integer.parseInt(totalQuestion) - Integer.parseInt(nonAttempt) + "";
        Log.d("gilog", Attempt + "");

        result.setText(count + "/" + totalQuestion);
        setTitle.setText(title);
        price.setText(winning_price);
        nonAttemptText.setText(nonAttempt);
        attemptText.setText(Attempt);

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, QuizActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
}