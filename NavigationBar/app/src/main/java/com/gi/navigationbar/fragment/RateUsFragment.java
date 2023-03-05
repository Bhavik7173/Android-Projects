package com.gi.navigationbar.fragment;

import static android.content.Context.MODE_PRIVATE;

import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.gi.navigationbar.R;

public class RateUsFragment extends Fragment {
    String email;
    Context context;
    public RateUsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_rateus, container, false);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("user_details", MODE_PRIVATE);
        email = sharedPreferences.getString("username",null);
        Log.d("username",email+"Rate Us");
        final EditText RD = (EditText)rootView.findViewById(R.id.RD);
        Button RU = (Button) rootView.findViewById(R.id.RU);
        final RatingBar RR = (RatingBar) rootView.findViewById(R.id.RR);
        final float[] n = {0};
        final Button[] ok = new Button[1];

        RR.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                n[0] = rating;
                Log.d("rate_us", n[0] +"");
                //final String rate_entry ="'"+email.toString()+"','"+n[0]+"','"+RD.getText().toString()+"'";
                //Log.d("job",rate_entry);



            }
        });

        RU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Log.d("Rating","BUTTON PRESSED");
                final View v = view;
                Log.d("Rating",n[0]+"");
                //String rate_entry ="'"+email.toString()+"','"+n[0]+"','"+RD.getText().toString()+"'";
                //String rate_entry =email.toString()+" / "+n[0]+" / "+RD.getText().toString()+" / ";
                //Toast.makeText(context,rate_entry+"",Toast.LENGTH_SHORT).show();
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ViewGroup viewGroup = (ViewGroup) rootView.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.successdialog, viewGroup, false);
                ok[0] = (Button) dialogView.findViewById(R.id.ok);
                builder.setView(dialogView);
                builder.setCancelable(false);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                ok[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RR.setRating(0);
                        RD.setText("");
                        alertDialog.cancel();
                    }
                });

            }
        });
        return rootView;
    }
}