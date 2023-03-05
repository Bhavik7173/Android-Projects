package com.example.sample.Frags;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.sample.Api;
import com.example.sample.InterestResponse;
import com.example.sample.LikeResponse;
import com.example.sample.MyRetrofit;
import com.example.sample.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RCAdapter_E extends RecyclerView.Adapter<RCAdapter_E.MyHolder> {

    ArrayList<AllEventData> arrayList;
    List<String> EN;
    List<String> EID;
    List<String> ED;
    List<String> EF;
    List<String> ESD;
    List<String> EED;
    List<String> EET;
    List<String> EST;
    List<String> EL;
    List<String> EI;
    List<String> like;
    List<String> dislike;
    List<String> interest;
    List<String> ldl;
    List<String> INI;
    Context context;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("Whyred","onCreateView");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rvcl_postedevents,null);
        context = view.getContext();
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {

        Log.d("Whyred","Bind");
        holder.EN.setText(EN.get(position));
        final String ImagePath=EI.get(position);
        Log.d("Whyred1",ImagePath);
        Glide.with(holder.Img.getContext()).load(context.getString(R.string.IP1)+EI.get(position)).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(holder.Img);
        holder.ESD.setText(ESD.get(position));
        holder.EL.setText(EL.get(position));
        holder.like.setText(like.get(position));
        holder.dislike.setText(dislike.get(position));
        holder.interest.setText(interest.get(position));
        holder.ED = ED.get(position);
        holder.EID = EID.get(position);
        holder.EED = EED.get(position);
        holder.EST = EST.get(position);
        holder.EET = EET.get(position);
        holder.ldl = ldl.get(position);
        holder.ini = INI.get(position);
        holder.EF=EF.get(position);


        if(holder.ldl.equals("like"))
        {
            holder.IL.setAlpha((float) 1.0);
        }
        else if(holder.ldl.equals("dislike"))
        {
            holder.IDL.setAlpha((float) 1.0);
        }
        else if(holder.ldl.equals("none"))
        {
            holder.IL.setAlpha((float) 0.5);
            holder.IDL.setAlpha((float) 0.5);
        }

        if(holder.ini.equals("I"))
        {
            holder.INI.setAlpha((float) 1.0);
        }
        else if(holder.ini.equals("N"))
        {
            holder.INI.setAlpha((float) 0.5);
        }

        holder.INI.setOnClickListener(new View.OnClickListener() {
            int cnt = 0;
            SharedPreferences sharedPreferences = context.getSharedPreferences("EMAIL", context.MODE_PRIVATE);
            String email = sharedPreferences.getString("email", null);
            @Override
            public void onClick(View view) {
                if(holder.INI.getAlpha()==0.5) {
                    holder.INI.setAlpha((float) 1.0);
                    cnt=1;
                }
                else
                {
                    holder.INI.setAlpha((float) 0.5);
                    cnt=0;
                }
                Retrofit r = MyRetrofit.getRetrofit(context.getString(R.string.IP1));
                Call<InterestResponse> call;
                Api api = r.create(Api.class);
                Log.d("Whyred",cnt+ " "+ Integer.parseInt(holder.EID)+ " "+email+" "+"Interest");
                call = api.interest(cnt, Integer.parseInt(holder.EID), email, "Interest");

                call.enqueue(new Callback<InterestResponse>() {
                    @Override
                    public void onResponse(Call<InterestResponse> call, Response<InterestResponse> response) {
                        final InterestResponse interestResponse = response.body();
                        Log.d("Whyred",interestResponse.getStatus());
                        holder.interest.setText(interestResponse.getStatus());
                    }

                    @Override
                    public void onFailure(Call<InterestResponse> call, Throwable t) {
                        Log.d("Whyred",t.toString());
                    }
                });

            }
        });
        Log.d("Whyred1","binded data"+" "+ED);
        holder.EMD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Whyred22",v.getContext().toString());

                Intent intent = new Intent(v.getContext(),EventDetails.class);
                intent.putExtra("EID",EID.get(position));
                intent.putExtra("EN",holder.EN.getText());
                intent.putExtra("EL",holder.EL.getText()+"");
                Log.d("Whyred1",holder.EL.getText()+"");
                intent.putExtra("EF",holder.EF);
                intent.putExtra("EI",ImagePath);
                Log.d("Whyred1",holder.EI+"");
                intent.putExtra("ED",holder.ED);
                intent.putExtra("ESD",holder.ESD.getText()+"");
                intent.putExtra("EST",holder.EST);
                intent.putExtra("EED",holder.EED);
                intent.putExtra("EET",holder.EET);
                intent.putExtra("EID",holder.EID);
                intent.putExtra("like",holder.like.getText().toString());
                intent.putExtra("dislike",holder.dislike.getText().toString());
                intent.putExtra("ldl",holder.ldl);
                Log.d("Whyred1","after intent");
                context.startActivity(intent);

            }
        });
        holder.IDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cntl=0,cntdl=0;
                SharedPreferences sharedPreferences = context.getSharedPreferences("EMAIL", context.MODE_PRIVATE);
                String email = sharedPreferences.getString("email", null);
                final String[] like = {""};
                final String[] dislike = {""};

                if(holder.IDL.getAlpha()==0.5) {
                   holder.IDL.setAlpha((float) 1.0);
                   cntdl=1;
                }
                else
                {
                    cntdl=0;
                    holder.IDL.setAlpha((float) 0.5);
                }
                if(holder.IL.getAlpha()==1.0) {
                    cntl=0;
                    holder.IL.setAlpha((float) 0.5);
                }
                Retrofit r = MyRetrofit.getRetrofit(context.getString(R.string.IP1));
                Call<LikeResponse> call;
                Api api = r.create(Api.class);
                Log.d("Whyred",cntl+ " "+cntdl+ " "+ Integer.parseInt(holder.EID)+ " "+email+" "+"Like");
                call = api.like(cntl, cntdl, Integer.parseInt(holder.EID), email, "Like");

                call.enqueue(new Callback<LikeResponse>() {
                    @Override
                    public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                        final LikeResponse likeResponse = response.body();
                        Log.d("responsePDL", likeResponse.getStatus());
                        Log.d("responsePDL", likeResponse.getStatus().toString().substring(0,likeResponse.getStatus().toString().indexOf(" ")));
                        Log.d("responsePDL", likeResponse.getStatus().toString().substring(likeResponse.getStatus().toString().indexOf(" ")+1));
                        Log.d("responsePDL", like[0]);
                        Log.d("responsePDL", dislike[0]);
                        like[0] = likeResponse.getStatus().toString().substring(0,likeResponse.getStatus().toString().indexOf(" "));
                        dislike[0] = likeResponse.getStatus().toString().substring(likeResponse.getStatus().toString().indexOf(" ")+1);
                        Log.d("responsePDLa", like[0]);
                        Log.d("responsePDLa", dislike[0]);
                        holder.like.setText(like[0]);
                        holder.dislike.setText(dislike[0]);
                    }

                    @Override
                    public void onFailure(Call<LikeResponse> call, Throwable t) {
                        Log.d("responseP", "error : " + t.toString());
                    }
                });
            }
        });
        holder.IL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int cntl=0,cntdl=0;
                SharedPreferences sharedPreferences = context.getSharedPreferences("EMAIL", context.MODE_PRIVATE);
                String email = sharedPreferences.getString("email", null);
                final String[] like = {""};
                final String[] dislike = {""};
                if(holder.IL.getAlpha()==0.5) {
                    cntl=1;
                    holder.IL.setAlpha((float) 1.0);
                }
                else
                {
                    cntl=0;
                    holder.IL.setAlpha((float) 0.5);
                }
                if(holder.IDL.getAlpha()==1.0) {
                    cntdl=0;
                    holder.IDL.setAlpha((float) 0.5);
                }
                Retrofit r = MyRetrofit.getRetrofit(context.getString(R.string.IP1));
                Call<LikeResponse> call;
                Api api = r.create(Api.class);
                Log.d("Whyred",cntl+ " "+cntdl+ " "+ Integer.parseInt(holder.EID)+ " "+email+" "+"Like");
                call = api.like(cntl, cntdl, Integer.parseInt(holder.EID), email, "Like");

                call.enqueue(new Callback<LikeResponse>() {
                    @Override
                    public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                        final LikeResponse likeResponse = response.body();
                        Log.d("responsePL", likeResponse.getStatus());
                        Log.d("responsePL", likeResponse.getStatus().toString().substring(0,likeResponse.getStatus().toString().indexOf(" ")));
                        Log.d("responsePL", likeResponse.getStatus().toString().substring(likeResponse.getStatus().toString().indexOf(" ")+1));
                        Log.d("responsePL", like[0]);
                        Log.d("responsePL", dislike[0]);
                        like[0] = likeResponse.getStatus().toString().substring(0,likeResponse.getStatus().toString().indexOf(" "));
                        dislike[0] = likeResponse.getStatus().toString().substring(likeResponse.getStatus().toString().indexOf(" ")+1);
                        Log.d("responsePLa", like[0]);
                        Log.d("responsePLa", dislike[0]);
                        holder.like.setText(like[0]+"");
                        holder.dislike.setText(dislike[0]+"");
                    }

                    @Override
                    public void onFailure(Call<LikeResponse> call, Throwable t) {
                        Log.d("responseP", "error : " + t.toString());
                    }
                });

            }
        });

    }

    public RCAdapter_E(List<String> EID, List<String> EN, List<String> ED, List<String> EF, List<String> EI, List<String> ESD, List<String> EED, List<String> EST, List<String> EET, List<String> EL, List<String> like, List<String> dislike, List<String> ldl, List<String> INI, List<String> interest, Context context) {
        Log.d("Whyred","Constructor");
        Log.d("Whyred",EID.toString());
        Log.d("Whyred",EF.toString());

        this.EID = EID;
        this.EN = EN;
        this.ED = ED;
        this.EF = EF;
        this.ESD = ESD;
        this.EED = EED;
        this.EI = EI;
        this.EL = EL;
        this.EST = EST;
        this.EET = EET;
        this.like = like;
        this.dislike = dislike;
        this.interest = interest;
        this.ldl = ldl;
        this.INI = INI;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return EN.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView EN,like,dislike,interest;
        EditText ESD,EL;
        ImageView Img,IL,IDL;
        Button EMD,INI;
        String EI,ED,EF,EED,EST,EET,EID,ldl,ini;
        public MyHolder(@NonNull View itemView) {

            super(itemView);
            Log.d("Whyred","MyHolder");

            EN = itemView.findViewById(R.id.EventTitle);
            ESD = itemView.findViewById(R.id.ESD);
            EL = itemView.findViewById(R.id.location);
            Img = itemView.findViewById(R.id.Img);
            EMD = itemView.findViewById(R.id.EMD);
            like = itemView.findViewById(R.id.like);
            dislike = itemView.findViewById(R.id.dislike);
            interest = itemView.findViewById(R.id.interest);
            IL = itemView.findViewById(R.id.IL);
            IDL = itemView.findViewById(R.id.IDL);
            INI = itemView.findViewById(R.id.INI);
        }
    }
}
