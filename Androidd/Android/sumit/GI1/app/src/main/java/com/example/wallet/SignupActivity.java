package com.example.wallet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wallet.Response.SignupResponse;
import com.example.wallet.Service.Api;
import com.example.wallet.Service.MyRetrofit;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {

    Api api;
    String cmobileno,a;
    String Name, mobile, Password, AdharCard, Pancard;
    SharedPreferences spf;
    EditText name, cmobile, password, AadhaarCard, pancard;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    ImageView qrCodeIV;
    boolean compressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        spf = getSharedPreferences("MobileNo", MODE_PRIVATE);
        cmobileno = spf.getString("mobileno", null);

        name = findViewById(R.id.fname);
        cmobile = findViewById(R.id.idEdtPhoneNumber);
        password = findViewById(R.id.idEdtPassword);
        AadhaarCard = findViewById(R.id.idEdtAadharProof);
        pancard = findViewById(R.id.idEdtPANProof);
        qrCodeIV = findViewById(R.id.idIVQrcode);
    }

    public void submit(View view) {
        Name = name.getText().toString();
        mobile = cmobile.getText().toString();
        Password = password.getText().toString();
        AdharCard = AadhaarCard.getText().toString();
        Pancard = pancard.getText().toString();

        if (Name.isEmpty()) {
            name.setError("Please Enter Your Full Name");
        } else if (Password.isEmpty()) {
            password.setError("Please Enter Your Password");
        } else if (mobile.isEmpty()) {
            cmobile.setError("Please Enter Your Mobile");
        } else if (AdharCard.isEmpty()) {
            AadhaarCard.setError("Please Enter Your Adhar Card No");
        } else if (Pancard.isEmpty()) {
            pancard.setError("Please Enter Your Pancard ID");
        } else {

            //QRCode Generator Code
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

            // initializing a variable for default display.
            Display display = manager.getDefaultDisplay();

            // creating a variable for point which
            // is to be displayed in QR Code.
            Point point = new Point();
            display.getSize(point);

            // getting width and
            // height of a point
            int width = point.x;
            int height = point.y;

            // generating dimension from width and height.
            int dimen = width < height ? width : height;
            dimen = dimen * 3 / 4;

            // setting this dimensions inside our qr code
            // encoder to generate our qr code.
            qrgEncoder = new QRGEncoder(cmobile.getText().toString(), null, QRGContents.Type.TEXT, dimen);
            try {
                // getting our qrcode in the form of bitmap.
                bitmap = qrgEncoder.encodeAsBitmap();
                Log.d("bitmap",bitmap.toString());
                //Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());

                // the bitmap is set inside our image
                // view using .setimagebitmap method.
                qrCodeIV.setImageBitmap(bitmap);
                Log.d("bitmap",qrCodeIV.toString());
                Log.d("bitmap1",bitmap.toString());

                /*File compressedPictureFile = new File(bitmap.toString());
                Bitmap bitmap1 = BitmapFactory.decodeFile(bitmap.toString());
                FileOutputStream fOut = new FileOutputStream(compressedPictureFile);
                compressed = bitmap1.compress(Bitmap.CompressFormat.PNG, 0, fOut);
                fOut.flush();
                fOut.close();
                */
            } catch (WriterException e) {
                // this method is called for
                // exception handling.
                Log.e("Tag", e.toString());
            }

            //RequestBody requestbody =  RequestBody.create(MediaType.parse("*/*"),imagefile);
           /* MultipartBody.Part fileupload = MultipartBody.Part.createFormData("file",imagefile.getName(),requestbody);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"),imagefile.getName());*/
            String qrcode1 = qrCodeIV.toString();
            //Retrofit Code
            Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP1));
            Call<SignupResponse> call;
            api = instance.create(Api.class);
            call = api.userinfo(Name,mobile,Password,AdharCard,Pancard,bitmap);

            Log.d("login", "btn clicked");
            call.enqueue(new Callback<SignupResponse>() {
                @Override
                public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                    SignupResponse response1 = response.body();
                    Log.d("login2", response.toString());
                    Log.d("login3", response1.getStatus().toString());
                    //Log.d("login3", String.valueOf(response.body()));
                    if (response1.getStatus().toString().equals("success")) {
                        Log.d("login", "hiii");

                        Intent i = new Intent(SignupActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                        onBackPressed();
                    } else {
                        Log.d("login4","Failure");

                    }
                }

                @Override
                public void onFailure(Call<SignupResponse> call, Throwable t) {

                    Log.d("login1", t.toString());
                    //Toast.makeText(this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}