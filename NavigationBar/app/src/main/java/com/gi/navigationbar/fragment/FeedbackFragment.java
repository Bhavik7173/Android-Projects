package com.gi.navigationbar.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.gi.navigationbar.R;

public class FeedbackFragment extends Fragment {
    Context context;
    EditText RD, YF, sub;
    String email;
    public FeedbackFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(android.R.id.content);
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
        return rootView;
    }
}
