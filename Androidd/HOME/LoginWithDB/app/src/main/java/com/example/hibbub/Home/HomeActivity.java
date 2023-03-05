package com.example.hibbub.Home;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hibbub.PostEvent.PostEventActivity;
import com.example.hibbub.R;
import com.google.android.gms.common.api.Api;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

//import de.hdodenhof.circleimageview.CircleImageView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener,RecyclerViewClickInterface {

    SharedPreferences sharedPreferences;
    String email, imagepath, status;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    //CircleImageView imageView;
    Api api;
    int manageid = 0;
    Userinfo userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        status = intent.getStringExtra("status");
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email", null);
        status = sharedPreferences.getString("status", null);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.inflateMenu(R.menu.bottom_navigation);

        displayFrags(R.id.home, "Home");

    }


    public void displayFrags(int itemId, String itemTitle) {
        FragmentTransaction ft;
        FragmentManager fm;

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        Log.d("gisurat", itemTitle);


        switch (itemId) {

            case R.id.home:
                if (manageid == R.id.home) {
                    itemTitle = "HUBBUB";
                    Log.d("gisurat", "Not Changed");
                } else {
                    itemTitle = "HUBBUB";
                    manageid = R.id.home;
                    HomeFragment home = new HomeFragment(this);
                    ft.replace(R.id.container, home);
                }
                break;
            case R.id.navigationTodaysEvent:
                Log.d("SWITCH", "Slideshow");
                Intent intent = new Intent(this, EventsActivity.class);
                intent.putExtra("data", "TEVENTS");
                startActivity(intent);

                break;
           /* case R.id.navigationProProfile:
                Log.d("SWITCH","Slideshow");
                if(manageid==R.id.navigationProProfile)
                {
                    Log.d("Whyred","Not Changed");
                }
                else {
                    manageid = R.id.navigationProProfile;
                    FragmentProProfile fragmentProProfile = new FragmentProProfile(this);
                    ft.replace(R.id.container, fragmentProProfile);
                }
                break;*/
            /*case R.id.account:
                Log.d("SWITCH", "Slideshow");
                if (manageid == R.id.account) {
                    Log.d("Whyred", "Not Changed");
                } else {
                    manageid = R.id.navigationStudentProfile;
                    FragmentAccount fragmentAccount = new FragmentAccount(this);
                    ft.replace(R.id.container, fragmentAccount);
                }
                break;
            case R.id.navigationStudentProfile:
                Log.d("SWITCH", "Slideshow");
                if (manageid == R.id.navigationStudentProfile) {
                    Log.d("Whyred", "Not Changed");
                } else {
                    manageid = R.id.navigationStudentProfile;
                    FragmentStudentProfile fragmentStudentProfile = new FragmentStudentProfile();
                    ft.replace(R.id.container, fragmentStudentProfile);
                }
                break;
            case R.id.navigationMyProfile:
                Log.d("SWITCH", "navgallery");
                if (manageid == R.id.navigationMyProfile) {
                    Log.d("Whyred", "Not Changed");
                } else {
                    manageid = R.id.navigationMyProfile;
                    FragmentProfile fragmentProfile = new FragmentProfile(this);
                    ft.replace(R.id.container, fragmentProfile);
                }
                break;
            case R.id.navigationSearch:
                if (manageid == R.id.navigationSearch) {
                    Log.d("Whyred", "Not Changed");
                } else {
                    manageid = R.id.navigationSearch;
                    FragmentSearch fragmentSearch = new FragmentSearch(this);
                    ft.replace(R.id.container, fragmentSearch);
                }
                break;
            case R.id.POSTEVENTS:
                Log.d("SWITCH", "Slideshow");
                if (manageid == R.id.POSTEVENTS) {
                    Log.d("Whyred", "Not Changed");
                } else {
                    manageid = R.id.POSTEVENTS;
                    FragmentPostEvents fragmentPostEvents = new FragmentPostEvents(this);
                    ft.replace(R.id.container, fragmentPostEvents);
                }
                break;
            case R.id.rateus:
                Log.d("SWITCH", "Slideshow");
                if (manageid == R.id.rateus) {
                    Log.d("Whyred", "Not Changed");
                } else {
                    manageid = R.id.rateus;
                    FragmentRateUs fragmentRateUs = new FragmentRateUs(this);
                    ft.replace(R.id.container, fragmentRateUs);
                }
                break;
            case R.id.feedback:
                Log.d("SWITCH", "Slideshow");
                if (manageid == R.id.feedback) {
                    Log.d("Whyred", "Not Changed");
                } else {
                    manageid = R.id.feedback;
                    FragmentFeedback fragmentFeedback = new FragmentFeedback(this);
                    ft.replace(R.id.container, fragmentFeedback);
                }
                break;

            case R.id.CompanyProfile:
                Log.d("SWITCH", "Slideshow");
                Intent intent5 = new Intent(this, Recycler_View_Company_Profile.class);
                intent5.putExtra("data", email);
                startActivity(intent5);

                break;

            case R.id.Skills:
                Log.d("SWITCH", "Slideshow");
                Intent intent6 = new Intent(this, Recycler_View_Skills.class);
                intent6.putExtra("data", email);
                startActivity(intent6);

                break;

            case R.id.Achievements:
                Log.d("SWITCH", "Slideshow");
                Intent intent7 = new Intent(this, Recycler_View_Achievements.class);
                intent7.putExtra("data", email);
                startActivity(intent7);

                break;
            case R.id.POSTJOBS:
                FragmentPostJobs fragmentPostJobs = new FragmentPostJobs(this);
                ft.replace(R.id.container, fragmentPostJobs);

                //Toast.makeText(this,"POSTJOB",Toast.LENGTH_LONG).show();
                break;
            case R.id.POSTEDJOBS:
                Intent intent1 = new Intent(this, JobsActivity.class);
                intent1.putExtra("data", email);
                startActivity(intent1);

                break;*/
            case R.id.POSTEDEVENTS:
                Intent intent2 = new Intent(this, EventsActivity.class);
                intent2.putExtra("data", email);
                startActivity(intent2);

                //Toast.makeText(this,"POSTJOB",Toast.LENGTH_LONG).show();
                break;
            case R.id.help:

            case R.id.nav_logout:

                final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
                ViewGroup viewGroup = this.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(this).inflate(R.layout.confirmdialog, viewGroup, false);
                final Button[] ok = new Button[2];
                ok[0] = (Button) dialogView.findViewById(R.id.Yes);
                ok[1] = (Button) dialogView.findViewById(R.id.No);
                builder.setView(dialogView);
                builder.setCancelable(false);
                final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
                TextView msg = dialogView.findViewById(R.id.msg);
                TextView title = dialogView.findViewById(R.id.title);
                msg.setText("Do you really want\n to logout?");
                title.setText("Logout");
                alertDialog.show();

                ok[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", "");
                        //editor.putString("password", "");
                        editor.putString("status", "");
                        editor.commit();
                        startActivity(new Intent(getApplicationContext(), PostEventActivity.class));


                    }
                });
                ok[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
        }
        toolbar.setTitle(itemTitle);

        ft.commit();


    }
    /*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displayFrags(item.getItemId(), item.getTitle().toString());
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
