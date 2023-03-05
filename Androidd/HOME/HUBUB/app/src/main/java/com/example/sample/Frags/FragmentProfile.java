package com.example.sample.Frags;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.sample.Api;
import com.example.sample.HubsHome;
import com.example.sample.MyRetrofit;
import com.example.sample.ProfileResponse;
import com.example.sample.R;
import com.example.sample.Userinfo;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class FragmentProfile extends Fragment {

    String email;
    Api api;
    String image,per_prof;
    Context context;
    Uri imageuri;
    int GALLERY_REQUEST=0;
    Button edit;
    ImageView imageView,editImage;
    View view;
    EditText mainName,mainEmail,mainAddress,mainPhone,mainDOB;
    ProgressBar progressBar;

    public FragmentProfile(Context context)
    {
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.profile,null);
        mainName=view.findViewById(R.id.mainName);
        mainEmail=view.findViewById(R.id.mainEmail);
        mainAddress=view.findViewById(R.id.mainAddress);
        mainPhone=view.findViewById(R.id.mainPhone);
        edit=view.findViewById(R.id.edit);
        mainDOB=view.findViewById(R.id.mainDOB);
        imageView=view.findViewById(R.id.mainimage);
        editImage=view.findViewById(R.id.editImage);
        image="";
        progressBar=view.findViewById(R.id.imageloading);
        progressBar.setVisibility(View.VISIBLE);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<Userinfo> call;
        api=instance.create(Api.class);
        call=api.userinfo(email);
        call.enqueue(new Callback<Userinfo>() {
            @Override
            public void onResponse(Call<Userinfo> call, Response<Userinfo> response) {
                Userinfo userinfo=response.body();
                set(userinfo.getPE(),userinfo.getPN(),userinfo.getPI(),userinfo.getPC(),
                        userinfo.getPA(),userinfo.getDOB());
            }

            @Override
            public void onFailure(Call<Userinfo> call, Throwable t) {
                Log.d("Whyred",t.toString());
            }
        });
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
        });

        mainDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener;
                listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        mainDOB.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                };
                Date d = new Date();
                Calendar c1 = Calendar.getInstance();
                //Calendar c2 = Calendar.getInstance();
                Date d1 = new Date(d.getYear()-18,12,31);
                // Date d2 = new Date(d.getYear()-1000,1,1);
                c1.setTime(d1);
                //c2.setTime(d2);
                DatePickerDialog dp;
                dp = new DatePickerDialog(context, R.style.DialogTheme, listener, d.getYear() + 1900-18, 12, 31);
                dp.getDatePicker().setMaxDate(c1.getTime().getTime());
                //dp.getDatePicker().setMinDate(c2.getTime().getTime());
                dp.show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit.getText().toString().equalsIgnoreCase("edit"))
                {
                    edit.setText("save");
                    mainName.setEnabled(true);
                    mainAddress.setEnabled(true);
                    mainPhone.setEnabled(true);
                    mainDOB.setEnabled(true);
                    mainDOB.setClickable(true);
                    imageView.setClickable(true);
                    editImage.setVisibility(View.VISIBLE);
                }
                else if(edit.getText().toString().equalsIgnoreCase("save"))
                {
                    int flag=0;
                    if(mainName.getText().toString().trim().isEmpty())
                    {
                        flag++;
                        mainName.setError("Please fill the empty field!");
                    }
                    if(mainAddress.getText().toString().trim().isEmpty())
                    {
                        flag++;
                        mainAddress.setError("Please fill the empty field!");
                    }
                    if(mainDOB.getText().toString().trim().isEmpty())
                    {
                        flag++;
                        mainDOB.setError("Please fill the empty field!");
                    }
                    if(mainPhone.getText().toString().trim().isEmpty())
                    {
                        flag++;
                        mainPhone.setError("Please fill the empty field!");
                    }
                    if(flag==0)
                    {
                        send();
                    }
                }
            }
        });
        return view;
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
                Glide.with(context).load(Uri.parse(selectedImage.getPath())).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).into(imageView);

                //imageView.setImageURI(selectedImage);
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
            //imageView.setImageBitmap(getRoundedCornerBitmap(bitmap,360));
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
    void set(String PE, String PN, String PI, String PC, String PA, String DOB)
    {
        mainName.setText(PN);
        mainEmail.setText(PE);
        mainAddress.setText(PA);
        mainDOB.setText(DOB);
        mainPhone.setText(PC);
        Log.d("imagepathfunction",PI);
        Glide.with(imageView.getContext()).load(getString(R.string.IP1)+PI).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(imageView);
    }
    void send()
    {
        per_prof="'"+mainName.getText().toString().trim()+"','"+mainDOB.getText().toString().trim()+"','"+mainPhone.getText().toString().trim()+"','"+mainAddress.getText().toString().trim();

        Log.d("companyu",per_prof+""+image+"h");
        Log.d("companyu",image+"h");
        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));

        Call<ProfileResponse> call;
        api=instance.create(Api.class);
        call=api.perprofile_u(mainName.getText().toString().trim(), mainDOB.getText().toString().trim(), mainPhone.getText().toString().trim(), mainAddress.getText().toString().trim(), image, email);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                final ProfileResponse profileResponse=response.body();
                Log.d("responseP",profileResponse.getStatus());

                finish(profileResponse.getStatus(),"Your personal profile details has been \nsuccessfully updated!");
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.d("responseP","error : "+t.toString());
            }
        });

    }
    public void finish(String check, String msg1)
    {
        if(check.equals("Success")) {
            edit.setText("edit");
            mainName.setEnabled(false);
            mainAddress.setEnabled(false);
            mainPhone.setEnabled(false);
            mainDOB.setEnabled(false);
            mainDOB.setClickable(false);
            imageView.setClickable(false);
            editImage.setVisibility(View.GONE);

            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ViewGroup viewGroup = (ViewGroup)view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.successdialog, viewGroup, false);
            final Button[] ok = new Button[1];
            ok[0] = (Button)dialogView.findViewById(R.id.ok);
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
        }
        else if(check.equals("Failure"))
        {
            Toast.makeText(context,"Problem with Server", Toast.LENGTH_LONG).show();
        }
    }
}
