package com.example.sample.Frags;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sample.R;

import java.util.ArrayList;
import java.util.List;

public class RCAdapter_J extends RecyclerView.Adapter<RCAdapter_J.MyHolder> {

    ArrayList<AllJobData> arrayList;
    List<String> JID;
    List<String> JT;
    List<String> JD;
    List<String> JL;
    List<String> JI;
    List<String> JRE;
    List<String> JF;
    List<String> JS;
    List<String> JSD;
    List<String> JED;
    List<String> JTA;
    List<String> apply;
    Context context;
    String data;
    Context context1;


    public RCAdapter_J(List<String> jid, List<String> jt, List<String> jre, List<String> jf, List<String> js, List<String> jsd, List<String> jed, List<String> ji, List<String> jd, List<String> jl, List<String> jta, List<String> apply, String data, Context context)
    {
        this.JD = jd;
        this.JID = jid;
        this.JI = ji;
        this.JT = jt;
        this.JRE = jre;
        this.JF = jf;
        this.JSD = jsd;
        this.JED = jed;
        this.JS = js;
        this.JL = jl;
        this.JTA = jta;
        this.apply = apply;
        this.data = data;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Log.d("Whyred","onCreateView");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rvcl_postedjobs,null);
        MyHolder myHolder = new MyHolder(view);
        context=view.getContext();
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {

        Log.d("Whyred","Bind");
        holder.JID = JID.get(position);
        holder.JT.setText(JT.get(position));
        holder.JL.setText(JL.get(position));
        holder.JTA.setText(JTA.get(position));
        holder.JSD.setText(JSD.get(position));
        holder.JED=JED.get(position);
        holder.JS=JS.get(position);
        holder.JRE=JRE.get(position);
        holder.JF=JF.get(position);
        holder.JD=JD.get(position);
        holder.JF=JF.get(position);
        holder.apply=apply.get(position);
        Glide.with(holder.Img.getContext()).load(context.getString(R.string.IP)+JI.get(position)).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(holder.Img);
        holder.JMD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), JobDetails.class);
                intent.putExtra("JF",holder.JF);
                intent.putExtra("JID",JID.get(position));
                intent.putExtra("JT",JT.get(position));
                intent.putExtra("JL",JL.get(position));
                intent.putExtra("JSD",JSD.get(position));
                intent.putExtra("JED",JED.get(position));
                intent.putExtra("JRE",JRE.get(position));
                intent.putExtra("JI",JI.get(position));
                intent.putExtra("JS",JS.get(position));
                intent.putExtra("JD",JD.get(position));
                intent.putExtra("apply",apply.get(position));
                intent.putExtra("data",data);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return JT.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView JT;
        EditText JL,JSD,JTA;
        ImageView Img;
        String JED,JS,JRE,JF,JD,JID,apply;
        Button JMD;

        public MyHolder(@NonNull View itemView) {

            super(itemView);
            //Log.d("Whyred","MyHolder");
            JT = itemView.findViewById(R.id.JT);
            JL = itemView.findViewById(R.id.location);
            JSD = itemView.findViewById(R.id.JSD);
            JTA = itemView.findViewById(R.id.JTA);
            JMD=itemView.findViewById(R.id.JMD);
            Img=itemView.findViewById(R.id.Img);


        }
    }
}
