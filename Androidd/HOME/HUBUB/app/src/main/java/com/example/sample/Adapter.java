package com.example.sample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.sample.Frags.AllAlumniData;
import com.example.sample.Frags.AlumniDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Adapter extends RecyclerView.Adapter<com.example.sample.Adapter.MyViewHolder> {
    public List<AlumniNames> alumniNames;
    public Context context;
    RecyclerViewClickInterface recyclerViewClickInterface;
    String data;

    Context context1;
    String Name;
    String Age;
    String Gender;
    String Contact;
    String Image;
    String Status;
    String Email;
    String Qualification;
    String YOP;
    String Experience;
    String Link;
    AllAlumniData ar;

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false),recyclerViewClickInterface);
    }

    public Adapter(List<AlumniNames> alumniNames2, Context context2, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.alumniNames = alumniNames2;
        this.context = context2;
        this.recyclerViewClickInterface=recyclerViewClickInterface;
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.email.setText(this.alumniNames.get(position).getPer_Name());
        Glide.with(holder.imageView.getContext()).load(context.getString(R.string.IP1)+alumniNames.get(position).getPer_Image()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).into(holder.imageView);
    }

    public int getItemCount() {
        return this.alumniNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email;
        ImageView imageView;

        public MyViewHolder(View itemView, final RecyclerViewClickInterface recyclerViewClickInterface) {
            super(itemView);
            this.email = (TextView) itemView.findViewById(R.id.email);
            this.imageView = (ImageView) itemView.findViewById(R.id.profileicon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,alumniNames.get(getAdapterPosition()).Per_Email+"", Toast.LENGTH_SHORT).show();
                    Retrofit r = MyRetrofit.getRetrofit(context.getString(R.string.IP1));
                    Call<AllAlumniData> call;
                    Api api = r.create(Api.class);
                    Log.d("AlumniDataSearch",alumniNames.get(getAdapterPosition()).Per_Email);
                    call = api.getDataSearchAlumni(alumniNames.get(getAdapterPosition()).Per_Email);
                    call.enqueue(new Callback<AllAlumniData>() {
                        @Override
                        public void onResponse(Call<AllAlumniData> call, Response<AllAlumniData> response) {
                            ar=response.body();
                            Log.d("Data",ar.toString());
                            Intent intent = new Intent(context, AlumniDetails.class);

                            intent.putExtra("Name",ar.getName());
                            intent.putExtra("Age",ar.getAge());
                            intent.putExtra("Gender",ar.getGender());
                            intent.putExtra("Image",ar.getImage());
                            intent.putExtra("Email",ar.getEmail());
                            intent.putExtra("Status",ar.getStatus());
                            intent.putExtra("YOP",ar.getYOP());
                            intent.putExtra("Qualification",ar.getQualification());
                            intent.putExtra("Experience",ar.getExperience());
                            intent.putExtra("Link",ar.getLink());
                            intent.putExtra("Contact",ar.getContact());
                            context.startActivity(intent);

                        }

                        @Override
                        public void onFailure(Call<AllAlumniData> call, Throwable t) {
                            Log.d("WhyredE",t.toString());
                        }
                    });

                }

            });
        }

    }

}