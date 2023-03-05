package com.gi.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText id,name,contact,email;
    Button insertBtn,deleteBtn,searchBtn,updateBtn,displayBtn,resetBtn,cameraBtn;
    LinearLayout linearLayout;
    TextView cid,cname,cemail,ccontact;
    String ename,econtact,eid,eemail;
    Intent intent;
    ImageView imageView;
    List<Contact> myListData;
    MyListAdapter adapter;
    RecyclerView recyclerView;

    public  static final int RequestPermissionCode  = 1 ;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;

    RetrofitClient retrofitClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.eid);
        name = findViewById(R.id.ename);
        contact = findViewById(R.id.econtact);
        email = findViewById(R.id.eemail);
        imageView = findViewById(R.id.iview);

        insertBtn = findViewById(R.id.insert);
        deleteBtn = findViewById(R.id.delete);
        searchBtn = findViewById(R.id.search);
        updateBtn = findViewById(R.id.update);
        displayBtn = findViewById(R.id.display);
        resetBtn = findViewById(R.id.reset);
        cameraBtn = findViewById(R.id.camera);
        linearLayout = findViewById(R.id.contact_layout);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ename = name.getText().toString();
                econtact = contact.getText().toString();
                eemail = email.getText().toString();
                eid = id.getText().toString();

                retrofitClient = new RetrofitClient();
                RetrofitClient.getClient(MainActivity.this).create(RetroInterface.class).createPost(eid,ename,econtact,eemail).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
                        String responseFromAPI = response.body();
                        reset();
                        String responseString = "Name : " + name + "\n" + "Contact : " + contact + "Email : " + email;
                        Toast.makeText(MainActivity.this,responseString,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MainActivity.this," " + t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.d("gisurat",t.toString());
                    }
                });



                }
        });

        displayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(MainActivity.this,DetailContact.class);
                startActivity(intent);


            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                econtact = contact.getText().toString();
                if(econtact.isEmpty())
                {
                    contact.setError("Please Enter the Contact No...");
                }
                else {
                    retrofitClient = new RetrofitClient();
                    RetrofitClient.getClient(MainActivity.this).create(RetroInterface.class).deleteContact(econtact).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(MainActivity.this, "Delete Contact", Toast.LENGTH_SHORT).show();
                            String responseFromAPI = response.body();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(MainActivity.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("gisurat", t.toString());
                        }
                    });
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                econtact = contact.getText().toString();
                if(econtact.isEmpty())
                {
                    contact.setError("Please Fill the Contact number..");
                }
                else
                {
                    ename = name.getText().toString();
                    eemail = email.getText().toString();
                    eid = id.getText().toString();
                    retrofitClient = new RetrofitClient();
                    RetrofitClient.getClient(MainActivity.this).create(RetroInterface.class).updateContact(eid,ename,econtact,eemail).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(MainActivity.this, "Data Updated to API", Toast.LENGTH_SHORT).show();
                            String responseFromAPI = response.body();
                            reset();
                            String responseString = "Name : " + name + "\n" + "Contact : " + contact + "Email : " + email;
                            Toast.makeText(MainActivity.this,responseString,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(MainActivity.this," " + t.getMessage(),Toast.LENGTH_SHORT).show();
                            Log.d("gisurat",t.toString());
                        }
                    });

                }
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                econtact = contact.getText().toString();
                if(econtact.isEmpty())
                {
                    contact.setError("Please Fill the Contact number..");
                }
                else
                {
                    intent = new Intent(getApplicationContext(), SearchActivity.class);
                    intent.putExtra("message_key", econtact);
                    startActivity(intent);
                }
            }
        });

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnableRuntimePermissionToAccessCamera();
                selectImage();
            }
        });


    }

    public void reset()
    {
        id.setText("");
        name.setText("");
        contact.setText("");
        email.setText("");
    }

    public void EnableRuntimePermissionToAccessCamera(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
        {

            // Printing toast message after enabling runtime permission.
            Toast.makeText(this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
//                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(camera_intent, 1);
                    intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(intent, 7);
                }
                else if (options[item].equals("Choose from Gallery"))
                {

                    checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT) .show();
            }
            else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT) .show();
            }
        }
        else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 7) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);

            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath+"");
                imageView.setImageBitmap(thumbnail);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}