package com.gi.millioners.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gi.millioners.R;
import com.gi.millioners.model.QuestionPojo;
import com.gi.millioners.staticfunction.AppSetting;


import java.util.ArrayList;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryHolder> {

    Context context;
    int start;
    ArrayList<QuestionPojo> quizModelArrayList;

    public SummaryAdapter(Context context, int start, ArrayList<QuestionPojo> quizModelArrayList) {
        this.context = context;
        this.start = start;
        this.quizModelArrayList = quizModelArrayList;
    }

    @Override
    public SummaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_design, parent, false);
//        SummaryHolder holder = new SummaryHolder(view);
//        return holder;
        return null;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(SummaryHolder holder, int position) {

        int newpos = start + position;
        if (newpos < quizModelArrayList.size()) {
            holder.question.setText(newpos + 1 + "." + quizModelArrayList.get(position).getQ_question());
            holder.a.setText(quizModelArrayList.get(newpos).getOptionA());
            holder.b.setText(quizModelArrayList.get(newpos).getOptionB());
            holder.c.setText(quizModelArrayList.get(newpos).getOptionC());
            holder.d.setText(quizModelArrayList.get(newpos).getOptionD());

            holder.textA.setVisibility(View.VISIBLE);
            holder.a.setVisibility(View.VISIBLE);
            holder.textB.setVisibility(View.VISIBLE);
            holder.b.setVisibility(View.VISIBLE);
            holder.textC.setVisibility(View.VISIBLE);
            holder.c.setVisibility(View.VISIBLE);
            holder.textD.setVisibility(View.VISIBLE);
            holder.d.setVisibility(View.VISIBLE);

            holder.a.setEnabled(false);
            holder.b.setEnabled(false);
            holder.c.setEnabled(false);
            holder.d.setEnabled(false);

            holder.a.setChecked(false);
            holder.b.setChecked(false);
            holder.c.setChecked(false);
            holder.d.setChecked(false);

            if (quizModelArrayList.get(position).getUser_answer().equals(quizModelArrayList.get(position).getCorrect_answer())) {
                setResult(quizModelArrayList.get(position).getCorrect_answer(), context.getString(R.color.green), holder);
            } else {
                setResult(quizModelArrayList.get(position).getCorrect_answer(), context.getString(R.color.green), holder);
                setResult(quizModelArrayList.get(position).getUser_answer(), context.getString(R.color.red), holder);
            }
        }
    }

    public void setResult(String option, String color, SummaryHolder holder) {
        switch (option) {
            case "A":
                holder.a.setChecked(true);
                holder.a.setTextColor(Color.parseColor(color));
                break;
            case "B":
                holder.b.setChecked(true);
                holder.b.setTextColor(Color.parseColor(color));
                break;
            case "C":
                holder.c.setChecked(true);
                holder.c.setTextColor(Color.parseColor(color));
                break;
            case "D":
                holder.d.setChecked(true);
                holder.d.setTextColor(Color.parseColor(color));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return AppSetting.numbers;
    }

    public class SummaryHolder extends RecyclerView.ViewHolder {
        TextView question, textA, textB, textC, textD;
        RadioButton a, b, c, d;

        public SummaryHolder(View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question);
            a = itemView.findViewById(R.id.a);
            b = itemView.findViewById(R.id.b);
            c = itemView.findViewById(R.id.c);
            d = itemView.findViewById(R.id.d);
//            textA = itemView.findViewById(R.id.textA);
//            textB = itemView.findViewById(R.id.textB);
//            textC = itemView.findViewById(R.id.textC);
//            textD = itemView.findViewById(R.id.textD);
        }
    }
}