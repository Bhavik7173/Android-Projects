package com.example.hibbub.Login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hibbub.ForgotPassword.ForgotPasswordActivity;
import com.example.hibbub.Home.HomeActivity;
import com.example.hibbub.MySharedPre.MySharedPre;
import com.example.hibbub.R;
import com.example.hibbub.RetroFile.MyRetrofit;
import com.example.hibbub.SignUp.SignUpActivity;
import com.example.hibbub.sendnotificationPack.APIService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginActivity extends AppCompatActivity {

    EditText ed1, ed2;
    private FirebaseAuth mAuth;
    MySharedPre sharedPre;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed1 = findViewById(R.id.email);
        ed2 = findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();


        retrofit = MyRetrofit.getRetrofit(getResources().getString(R.string.IP));
        Log.d("mylog", "Retrofit" + retrofit);
        APIService apiService = retrofit.create(APIService.class);

        sharedPre = new MySharedPre(this);
        String fcm = sharedPre.readData("fcm", "");
        apiService.insertKey(fcm).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String status = response.toString();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        //Toast.makeText(this,fcm,Toast.LENGTH_SHORT).show();

    }

    public void signup(View view) {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    public void forgotpwd(View view) {
        Intent i = new Intent(this, ForgotPasswordActivity.class);
        startActivity(i);
    }

    public void login(View view) {
        String name = ed1.getText().toString().trim();
        String pass = ed2.getText().toString().trim();

        if (name.length() == 0) {
            Toast.makeText(this, "enter a name", Toast.LENGTH_SHORT).show();
            return;
        } else if (pass.length() == 0) {
            Toast.makeText(this, "enter a password", Toast.LENGTH_SHORT).show();
            return;
        } else {
            mAuth.signInWithEmailAndPassword(name, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("gilog", "signInWithEmail:success");
                                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(i);
                                finish();
                                MySharedPre sp = new MySharedPre(LoginActivity.this);
                                sp.writeData("login", "done");
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("gilog", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
        }
        /*String data = "a=" + name + "&b=" + pass;
        MyTask task = new MyTask(this, name);
        task.execute(data);

        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
        */
        MySharedPre sp = new MySharedPre(this);
        sp.writeData("login", "done");
    }

    public void reset(View view) {
        ed1.setText("");
        ed2.setText("");

        ed1.requestFocus();
    }

    public void onBackPressed() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);

        b.setMessage("Do you want to Exit?");

        b.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        b.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        b.setCancelable(false);
        // b.setNeutralButton("cancel",null);
        AlertDialog alt = b.create();
        alt.show();
    }

}