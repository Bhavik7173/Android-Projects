package com.example.wallet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.wallet.Service.Api;
import com.example.wallet.Service.MyRetrofit;
import com.google.android.material.navigation.NavigationView;

import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button send, receive, scanqrcode;
    EditText ed1, ed2, RD;
    Api api;
    String cmobileno;
    SharedPreferences spf;
    NavigationView nv;
    Bitmap bitmap;
    View rating;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        spf = getSharedPreferences("MobileNo", MODE_PRIVATE);
        cmobileno = spf.getString("mobileno", null);

        nv = findViewById(R.id.nav_view);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        scanqrcode = findViewById(R.id.qr_code);
        RD = findViewById(R.id.rd);
        rating = findViewById(R.id.RR);
        send = findViewById(R.id.send);
        receive = findViewById(R.id.receive);
        ed1 = findViewById(R.id.mobile_no1);
        ed2 = findViewById(R.id.amount);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String cmobileno = "6353539097";
                Log.d("successlog", cmobileno);
                String mobile = ed1.getText().toString();
                String amount = ed2.getText().toString();
                if (ed1.getText().toString().isEmpty()) {
                    Toast.makeText(HomeActivity.this, "Enter Mobile No", Toast.LENGTH_SHORT).show();
                } else if (ed2.getText().toString().isEmpty()) {
                    Toast.makeText(HomeActivity.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                } else {
                    Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP1));
                    Call<PaymentResponse> call;
                    api = instance.create(Api.class);
                    call = api.paymentsend(cmobileno, mobile, amount);

                    Log.d("Whyred1", api.toString());
                    call.enqueue(new Callback<PaymentResponse>() {
                        @Override
                        public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                            PaymentResponse pr = response.body();
                            Log.d("Whyred1", pr.toString());
                            Log.d("Whyred1", pr.getStatus());

                            Intent i = new Intent(HomeActivity.this, SuccessActivity.class);
                            startActivity(i);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<PaymentResponse> call, Throwable t) {
                            Log.d("Whyred", t.toString());
                        }
                    });
                }
            }
        });

        scanqrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, QrcodeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    ok[0].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick ( final View view){
            Log.d("Rating", n[0] + "");
            final String rate_entry = "'" + cmobileno + "','"  + RD.getText().toString() + "','" + cmobileno + "'";
            Log.d("Rating1", rate_entry);

            Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP1));
            Call<RateUsResponse> call;
            api = instance.create(Api.class);
            call = api.rateus(n[0], RD.toString(), cmobileno);
            Log.d("Rating", rate_entry);
            call.enqueue(new Callback<RateUsResponse>() {
                @Override
                public void onResponse(Call<RateUsResponse> call, Response<RateUsResponse> response) {
                    Log.d("mylog", response.body().getStatus());


                }

                @Override
                public void onFailure(Call<RateUsResponse> call, Throwable t) {
                    Log.d("mylog", t.toString());
                }
            });

        }
    });

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            Toast.makeText(getApplicationContext(), "Account is clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_bank_account) {
            Toast.makeText(getApplicationContext(), "Bank Account is clicked", Toast.LENGTH_SHORT).show();

        }/*else if (id == R.id.nav_qrcode) {
            Toast.makeText(getApplicationContext(), "QR Code is clicked", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, QrCodeFragment.class);
            startActivity(i);
            finish();
        } */ else if (id == R.id.nav_order_booking) {
            Toast.makeText(getApplicationContext(), "Order & Booking is clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_setting) {
            Toast.makeText(getApplicationContext(), "Setting is clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_help_support) {
            Toast.makeText(getApplicationContext(), "Help & Support is clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_contactus) {
            Toast.makeText(getApplicationContext(), "Contact us is clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_feedback) {

            Toast.makeText(getApplicationContext(), "Feedback is clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_rateus) {
            Toast.makeText(getApplicationContext(), "Rate us is clicked", Toast.LENGTH_SHORT).show();
            Log.d("Rating", n[0] + "");
            final String rate_entry = "'" + cmobileno + "','" + n[0] + "','" + RD.getText().toString() + "','" + id + "'";
            Log.d("Rating1", rate_entry);

            Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP1));
            Call<RateUsResponse> call;
            api = instance.create(Api.class);
            call = api.rateus(cmobileno, n[0], RD.toString(), id);
            Log.d("Rating", rate_entry);
            call.enqueue(new Callback<RateUsResponse>() {
                @Override
                public void onResponse(Call<RateUsResponse> call, Response<RateUsResponse> response) {
                    Log.d("mylog", response.body().getStatus());

                }
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return false;
    }
}