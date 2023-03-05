package com.gi.ginquiry.admin.fragment;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.gi.ginquiry.MainActivity;
import com.gi.ginquiry.R;
import com.gi.ginquiry.admin.AdminHome;
import com.gi.ginquiry.admin.addNew.AddNewCourse;
import com.gi.ginquiry.admin.addNew.AddNewFaculty;
import com.gi.ginquiry.admin.addNew.AddNewInquiry;
import com.gi.ginquiry.admin.recycleView.Recycler_View_Achievements;
import com.gi.ginquiry.admin.recycleView.Recycler_View_Company_Profile;
import com.gi.ginquiry.admin.recycleView.Recycler_View_Skills;
import com.gi.ginquiry.admin.settings.ChangePasswordActivity;
import com.gi.ginquiry.admin.settings.PersonalProfileActivity;
import com.gi.ginquiry.admin.settings.RateUsActivity;
import com.gi.ginquiry.register.Login;


public class SettingFragment extends Fragment {
    Context context;
    TextView changePassword, PersonalProfile, contactus, rateus, shareus, logout, feedback, companyprofile, skills, achievement, addinquiry, addcourse, addfaculty, addfees, addoffer, applybatch;
    String email;
    String time = "";
    EditText RD, YF, sub;
    TextView name,email1;
    String uname,uemail;
    public SettingFragment(Context context) {
        this.context = context;
    }

