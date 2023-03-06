package com.gi.application2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gi.application2.R;
import com.gi.application2.adapter.SummaryAdapter;
import com.gi.application2.model.QuestionPojo;
import com.gi.application2.staticfunction.AppSetting;

import java.util.ArrayList;

public class ViewSummary extends AppCompatActivity {

    RecyclerView summaryRView;
    String title;
    TextView heading;
    LinearLayout pageLayout;
    Button change = null;
    boolean flag = true;
    ArrayList<QuestionPojo> quizModelArrayList;

//    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_summary);

//        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ffffff'>Summary</font>"));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        title = getIntent().getStringExtra("title");
        pageLayout = findViewById(R.id.pageLayout);
        summaryRView = findViewById(R.id.summaryRView);
        heading = findViewById(R.id.heading);
        summaryRView.setLayoutManager(new LinearLayoutManager(this));
        heading.setText(title);
        setSummary(0);
        
        int numpages = quizModelArrayList.size() / AppSetting.numbers;
        int rem = quizModelArrayList.size() % AppSetting.numbers;

        if (rem != 0)
            numpages++;

        for (int i = 0; i < numpages; i++) {
            String no = String.valueOf(i + 1);
            Button buttonDes = new Button(ViewSummary.this);
            buttonDes.setText("Page " + no);
            final int temp = i;

            buttonDes.setTextColor(Color.parseColor("#FFFFFF"));
            buttonDes.setBackgroundColor(Color.parseColor("#BAB8B7"));

            if (flag) {
                change = buttonDes;
                buttonDes.setBackgroundColor(Color.parseColor("#F44336"));
                flag = false;
            }

            buttonDes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (change != null) {
                        change.setBackgroundColor(Color.parseColor("#BAB8B7"));
                    }
                    change = buttonDes;
                    buttonDes.setBackgroundColor(Color.parseColor("#F44336"));
                    setSummary(temp * 5);
                }
            });
            pageLayout.addView(buttonDes);
        }
//        loadInterstitialAd();
    }

    public void setSummary(int start) {
        SummaryAdapter adapter = new SummaryAdapter(this, start);
        summaryRView.setAdapter(adapter);
    }

//    private void loadInterstitialAd() {
//        AdRequest adRequest = new AdRequest.Builder().build();
//        InterstitialAd.load(this, getString(R.string.interstitial_full_screen), adRequest, new InterstitialAdLoadCallback() {
//            @Override
//            public void onAdFailedToLoad(LoadAdError loadAdError) {
//                super.onAdFailedToLoad(loadAdError);
//                Log.d("yash-Ad", "Ad Failed");
//                mInterstitialAd = null;
//            }
//
//            @Override
//            public void onAdLoaded(InterstitialAd interstitialAd) {
//                super.onAdLoaded(interstitialAd);
//                Log.d("yash-Ad", "Ad Loaded Successfully");
//                mInterstitialAd = interstitialAd;
//                mInterstitialAd.show(ViewSummary.this);
//                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                    @Override
//                    public void onAdDismissedFullScreenContent() {
//                        super.onAdDismissedFullScreenContent();
//                        Log.d("yash-Ad", "Ad Dismiss");
//                    }
//
//                    @Override
//                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
//                        super.onAdFailedToShowFullScreenContent(adError);
//                        Log.d("yash-Ad", "Ad failed");
//                    }
//
//                    @Override
//                    public void onAdShowedFullScreenContent() {
//                        super.onAdShowedFullScreenContent();
//                        Log.d("yash-Ad", "Ad Loaded Successfully");
//                        mInterstitialAd = null;
//                    }
//                });
//            }
//        });

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}