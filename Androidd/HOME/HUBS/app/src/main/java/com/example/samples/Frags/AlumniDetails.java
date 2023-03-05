package com.example.samples.Frags;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.samples.R;

public class AlumniDetails extends AppCompatActivity {

    ProgressBar progressBar;
    TextView Name,Age,Gender,Contact,Status,Email,YOP,Link,Experience,Qualifcation;
    ImageView Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_details);
        Intent intent = getIntent();
        Log.d("Whyred1","alumnidetails");
        Name = findViewById(R.id.Name);
        Age = findViewById(R.id.Age);
        Gender = findViewById(R.id.Gender);
        Contact = findViewById(R.id.Contact);
        Status = findViewById(R.id.Status);
        Email = findViewById(R.id.Email);
        YOP = findViewById(R.id.YOP);
        Link = findViewById(R.id.Link);
        Experience = findViewById(R.id.Experience);
        Qualifcation = findViewById(R.id.Qualification);
        Image = findViewById(R.id.Image);
        progressBar=findViewById(R.id.imageloading);
        progressBar.setVisibility(View.VISIBLE);

        Name.setText(intent.getStringExtra("Name"));
        Age.setText(intent.getStringExtra("Age"));
        Gender.setText(intent.getStringExtra("Gender"));
        Status.setText(intent.getStringExtra("Status"));
        Contact.setText(intent.getStringExtra("Contact"));
        Email.setText(intent.getStringExtra("Email"));
        //Toast.makeText(this,intent.getStringExtra("EI"),Toast.LENGTH_LONG).show();
        Qualifcation.setText(intent.getStringExtra("Qualification"));
        YOP.setText(intent.getStringExtra("YOP"));
        Link.setText(intent.getStringExtra("Link"));
        Experience.setText(intent.getStringExtra("Experience"));
        Glide.with(Image.getContext()).load(getString(R.string.IP)+intent.getStringExtra("Image")).error(R.drawable.ic_person_black_24dp).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(Image);


    }
}