    public SettingFragment(Context context, String name,String email) {
        this.context = context;
        uname = name;
        uemail = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        name = view.findViewById(R.id.name);
        name.setText("Welcome to " + uname);
        email1 = view.findViewById(R.id.email);
        email1.setText(uemail);

        changePassword = view.findViewById(R.id.changeP);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ChangePasswordActivity.class);
                startActivity(i);
            }
        });
        contactus = view.findViewById(R.id.contact_us);
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
                ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_problem_signup, viewGroup, false);
                final Button[] ok = new Button[2];
                ok[0] = (Button) dialogView.findViewById(R.id.submit);
                ok[1] = (Button) dialogView.findViewById(R.id.cancel);
                builder.setView(dialogView);
                builder.setCancelable(false);
                final int[] cnt = {0};
                final EditText email1 = (dialogView).findViewById(R.id.email);
                final EditText name = (dialogView).findViewById(R.id.name);
                final EditText CN = (dialogView).findViewById(R.id.CN);
                final EditText problem = (dialogView).findViewById(R.id.problem);
                final TextView error_time = (dialogView).findViewById(R.id.error_time);
                final Spinner spinner = (Spinner) (dialogView).findViewById(R.id.time_spinner);

                email1.setText(email);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                        android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.time_spinner));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        error_time.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
                alertDialog.show();


                ok[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cnt[0] = 0;

                        time = spinner.getSelectedItem().toString();
                        if (name.getText().toString().trim().isEmpty()) {
                            cnt[0]++;
                            name.setError("Fill your name!");
                        }
                        if (CN.getText().toString().trim().isEmpty()) {
                        } else {
                            if (!test(CN)) {
                                cnt[0]++;
                            }
                        }
                        if (time.isEmpty() || time.equals("Select Time")) {
                            cnt[0]++;
                            error_time.setVisibility(View.VISIBLE);
                        }
                        if (problem.getText().toString().trim().isEmpty()) {
                            cnt[0]++;
                            problem.setError("Fill your problem!");
                        }
                        if (cnt[0] == 0) {

                        }

                    }
                });

                ok[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
            }
        });
        rateus = view.findViewById(R.id.rate_us);
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, RateUsActivity.class);
                startActivity(i);
            }
        });
        shareus = view.findViewById(R.id.share);
        shareus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Govardhan Institute");
                String app_url = " https://play.google.com/store/apps/details?id=com.GI.govardhaninstitute";
                shareIntent.putExtra(Intent.EXTRA_TEXT, app_url);
                startActivity(Intent.createChooser(shareIntent, "Share via"));

            }

        });
        skills = view.findViewById(R.id.skills);
        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Recycler_View_Skills.class);
                startActivity(i);
            }
        });
        achievement = view.findViewById(R.id.achievements);
        achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Recycler_View_Achievements.class);
                startActivity(i);
            }
        });
        addinquiry = view.findViewById(R.id.add_inquiry);
        addinquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddNewInquiry.class);
                startActivity(i);
            }
        });
        addcourse = view.findViewById(R.id.add_course);
        addcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddNewCourse.class);
                startActivity(i);
            }
        });
        addfaculty = view.findViewById(R.id.add_faculty);
        addfaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddNewFaculty.class);
                startActivity(i);
            }
        });
        PersonalProfile = view.findViewById(R.id.personal_profile);
        PersonalProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, PersonalProfileActivity.class);
                startActivity(intent);
            }
        });
        feedback = view.findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_feedback, viewGroup, false);
                final Button[] ok = new Button[2];
                ok[0] = (Button) dialogView.findViewById(R.id.SF);
                ok[1] = (Button) dialogView.findViewById(R.id.cancel);
                builder.setView(dialogView);
                builder.setCancelable(false);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                YF = (EditText) dialogView.findViewById(R.id.YF);
                sub = (EditText) dialogView.findViewById(R.id.sub);

                ok[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        final String feedback = "'" + email + "','" + sub.getText().toString() + "','" + YF.getText().toString() + "'";
                        String status = "Success";
                        if (status.equals("Success")) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                            View dialogView = LayoutInflater.from(context).inflate(R.layout.successdialog, viewGroup, false);
                            ok[0] = (Button) dialogView.findViewById(R.id.ok);
                            TextView msg = (TextView) dialogView.findViewById(R.id.msg);
                            msg.setText("Your feedback has been \nsuccessfully submitted!");
                            builder.setView(dialogView);
                            builder.setCancelable(false);
                            final AlertDialog alertDialog1 = builder.create();
                            alertDialog1.show();

                            ok[0].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    sub.setText("");
                                    YF.setText("");
                                    alertDialog1.cancel();
                                    alertDialog.cancel();
                                }
                            });
                        }
                    }
                });

                ok[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
            }
        });
        companyprofile = view.findViewById(R.id.company_profile);
        companyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Recycler_View_Company_Profile.class);
                startActivity(i);
            }
        });
        logout = view.findViewById(R.id.sign_out);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ViewGroup viewGroup = container.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.confirmdialog, viewGroup, false);

                final Button[] ok = new Button[2];
                ok[0] = (Button) dialogView.findViewById(R.id.Yes);
                ok[1] = (Button) dialogView.findViewById(R.id.No);
                builder.setView(dialogView);
                builder.setCancelable(false);
                final AlertDialog alertDialog = builder.create();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(alertDialog.getWindow().getAttributes());
                layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                alertDialog.getWindow().setAttributes(layoutParams);
                TextView msg = dialogView.findViewById(R.id.msg);
                TextView title = dialogView.findViewById(R.id.title);
                msg.setText("Do you really want\n to logout?");
                title.setText("Logout");
                alertDialog.show();
                ok[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                });
                ok[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
            }
        });
        return view;
    }

    public boolean test(EditText edt) {
        String pattern = "^((\\+){0,1}91(\\s){0,1}(\\-){0,1}(\\s){0,1}){0,1}[6-9]{1}[0-9](\\s){0,1}(\\-){0,1}(\\s){0,1}[1-9]{1}[0-9]{7}$";
        if ((edt.getText().toString().matches(pattern))) {
            Toast.makeText(context, "Accepted", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(context, "declined", Toast.LENGTH_LONG).show();
            edt.setError("Invalid number!");
            return false;
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        //getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
//
//        // first parameter is the file for icon and second one is menu
//        return super.onCreateOptionsMenu(menu);
//    }

}