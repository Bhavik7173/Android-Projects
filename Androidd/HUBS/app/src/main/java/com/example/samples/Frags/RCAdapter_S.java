package com.example.samples.Frags;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samples.R;

import java.util.ArrayList;
import java.util.List;

public class RCAdapter_S extends RecyclerView.Adapter<RCAdapter_S.MyHolder> {

    ArrayList<AllSkillData> arrayList;
    List<String> Skill;
    List<String> Time;
    Context context;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("Whyred","onCreateView");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rvcl_skill,null);
        context = view.getContext();
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        Log.d("Whyred","Bind");
        holder.Skill.setText(Skill.get(position));
        holder.ms.setText("Skill "+(position+1)+"");
        Log.d("Whyred1",Skill.get(position));
        holder.Time.setText(Time.get(position));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),SkillDetails.class);
                intent.putExtra("Skill",holder.Skill.getText()+"");
                intent.putExtra("Time",holder.Time.getText()+"");
                intent.putExtra("func","Update");
                Log.d("Whyred1","after intent");
                context.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),SkillDetails.class);
                intent.putExtra("Skill",holder.Skill.getText()+"");
                intent.putExtra("Time",holder.Time.getText()+"");
                intent.putExtra("func","Delete");
                context.startActivity(intent);
            }
        });
    }

    public RCAdapter_S(List<String> Skill, List<String> Time, Context context) {
        Log.d("Whyred","Constructor");
        Log.d("Whyred",Skill.toString());

        this.Skill = Skill;
        this.Time = Time;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return Skill.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        EditText Skill,Time;
        TextView ms;
        Button edit,delete;
        public MyHolder(@NonNull View itemView) {

            super(itemView);
            Log.d("Whyred","MyHolder");
            Skill = itemView.findViewById(R.id.Skill);
            ms = itemView.findViewById(R.id.ms);
            Time = itemView.findViewById(R.id.Time);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
