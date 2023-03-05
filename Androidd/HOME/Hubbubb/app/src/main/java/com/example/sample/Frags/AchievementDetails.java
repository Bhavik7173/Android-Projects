package com.example.sample.Frags;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.sample.AchievementResponse;
import com.example.sample.Api;
import com.example.sample.HubsHome;
import com.example.sample.MyRetrofit;
import com.example.sample.R;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class AchievementDetails extends AppCompatActivity {

    EditText Ach, AY, AR;
    TextView AN;
    int GALLERY_REQUEST=0;
    ImageView Image, editImage;
    Api api;
    Uri imageuri;
    ProgressBar progressBar;
    String email, func, image;
    View view;
    Context context;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_details);

        view = getWindow().getDecorView().getRootView();
        context = this;
        Intent intent = getIntent();
        Log.d("Whyred1", "achdetails");
        AN = findViewById(R.id.AN);
        Ach = findViewById(R.id.Ach);
        AY = findViewById(R.id.AY);
        AR = findViewById(R.id.AR);
        Image = findViewById(R.id.Image);
        editImage = findViewById(R.id.editImage);
        progressBar = findViewById(R.id.imageloading);
        context = this;
        update = findViewById(R.id.update);
        image="";
        Ach.setText(intent.getStringExtra("Ach"));
        AY.setText(intent.getStringExtra("AY"));
        AR.setText(intent.getStringExtra("AR"));
        AN.setText(intent.getStringExtra("AN"));
        func = intent.getStringExtra("func");
        image = intent.getStringExtra("Image");

        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email", null);
        if(!image.isEmpty()) {
            Glide.with(Image.getContext()).load(getString(R.string.IP)+image).error(R.drawable.ic_camera_black_24dp).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    Image.setImageResource(R.drawable.ic_camera_black_24dp);
                    editImage.setVisibility(View.VISIBLE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    editImage.setVisibility(View.VISIBLE);
                    return false;
                }
            }).into(Image);        }
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
        });

        AY.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                Log.d("WhyredPto","hello");
                show();

            }
        });

        if (func.equals("Delete")) {
            update.setVisibility(View.GONE);

            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.confirmdialog, viewGroup, false);
            final Button[] ok = new Button[2];
            ok[0] = (Button) dialogView.findViewById(R.id.Yes);
            ok[1] = (Button) dialogView.findViewById(R.id.No);
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            msg.setText("Are you sure you want to \ndelete your company details?");
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    deleteComapnyDetails();


                }
            });
            ok[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    Intent intent = new Intent(context, HubsHome.class);
                    startActivity(intent);
                }
            });


        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PERMISSION_GRANTED)
        {
            Toast.makeText(context,"GRANTED", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_REQUEST);
        }
        else {
            Toast.makeText(context, "PERMISSION NOT TAKEN", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK)
        {
            try {
                Uri selectedImage = data.getData();
                imageuri = selectedImage;
                Image.setImageURI(selectedImage);
                image = imageToString();
            }
            catch (Exception ex)
            {
                Log.d("Whyred",ex.getMessage());
            }

        }
    }
    private String imageToString()
    {
        Bitmap bitmap=null;
        try
        {
            bitmap= MediaStore.Images.Media.getBitmap(context.getContentResolver(),imageuri);
        }
        catch (Exception ex)
        {
            Log.d("Error",ex.toString());
        }
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] image=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(image, Base64.DEFAULT);
    }

    public void updateCompanyDetails(View view) {
        int flag = 0;
        if (Ach.getText().toString().isEmpty()) {
            flag++;
            Ach.setError("Please fill the empty field!");
        }
        if (AY.getText().toString().isEmpty()) {
            flag++;
            AY.setError("Please fill the empty field!");
        }
        if (AR.getText().toString().isEmpty()) {
            flag++;
            AR.setError("Please fill the empty field!");
        }

        if (flag == 0) {

            Log.d("companyu", Ach.getText() + " " + AR.getText() + " " + AY.getText()+ " " + image + " " + email+" "+"Update");
            Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP));

            Call<AchievementResponse> call;
            api = instance.create(Api.class);
            call = api.achievement_ud(Ach.getText() + "", AR.getText() + "", AY.getText() + "", "", email, "Update");


            call.enqueue(new Callback<AchievementResponse>() {
                @Override
                public void onResponse(Call<AchievementResponse> call, Response<AchievementResponse> response) {
                    final AchievementResponse achievementResponse = response.body();
                    Log.d("responseP", achievementResponse.getStatus());
                    finish(achievementResponse.getStatus(), "Your achievement details has been \nsuccessfully updated!");
                }

                @Override
                public void onFailure(Call<AchievementResponse> call, Throwable t) {
                    Log.d("responseP", "error : " + t.toString());
                }
            });
        }
    }
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public void show()
        {

            final Dialog d = new Dialog(context);
            d.setTitle("Year Of Passing");
            d.setContentView(R.layout.pickerdialog);
            Button b1 = (Button) d.findViewById(R.id.button1);
            Button b2 = (Button) d.findViewById(R.id.button2);
            Date date = new Date();

            final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
            np.setMaxValue(date.getYear()+1900); // max value 100
            np.setMinValue(1900);   // min value 0
            if(AY.getText().toString().trim().isEmpty())
                np.setValue(date.getYear()+1900);
            else
                np.setValue(Integer.parseInt(AY.getText().toString().trim()));

            np.setWrapSelectorWheel(false);
            np.setTextColor(getResources().getColor(R.color.colorAccent));
            np.setTextColor(getResources().getColor(R.color.colorAccent));

            np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                }
            });
            b1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    AY.setText(String.valueOf(np.getValue())); //set the value to textview
                    d.dismiss();
                }
            });
            b2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    d.dismiss(); // dismiss the dialog
                }
            });
            d.show();


        }

        public void deleteComapnyDetails () {


            Log.d("companyu", Ach.getText() + " " + AR.getText() + " " + AY.getText());
            Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP));

            Call<AchievementResponse> call;
            api = instance.create(Api.class);
            call = api.achievement_ud(Ach.getText() + "", AR.getText() + "", AY.getText() + "", image, email, "Delete");


            call.enqueue(new Callback<AchievementResponse>() {
                @Override
                public void onResponse(Call<AchievementResponse> call, Response<AchievementResponse> response) {
                    final AchievementResponse achievementResponse = response.body();
                    Log.d("responseP", achievementResponse.getStatus());

                    finish(achievementResponse.getStatus(), "Your achievement details has been \nsuccessfully deleted!");
                }

                @Override
                public void onFailure(Call<AchievementResponse> call, Throwable t) {
                    Log.d("responseP", "error : " + t.toString());
                }
            });

        }
        public void finish (String check, String msg1)
        {
            if (check.equals("Success")) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.successdialog, viewGroup, false);
                final Button[] ok = new Button[1];
                ok[0] = (Button) dialogView.findViewById(R.id.ok);
                builder.setView(dialogView);
                builder.setCancelable(false);
                final AlertDialog alertDialog = builder.create();
                TextView msg = dialogView.findViewById(R.id.msg);
                msg.setText(msg1);
                alertDialog.show();

                ok[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                        Intent intent = new Intent(context, HubsHome.class);
                        startActivity(intent);


                    }
                });
            } else if (check.equals("Failure")) {
                Toast.makeText(this, "Problem with Server", Toast.LENGTH_LONG).show();
            }
        }
    }

