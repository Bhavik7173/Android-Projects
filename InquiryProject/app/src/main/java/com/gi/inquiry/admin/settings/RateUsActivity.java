package com.gi.inquiry.admin.settings;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.inquiry.R;

public class RateUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);

        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Rate Us</font>"));
        final EditText RD = (EditText) findViewById(R.id.RD);
        Button RU = (Button) findViewById(R.id.RU);
        final RatingBar RR = (RatingBar) findViewById(R.id.RR);
        final float[] n = {0};
        final Button[] ok = new Button[1];

        RR.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                n[0] = rating;
                Log.d("rate_us", n[0] + "");

            }
        });

        RU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
//
            }
        });
    }
}