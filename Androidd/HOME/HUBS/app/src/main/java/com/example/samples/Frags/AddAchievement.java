package com.example.samples.Frags;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.samples.AchievementResponse;
import com.example.samples.Api;
import com.example.samples.HubsHome;
import com.example.samples.MyRetrofit;
import com.example.samples.PostEventsResponse;
import com.example.samples.R;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class AddAchievement extends AppCompatActivity {

    EditText Ach,YOW,Rank,Image;
    Context context;
    String email;
    View view;
    Api api;
    String ach;

    int GALLERY_REQUEST=0;
    int PDF_REQUEST=1;
    Uri uri,pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_achievement);

        view = getWindow().getDecorView().getRootView();

        Ach = findViewById(R.id.Ach);
        YOW = findViewById(R.id.YOW);
        Rank = findViewById(R.id.Rank);
        Image = findViewById(R.id.AImg);
        context = this;
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL",MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);


        YOW.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                Log.d("WhyredPto","hello");
                show();
            }
        });

        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image.setText("No Banner Selected");
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void show()
    {

        final Dialog d = new Dialog(this);
        d.setTitle("Year Of Passing");
        d.setContentView(R.layout.pickerdialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        Date date = new Date();

        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(date.getYear()+1900); // max value 100
        np.setMinValue(1900);   // min value 0
        if(YOW.getText().toString().trim().isEmpty())
            np.setValue(date.getYear()+1900);
        else
            np.setValue(Integer.parseInt(YOW.getText().toString().trim()));
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
                YOW.setText(String.valueOf(np.getValue())); //set the value to textview
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
    public void addAchievement(View view) {
        int flag = 0;
        if(Ach.getText().toString().isEmpty())
        {
            flag++;
            Ach.setError("Please fill the empty field!");
        }
        if(YOW.getText().toString().isEmpty())
        {
            flag++;
            YOW.setError("Please fill the empty field!");
        }
        if(Rank.getText().toString().isEmpty())
        {
            flag++;
            Rank.setError("Please fill the empty field!");
        }
        if(Image.getText().toString().isEmpty())
        {
            flag++;
            Image.setError("Please upload the image!");
        }

        if(flag==0) {
            Log.d("Frags", "Button Pressed");
            int ind = email.indexOf('@');
            String path = "Achievements/" + Ach.getText().toString() + "_" + email.substring(0, ind) + ".jpg";
            ach = "'" + Ach.getText().toString() + "','" + YOW.getText().toString() + "','" + path + "','" + Rank.getText().toString() + "'";
            String image = imageToString();

            Log.d("post", ach);
            Log.d("post", path);
            Log.d("post", image);
            Log.d("post", email);
            Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP));

            Call<AchievementResponse> call;
            api = instance.create(Api.class);
            call = api.achievement(ach, email, image, path);

            call.enqueue(new Callback<AchievementResponse>() {
                @Override
                public void onResponse(Call<AchievementResponse> call, Response<AchievementResponse> response) {
                    AchievementResponse achievementResponse = response.body();
                    //Log.d("responseP", achievementResponse.getStatus());
                    finish(achievementResponse.getStatus());
                }

                @Override
                public void onFailure(Call<AchievementResponse> call, Throwable t) {
                    Log.d("responseP", "error : " + t.toString());
                }
            });
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PERMISSION_GRANTED)
        {
            Toast.makeText(context,"GRANTED",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_REQUEST);
        }
        else {
            Toast.makeText(context, "PERMISSION NOT TAKEN", Toast.LENGTH_LONG).show();
        }
    }
    private String imageToString()
    {
        Bitmap bitmap=null;
        try
        {
            bitmap= MediaStore.Images.Media.getBitmap(context.getContentResolver(),uri);
        }
        catch (Exception ex)
        {
            Log.d("Error",ex.toString());
        }
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] image=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(image,Base64.DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK)
        {
            try
            {
                uri = data.getData();
                if(!uri.toString().equals(""))
                {
                    Image.setText("No file Selected");
                }
                if(uri.toString().length()>0)
                {
                    Image.setText("Banner Selected");
                }
            }
            catch (Exception ex)
            {
                Log.d("Whyred",ex.getMessage());
            }

        }

    }
    public void finish(String check)
    {
        if(check.equals("Success")) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ViewGroup viewGroup = (ViewGroup)view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.successdialog, viewGroup, false);
            final Button[] ok = new Button[1];
            ok[0] = (Button)dialogView.findViewById(R.id.ok);
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            msg.setText("Your achievement have been \nsuccessfully submitted!");
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    onBackPressed();


                }
            });
        }
        else if(check.equals("Failure"))
        {
            Toast.makeText(this,"Problem with Server",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
