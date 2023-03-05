package com.example.sample;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    OTP otp;
    TextView ctl,email,password;
    String e="";
    TextView cl,fp;
    ImageView test;
    EditText SE,fillotp,np,cp,otpemail;
    Button sendOTP,confirmOTP,resendOTP,changeP;
    View rl,ll,so,co,gnp;
    Context context;
    int cnt=0;
    View view;
    SharedPreferences sharedPreferences;
    Intent intent;
    Api api;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        view = getWindow().getDecorView().getRootView();
        context = this;

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Verifying");

        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        if(sharedPreferences.contains("email") && sharedPreferences.contains("password"))
        {
            String email=sharedPreferences.getString("email","");
            String status=sharedPreferences.getString("status","");
            Toast.makeText(this,email+status, Toast.LENGTH_LONG).show();
            if(email.equals("") && status.equals(""))
            {

            }
            else {
                Intent intent = new Intent(this, HubsHome.class);
                intent.putExtra("email", email);
                intent.putExtra("status", status);
                startActivity(intent);
            }
        }
        else
        {
            Toast.makeText(this,"not contain", Toast.LENGTH_LONG).show();
        }


        ctl = findViewById(R.id.ctl);
        cl = findViewById(R.id.cl);
        fp = findViewById(R.id.fp);
        SE = findViewById(R.id.SE);
        fillotp = findViewById(R.id.fillotp);
        np = findViewById(R.id.np);
        cp = findViewById(R.id.cp);
        sendOTP = findViewById(R.id.sendOTP);
        otpemail=findViewById(R.id.otpemail);
        confirmOTP = findViewById(R.id.confirmOTP);
        resendOTP = findViewById(R.id.resendOTP);
        changeP = findViewById(R.id.changeP);
        rl = (View) findViewById(R.id.rl);
        ll = (View) findViewById(R.id.ll);
        so = (View) findViewById(R.id.so);
        co = (View) findViewById(R.id.co);
        gnp = (View) findViewById(R.id.gnp);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);/*
        Log.d("Whyred","http://192.168.64.2/dashboard/tryretro/Events/Jack_zeus16.jpg");

        Glide.with(test.getContext()).load("http://192.168.64.2/dashboard/tryretro/Events/Jack_zeus16.jpg").error(R.drawable.ic_address).fallback(R.drawable.ic_access_time_black_24dp).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("Whyred","Load FAiled");
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.d("Whyred","Load");
                return false;
            }
        }).into(test);*/

        ctl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("LayoutChange", "donedonedone");
                rl.setVisibility(View.GONE);
                ll.setVisibility(View.VISIBLE);


            }
        });
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("LayoutChange", "donedonedone");
                rl.setVisibility(View.VISIBLE);
                ll.setVisibility(View.GONE);
            }
        });
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LayoutChange", "donedonedone");
                ll.setVisibility(View.GONE);
                so.setVisibility(View.VISIBLE);

            }
        });

        confirmOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LayoutChange", "donedonedone");

                if(otp.getOtp().equals(fillotp.getText().toString()))
                {
                    co.setVisibility(View.GONE);
                    gnp.setVisibility(View.VISIBLE);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Wrong OTP", Toast.LENGTH_LONG).show();
                }
                //co.setVisibility(View.GONE);
                //gnp.setVisibility(View.VISIBLE);
            }
        });
        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP1));
                Call<OTP> call;
                api = instance.create(Api.class);
                call = api.matchOTP(email.getText().toString());
                call.enqueue(new Callback<OTP>() {
                    @Override
                    public void onResponse(Call<OTP> call, Response<OTP> response) {
                        Toast.makeText(MainActivity.this,"OTP SENT", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<OTP> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Problem in sending new OTP", Toast.LENGTH_LONG).show();

                    }
                });



            }
        });
        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.progressdialog, viewGroup, false);

                builder.setView(dialogView);
                builder.setCancelable(false);
                final AlertDialog alertDialog = builder.create();
                final ProgressBar progressBar=dialogView.findViewById(R.id.progressbar);

                alertDialog.show();

                Log.d("LayoutChange", "donedonedone");
                Retrofit instance = MyRetrofit.getRetrofit(getString(R.string.IP1));
                Call<OTP> call;
                api = instance.create(Api.class);
                call = api.matchOTP(otpemail.getText().toString());
                call.enqueue(new Callback<OTP>() {
                    @Override
                    public void onResponse(Call<OTP> call, Response<OTP> response){
                        alertDialog.cancel();
                        otp=response.body();
                        if(otp.getStatus().equals("fail"))
                        {
                            Toast.makeText(MainActivity.this,"Email is not valid", Toast.LENGTH_LONG).show();
                        }
                        if(otp.getStatus().equals("success"))
                        {
                            co.setVisibility(View.VISIBLE);
                            so.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<OTP> call, Throwable t) {
                        Log.d("Whyred",t.toString());
                    }
                });
                //co.setVisibility(View.VISIBLE);
                //so.setVisibility(View.GONE);
            }
        });
        changeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String NewP=np.getText().toString();
                if(NewP.equals(cp.getText().toString())) {
                    Retrofit instance =  MyRetrofit.getRetrofit(getString(R.string.IP1));
                    Call< ForgotPassword> call;
                    api = instance.create( Api.class);
                    call = api.forgotPassword(otpemail.getText().toString(), np.getText().toString());
                    Log.d("forgotPassword",otpemail.getText().toString()+np.getText().toString());
                    call.enqueue(new Callback< ForgotPassword>() {
                        @Override
                        public void onResponse(Call< ForgotPassword> call, Response< ForgotPassword> response) {
                             ForgotPassword forgotPassword=response.body();
                            Log.d("Whyred",forgotPassword.toString());
                            Log.d("Whyred",forgotPassword.getStatus());
                            if(forgotPassword.getStatus().equals("updated"))
                            {
                                final AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this);
                                ViewGroup viewGroup = (ViewGroup)view.findViewById(android.R.id.content);
                                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.successdialog, viewGroup, false);
                                final Button[] ok = new Button[1];
                                ok[0] = (Button)dialogView.findViewById(R.id.ok);
                                builder.setView(dialogView);
                                builder.setCancelable(false);
                                final AlertDialog alertDialog = builder.create();
                                TextView msg = dialogView.findViewById(R.id.msg);
                                msg.setText("Password has been successfully updated");
                                alertDialog.show();

                                ok[0].setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alertDialog.hide();
                                        ll.setVisibility(View.VISIBLE);
                                        gnp.setVisibility(View.GONE);


                                    }
                                });

                            }
                            if(forgotPassword.getStatus().equals("fail"))
                            {

                            }

                        }

                        @Override
                        public void onFailure(Call< ForgotPassword> call, Throwable t) {
                            Log.d("Whyred",t.toString());
                        }
                    });
                }



            }
        });


    }

    public void check(View view)
    {
        if(SE.getText().toString().trim().isEmpty())
        {
            SE.setError("Enter your Email!");
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(SE.getText().toString().trim()).matches())
        {
            SE.setError("Please fill valid email address");
        }
        else {
            progressDialog.show();
            Retrofit instance =  MyRetrofit.getRetrofit(getString(R.string.IP1));
            //Log.d("Whyred",instance.toString());
            Call< SignupResponse> call;
            api = instance.create( Api.class);
            call = api.check(SE.getText().toString().trim());
            Log.d("email",SE.getText().toString().trim());

            call.enqueue(new Callback< SignupResponse>() {
                @Override
                public void onResponse(Call< SignupResponse> call, Response< SignupResponse> response)
                {
                    progressDialog.dismiss();
                     SignupResponse response1 = response.body();
                    Log.d("responseP", response1.getStatus());
                    validation(response1.getStatus(),SE.getText().toString().trim());
                }

                @Override
                public void onFailure(Call< SignupResponse> call, Throwable t) {
                    Log.d("responseP", "error : " + t.toString());
                }
            });

        }

    }



    void validation(String check, String trim)
    {


        if (check.equals("false"))
        {
            progressDialog.show();
            Retrofit instance=  MyRetrofit.getRetrofit(getString(R.string.IP1));
            Call< OTP> call;
            api=instance.create( Api.class);
            call = api.signupverify(trim);
            call.enqueue(new Callback< OTP>() {
                @Override
                public void onResponse(Call< OTP> call, Response< OTP> response)
                {

                    progressDialog.dismiss();
                    final  OTP otp=response.body();
                    final AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this);
                    ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from( MainActivity.this).inflate(R.layout.confirmotp, viewGroup, false);
                    final Button[] ok = new Button[2];
                    final EditText fillotp = (EditText) dialogView.findViewById(R.id.fillotp);
                    ok[0] = (Button) dialogView.findViewById(R.id.confirmOTP);
                    ok[1] = (Button) dialogView.findViewById(R.id.cancel);
                    builder.setView(dialogView);
                    builder.setCancelable(true);
                    final AlertDialog alertDialog1 = builder.create();
                    alertDialog1.show();


                    Toast.makeText(context,"OTP has been sent!", Toast.LENGTH_LONG).show();
                    ok[0].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(otp.getStatus().equals("success"))
                            {

                                if(fillotp.getText().toString().equals(""))
                                {
                                    fillotp.setError("Enter OTP");
                                }
                                else if (fillotp.getText().toString().length()<6)
                                {
                                    fillotp.setError("Enter valid OTP");
                                }

                                else if(fillotp.getText().toString().equals(otp.getOtp()))
                                {
                                    Intent intent = new Intent(getApplicationContext(),  SignUp.class);
                                    intent.putExtra("email", SE.getText().toString().trim());
                                    startActivity(intent);
                                    alertDialog1.dismiss();
                                }
                                else
                                {
                                    fillotp.setError("Enter correct OTP!");
                                }
                            }
                            else if(otp.getStatus().equals("fail"))
                            {
                                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                                final Button[] ok = new Button[1];
                                View dialogView = LayoutInflater.from(context).inflate(R.layout.successdialog, viewGroup, false);
                                ok[0] = (Button) dialogView.findViewById(R.id.ok);
                                builder.setView(dialogView);
                                builder.setCancelable(false);
                                final TextView msg1 = (dialogView).findViewById(R.id.msg);
                                final TextView header = (dialogView).findViewById(R.id.header);
                                msg1.setText("There's problem with server.\nPlease try again later!");
                                header.setText("Try again later!");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                                ok[0].setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alertDialog.cancel();
                                    }
                                });
                            }
                        }
                    });
                    ok[1].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog1.dismiss();
                        }
                    });

                }

                @Override
                public void onFailure(Call< OTP> call, Throwable t) {
                    Log.d("responseP",t.toString());
                }
            });

        }
        else {
            View v = (View) findViewById(R.id.s);
            v.setVisibility(View.VISIBLE);
        }

    }

    public void login(View view)
    {
        if(email.getText().toString().trim().isEmpty())
        {
            email.setError("Enter your Email!");
        }
        else if(password.getText().toString().isEmpty() )
        {
            password.setError("Enter your Password!");

        }
        else {
            if(!e.equals(email.getText().toString()))
            {
                cnt=0;
            }
            e = email.getText().toString();
            Retrofit instance =  MyRetrofit.getRetrofit(getString(R.string.IP1));
            Call< LoginResponse> call;
            api = instance.create( Api.class);
            call = api.userLogin(email.getText().toString().trim(), password.getText().toString());

            call.enqueue(new Callback< LoginResponse>() {
                @Override
                public void onResponse(Call< LoginResponse> call, Response< LoginResponse> response) {
                    cnt++;
                     LoginResponse response1 = response.body();
                    Log.d("login", response1.getStatus());
                    if (response1.getStatus().equals("A") || response1.getStatus().equals("S") || response1.getStatus().equals("F")) {
                        success(response1.getStatus());
                    }
                    else {
                        fail(response1.getStatus().toString());
                    }
                }

                @Override
                public void onFailure(Call< LoginResponse> call, Throwable t) {
                    Log.d("Whyred",t.toString());
                }
            });


        }
    }
    void success(String status) {

        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email.getText().toString().trim());
        editor.putString("password",password.getText().toString().trim());
        editor.putString("status",status);
        editor.commit();



        if (status.equals("A")) {
            Log.d("Whyred","Faculty");
            Intent intent=new Intent(this,  HubsHome.class);
            intent.putExtra("email",email.getText().toString());
            intent.putExtra("status","Alumni");
            editor.putString("status","Alumni");
            editor.commit();
            startActivity(intent);
        }
        else if (status.equals("F")) {
            Log.d("Whyred","Faculty");
            Intent intent=new Intent(this,  HubsHome.class);
            intent.putExtra("email",email.getText().toString());
            intent.putExtra("status","Faculty");
            editor.putString("status","Faculty");
            editor.commit();
            startActivity(intent);
        }
        else
        {
            Log.d("Whyred","Student");
            Intent intent=new Intent(this,  HubsHome.class);
            intent.putExtra("email",email.getText().toString());
            intent.putExtra("status","Student");
            editor.putString("status","Student");
            editor.commit();
            startActivity(intent);
        }

    }
    void fail(String message)
    {
        int cntp=0,cnte=0;
        String head="";
        if(message.equals("Pending"))
        {
            head = "Account Pending";
            message = "Your account status with\n"+email.getText().toString()+" is still pending.\nContact admin for reactivating the account.";

        }
        else if(message.equals("Deactivated"))
        {
            head = "Account Deactivated";
            message = "Your account with "+email.getText().toString()+" has been deactivated.\n Contact admin for reactivating the account.";
        }
        else if(message.equals("Deleted"))
        {
            head = "Account Deleted";
            message = "Looks like you have requested\nto delete your account"+email.getText().toString();
        }
        else if(message.equals(" IP1"))
        {
            cntp++;
            head = "Incorrect password for\n"+email.getText().toString();
            message = "The password you entered is incorrect.\nPlease try again.";
        }
        else if(message.equals("IE"))
        {
            cnte++;
            head = "Can't Find Account";
            message = "We can't find account with\n"+email.getText().toString()+". Try another email,\nor if you don't have an account,\n you can sign up.";
        }
        if(cntp>0 && cnt>3) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.tryagaindialog, viewGroup, false);
            final Button[] ok = new Button[2];
            ok[0] = (Button) dialogView.findViewById(R.id.Yes);
            ok[1] = (Button) dialogView.findViewById(R.id.No);
            ok[1].setText("Forgot Password");
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            TextView header = dialogView.findViewById(R.id.header);
            msg.setText(message);
            header.setText(head);
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    password.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            password.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    password.setText("");
                                }
                            });                        }
                    });
                }
            });
            ok[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    Log.d("LayoutChange", "donedonedone");
                    ll.setVisibility(View.GONE);
                    so.setVisibility(View.VISIBLE);
                }
            });
        }
        else if(cnte>0) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.tryagaindialog, viewGroup, false);
            final Button[] ok = new Button[2];
            ok[0] = (Button) dialogView.findViewById(R.id.Yes);
            ok[1] = (Button) dialogView.findViewById(R.id.No);
            ok[1].setText("Sign Up");
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            TextView header = dialogView.findViewById(R.id.header);
            msg.setText(message);
            header.setText(head);
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    password.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            password.setText("");
                            email.setText("");
                        }
                    });
                }
            });
            ok[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    Log.d("LayoutChange", "donedonedone");
                    rl.setVisibility(View.VISIBLE);
                    ll.setVisibility(View.GONE);
                }
            });
        }
        else if(cntp>0) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.logindialog, viewGroup, false);
            final Button[] ok = new Button[1];
            ok[0] = (Button) dialogView.findViewById(R.id.ok);
            ok[0].setText("Try again");
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            TextView header = dialogView.findViewById(R.id.header);
            msg.setText(message);
            header.setText(head);
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    password.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            password.setText("");
                        }
                    });                }
            });
        }

        else if(message.equals("Deleted"))
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.logindialog, viewGroup, false);
            final Button[] ok = new Button[1];
            ok[0] = (Button) dialogView.findViewById(R.id.ok);
            ok[0].setText("Ok");
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            TextView header = dialogView.findViewById(R.id.header);
            msg.setText(message);
            header.setText(head);
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                }
            });
        }
        else
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.logindialog, viewGroup, false);
            final Button[] ok = new Button[1];
            ok[0] = (Button) dialogView.findViewById(R.id.ok);
            ok[0].setText("Ok");
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            TextView header = dialogView.findViewById(R.id.header);
            msg.setText(message);
            header.setText(head);
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {

        if(rl.getVisibility()== View.VISIBLE)
        {
            ll.setVisibility(View.VISIBLE);
            rl.setVisibility(View.GONE);
        }
        else if(so.getVisibility()== View.VISIBLE)
        {
            ll.setVisibility(View.VISIBLE);
            so.setVisibility(View.GONE);
        }
        else if(co.getVisibility()== View.VISIBLE)
        {
            so.setVisibility(View.VISIBLE);
            co.setVisibility(View.GONE);
        }
        else if(gnp.getVisibility()== View.VISIBLE)
        {
            co.setVisibility(View.VISIBLE);
            gnp.setVisibility(View.GONE);
        }
        else if(ll.getVisibility()== View.VISIBLE)
        {
           finishAffinity();
        }

        //super.onBackPressed();
    }

}
