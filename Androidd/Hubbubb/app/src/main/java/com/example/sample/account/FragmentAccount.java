package com.example.sample.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
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
import com.example.sample.ContactUsResponse;
import com.example.sample.EventsActivity;
import com.example.sample.FeedbackResponse;
import com.example.sample.JobsActivity;
import com.example.sample.MainActivity;
import com.example.sample.MyRetrofit;
import com.example.sample.R;
import com.example.sample.RateUsResponse;
import com.example.sample.Userinfo;
import com.example.sample.account.aboutMe.Recycler_View_Achievements;
import com.example.sample.account.aboutMe.Recycler_View_Company_Profile;
import com.example.sample.account.aboutMe.Recycler_View_Skills;
import com.example.sample.account.accountSecurity.ChangePasswordActivity;
import com.example.sample.account.accountSecurity.PersonalProfileActivity;
import com.example.sample.account.accountSecurity.ProfessionalProfileActivity;
import com.example.sample.account.accountSecurity.StudentProfileActivity;
import com.example.sample.account.myActivities.PostEventsActivity;
import com.example.sample.account.myActivities.PostJobsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class FragmentAccount extends Fragment {

    Context context;
    Intent intent;
    View view;
    Api api;
    String email,status;
    SharedPreferences sharedPreferences;
    ImageView Image;
    RatingBar RR;
    EditText RD,YF,sub;
    ProgressBar progressBar;
    final float[] n = {0};
    TextView imageView,name,acc_email;
    TextView PersonalProfile,ProfessionalProfile,StudentProfile,ChangeP,PostEvents,PostJobs,PostedEvents,PostedJobs,
            LikedEvents,DislikedEvents,InterestedEvents,AppliedJobs,CompanyProfile,Skills,Achievements,
    ContactUs,Feedback,ShareUs,RateUs,SignOut;
    TextView acc_title,my_activities,about_me,support;
    String time="";

    public FragmentAccount(){}
    public FragmentAccount(Context context)
    {
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_account,null);


        Image=view.findViewById(R.id.Image);
        progressBar=view.findViewById(R.id.imageloading);
        imageView=view.findViewById(R.id.imageView);
        acc_email=view.findViewById(R.id.email);
        name=view.findViewById(R.id.name);

        PersonalProfile=view.findViewById(R.id.personal_profile);
        ProfessionalProfile=view.findViewById(R.id.professional_profile);
        StudentProfile=view.findViewById(R.id.student_profile);
        ChangeP=view.findViewById(R.id.changeP);
        PostEvents=view.findViewById(R.id.post_events);
        PostJobs=view.findViewById(R.id.post_jobs);
        PostedEvents=view.findViewById(R.id.posted_events);
        PostedJobs=view.findViewById(R.id.posted_jobs);
        LikedEvents=view.findViewById(R.id.liked_events);
        DislikedEvents=view.findViewById(R.id.disliked_events);
        InterestedEvents=view.findViewById(R.id.interested_events);
        AppliedJobs=view.findViewById(R.id.applied_jobs);
        CompanyProfile=view.findViewById(R.id.company_profile);
        Skills=view.findViewById(R.id.skills);
        Achievements=view.findViewById(R.id.achievements);
        ContactUs=view.findViewById(R.id.contact_us);
        Feedback=view.findViewById(R.id.feedback);
        ShareUs=view.findViewById(R.id.share);
        RateUs=view.findViewById(R.id.rate_us);
        SignOut=view.findViewById(R.id.sign_out);

        acc_title=view.findViewById(R.id.acc_title);
        my_activities=view.findViewById(R.id.my_activities);
        about_me=view.findViewById(R.id.about_me);
        support=view.findViewById(R.id.support);


        imageView.setVisibility(View.GONE);

        sharedPreferences = getContext().getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        String id = sharedPreferences.getString("id",null);
        status = sharedPreferences.getString("status",null);
/*        if(status.equals("Alumni"))
        {
            StudentProfile.setVisibility(View.GONE);
        }
        else if(status.equals("Student"))
        {
            ProfessionalProfile.setVisibility(View.GONE);
            CompanyProfile.setVisibility(View.GONE);
            PostEvents.setVisibility(View.GONE);
            PostJobs.setVisibility(View.GONE);
            PostedEvents.setVisibility(View.GONE);
            PostedJobs.setVisibility(View.GONE);
        }
        if(status.equals("Faculty"))
        {
            StudentProfile.setVisibility(View.GONE);
            CompanyProfile.setVisibility(View.GONE);
        }*/
        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<Userinfo> call;
        api=instance.create(Api.class);

        call=api.userinfo(email);
        call.enqueue(new Callback<Userinfo>() {
            @Override
            public void onResponse(Call<Userinfo> call, Response<Userinfo> response) {
               // Userinfo userinfo=response.body();
               /* set(userinfo.getPE(),userinfo.getPN(),userinfo.getPI(),status);
                Log.d("Whyred",userinfo.getPE()+userinfo.getPN()+userinfo.getPI());*/
            }

            @Override
            public void onFailure(Call<Userinfo> call, Throwable t) {
                Log.d("Whyred", t.toString());
            }
        });

        PersonalProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, PersonalProfileActivity.class);
                startActivity(intent);
            }
        });
        ProfessionalProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, ProfessionalProfileActivity.class);
                startActivity(intent);
            }
        });
        StudentProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, StudentProfileActivity.class);
                startActivity(intent);
            }
        });
        ChangeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        PostEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, PostEventsActivity.class);
                startActivity(intent);
            }
        });
        PostJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, PostJobsActivity.class);
                startActivity(intent);
            }
        });
        PostedEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, EventsActivity.class);
                intent.putExtra("data", email);
                startActivity(intent);
            }
        });
        PostedJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, JobsActivity.class);
                intent.putExtra("data", email);
                startActivity(intent);
            }
        });
        LikedEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent=new Intent(context, EventsActivity.class);
                intent.putExtra("data","LIKED");
                startActivity(intent);
            }
        });
        DislikedEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent=new Intent(context, EventsActivity.class);
                intent.putExtra("data","DISLIKED");
                startActivity(intent);
            }
        });
        InterestedEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent=new Intent(context, EventsActivity.class);
                intent.putExtra("data","INTERESTED");
                startActivity(intent);
            }
        });
        AppliedJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, JobsActivity.class);
                intent.putExtra("data", "APPLIED");
                startActivity(intent);
            }
        });
        CompanyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, Recycler_View_Company_Profile.class);
                intent.putExtra("data", email);
                startActivity(intent);
            }
        });

        Skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, Recycler_View_Skills.class);
                intent.putExtra("data", email);
                startActivity(intent);
            }
        });

        Achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, Recycler_View_Achievements.class);
                intent.putExtra("data", email);
                startActivity(intent);
            }
        });

        ContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contactus();
            }
        });

        RateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rateus();
            }
        });

        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                feedback();
            }
        });

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Whyred","log out");
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.confirmdialog, viewGroup, false);
                final Button[] ok = new Button[2];
                ok[0] = (Button) dialogView.findViewById(R.id.Yes);
                ok[1] = (Button) dialogView.findViewById(R.id.No);
                builder.setView(dialogView);
                builder.setCancelable(false);
                final AlertDialog alertDialog = builder.create();
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
                        editor.putString("status", "");
                        editor.commit();
                        startActivity(new Intent(context, MainActivity.class));


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

    private void contactus() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
        final Spinner spinner = (Spinner)(dialogView).findViewById(R.id.time_spinner);

        email1.setText(email);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.time_spinner));
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
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();


        ok[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnt[0]=0;

                time = spinner.getSelectedItem().toString();
                if(name.getText().toString().trim().isEmpty())
                {
                    cnt[0]++;
                    name.setError("Fill your name!");
                }
                if(CN.getText().toString().trim().isEmpty())
                {
                }
                else
                {
                    if(!test(CN)) {
                        cnt[0]++;
                    }
                }
                if(time.isEmpty() || time.equals("Select Time"))
                {
                    cnt[0]++;
                    error_time.setVisibility(View.VISIBLE);
                }
                if(problem.getText().toString().trim().isEmpty())
                {
                    cnt[0]++;
                    problem.setError("Fill your problem!");
                }
                if (cnt[0] == 0) {

                    final String contact_us = "'" + email1.getText().toString().trim() + "','" + name.getText().toString().trim() + "','" + CN.getText().toString() + "','" + time + "','" + problem.getText().toString() + "'";

                    String name1=name.getText().toString().trim();
                    String CN1 = CN.getText().toString().trim();
                    String problem1 = problem.getText().toString();
                    String id = sharedPreferences.getString("id",null);
                    Log.d("mylogf",contact_us);
                    Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));
                    Call<ContactUsResponse> call;
                    api=instance.create(Api.class);
                    call = api.contact_us(email,name1,CN1,time,problem1,id);
                    call.enqueue(new Callback<ContactUsResponse>() {
                        @Override
                        public void onResponse(Call<ContactUsResponse> call, Response<ContactUsResponse> response) {
                            alertDialog.cancel();
                            msg();
                        }

                        @Override
                        public void onFailure(Call<ContactUsResponse> call, Throwable t) {
                            Log.d("mylogf",t.toString());
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

    private void msg() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
        final Button[] ok = new Button[1];
        View dialogView = LayoutInflater.from(context).inflate(R.layout.successdialog, viewGroup, false);
        ok[0] = (Button) dialogView.findViewById(R.id.ok);
        builder.setView(dialogView);
        builder.setCancelable(false);
        final TextView msg1 = (dialogView).findViewById(R.id.msg);
        msg1.setText("Your problem has been successfully submitted.\nWe'll contact you soon.");
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        ok[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
    }
    private void feedback() {
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

        YF = (EditText)dialogView.findViewById(R.id.YF);
        sub = (EditText)dialogView.findViewById(R.id.sub);

        ok[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String id= sharedPreferences.getString("id",null);
                final String feedback ="'"+id+"','"+sub.getText().toString()+"','"+YF.getText().toString()+"'";

                Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));
                Call<FeedbackResponse> call;
                api=instance.create(Api.class);
                call = api.feedback(id.toString(),sub.getText().toString(),YF.getText().toString());
                Log.d("Feedback",feedback);

                call.enqueue(new Callback<FeedbackResponse>() {
                    @Override
                    public void onResponse(Call<FeedbackResponse> call, Response<FeedbackResponse> response) {
                        FeedbackResponse feedbackResponse = response.body();
                        String status=feedbackResponse.getStatus();
                        Log.d("responseP",status);


                        if(status.equals("Success")) {
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

                    @Override
                    public void onFailure(Call<FeedbackResponse> call, Throwable t) {
                        Log.d("responseP","error : "+t.toString());
                    }
                });
            }
        });
        ok[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
    }

    private boolean rateus() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_rateus, viewGroup, false);
        final Button[] ok = new Button[2];
        ok[0] = (Button) dialogView.findViewById(R.id.RU);
        ok[1] = (Button) dialogView.findViewById(R.id.cancel);
        builder.setView(dialogView);
        builder.setCancelable(false);
        final AlertDialog alertDialog = builder.create();
        RD = dialogView.findViewById(R.id.rd);
        RR = dialogView.findViewById(R.id.RR);
        alertDialog.show();
    String id = sharedPreferences.getString("id",null);

        RR.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                n[0] = rating;
                if (n[0] == 0) {
                    ok[0].setEnabled(false);
                    ok[0].setAlpha((float) 0.5);
                } else {
                    ok[0].setAlpha(1);
                    ok[0].setEnabled(true);
                }

            }
        });
        RD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RD.setHint("It's really helpful.");
                RD.setHintTextColor(Color.GRAY);
            }
        });
        if (n[0] == 0) {
            ok[0].setEnabled(false);
            ok[0].setAlpha((float) 0.5);
        }
        ok[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Log.d("Rating",n[0]+"");
                final String rate_entry ="'"+email.toString()+"','"+n[0]+"','"+RD.getText().toString()+"','"+id+"'";
                Log.d("Rating1",rate_entry);

                Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));
                Call<RateUsResponse> call;
                api=instance.create(Api.class);
                call = api.rateus(email.toString(),n[0],RD.toString(),id);
                Log.d("Rating",rate_entry);
                call.enqueue(new Callback<RateUsResponse>() {
                    @Override
                    public void onResponse(Call<RateUsResponse> call, Response<RateUsResponse> response) {
                        Log.d("mylog", response.body().getStatus());

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(getString(R.string.rate_us), n[0] + "");
                        alertDialog.cancel();

                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                        final Button[] ok = new Button[1];
                        View dialogView = LayoutInflater.from(context).inflate(R.layout.successdialog, viewGroup, false);
                        ok[0] = (Button) dialogView.findViewById(R.id.ok);
                        builder.setView(dialogView);
                        builder.setCancelable(false);
                        final TextView msg1 = (dialogView).findViewById(R.id.msg);
                        final TextView header = (dialogView).findViewById(R.id.header);
                        msg1.setText("Your rating has been successfully submitted.");
                        header.setText("Success");
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        ok[0].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.cancel();

                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<RateUsResponse> call, Throwable t) {
                        Log.d("mylog",t.toString());
                    }
                });

            }
        });
        ok[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();

            }
        });
        return true;
    }

    private void set(String pe, String pn, String pi, String status) {
        Log.d("Whyred",pe+" "+pn+" "+pi);
        name.setText(pn);
        acc_email.setText("\t\t"+pe);
        if(!pi.isEmpty() || pi!=null) {

            Glide.with(context).load(getString(R.string.IP1) + pi).error(R.drawable.ic_person_black_24dp).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(this.Image);
            imageView.setVisibility(View.GONE);
        }
        else
        {
            imageView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            imageView.setText(pn.charAt(0)+"");
        }
    }
    public boolean test(EditText edt) {
        String pattern = "^((\\+){0,1}91(\\s){0,1}(\\-){0,1}(\\s){0,1}){0,1}[6-9]{1}[0-9](\\s){0,1}(\\-){0,1}(\\s){0,1}[1-9]{1}[0-9]{7}$";
        if((edt.getText().toString().matches(pattern)))
        {
            Toast.makeText(context,"Accepted", Toast.LENGTH_LONG).show();
            return  true;
        }
        else {
            Toast.makeText(context, "declined", Toast.LENGTH_LONG).show();
            edt.setError("Invalid number!");
            return false;
        }
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
