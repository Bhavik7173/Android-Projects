package com.example.sample.Frags;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.sample.R;
import com.example.sample.account.myActivities.viewPDF;
//import com.github.barteksc.pdfviewer.PDFView;

public class EventDetails extends AppCompatActivity {

    //PDFView pdfView;
    private DownloadManager mgr=null;
    //ProgressBar progressBar;
    TextView EN,ET,EL,ESD,EED,ED,like,dislike;
    EditText Download;
    ImageView Img,IL,IDL;
    String EI,EST,EET,EID,ldl,EF;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Intent intent = getIntent();
        Log.d("Whyred1","eventdetails");
        EN = findViewById(R.id.ETitle);
        ET = findViewById(R.id.T);
        EED = findViewById(R.id.EED);
        ESD = findViewById(R.id.ESD);
        ED = findViewById(R.id.ED);
        EL = findViewById(R.id.location);
        Download=findViewById(R.id.Download);



        Img = findViewById(R.id.Img);
       // progressBar=findViewById(R.id.progressbar);
        EF=intent.getStringExtra("EF");
        Toast.makeText(this, EF, Toast.LENGTH_SHORT).show();
        EN.setText(intent.getStringExtra("EN"));
        EED.setText(intent.getStringExtra("EED"));
        ESD.setText(intent.getStringExtra("ESD"));
        ED.setText(intent.getStringExtra("ED"));
        EL.setText(intent.getStringExtra("EL"));

        EST = intent.getStringExtra("EST");
        EET = intent.getStringExtra("EET");
        EI = intent.getStringExtra("EI");
        EID = intent.getStringExtra("EID");
        ldl = intent.getStringExtra("ldl");
        ET.setText(EST+" to "+EET);
        //Download.setText("Download PDF");



        Glide.with(Img.getContext()).load(getString(R.string.IP)+EI).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(Img);


    }
    public void finish(String l, String dl)
    {
        like.setText(l);
        dislike.setText(dl);
    }

    public void pdfview(View view) {
        Intent intent=new Intent(this,viewPDF.class);
        intent.putExtra("PDF",EF);
        intent.putExtra("Type","Event");
        startActivity(intent);
    }
}
