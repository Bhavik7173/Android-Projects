package com.example.sample.Frags;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.sample.R;

import java.util.ArrayList;
import java.util.List;

public class RCAdapter_Ach extends RecyclerView.Adapter<RCAdapter_Ach.MyHolder> {

    ArrayList<AllAchievementData> arrayList;
    List<String> Ach;
    List<String> Year;
    List<String> Rank;
    List<String> Image;
    Context context;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("Whyred","onCreateView");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rvcl_ach,null);
        context = view.getContext();
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {

        Log.d("Whyred","Bind");
        holder.AN.setText("Achievement "+(position+1)+"");
        //Log.d("Whyred1",Ach.get(position));
        holder.Ach.setText(Ach.get(position));
        final String path = Image.get(position);
        holder.AY.setText(Year.get(position));
        holder.AR.setText(Rank.get(position));
        if(!path.isEmpty()) {
            Glide.with(holder.Image.getContext()).load(context.getString(R.string.IP1)+Image.get(position)).error(R.drawable.ic_camera_black_24dp).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    holder.progressBar.setVisibility(View.GONE);
                    holder.Image.setImageResource(R.drawable.ic_camera_black_24dp);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    holder.progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(holder.Image);        }

        Log.d("WhyredAchImage",context.getString(R.string.IP1)+Image.get(position));
        Log.d("WhyredAchImage",context.getString(R.string.IP1)+path);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AchievementDetails.class);
                intent.putExtra("AN",holder.AN.getText()+"");
                intent.putExtra("Ach",holder.Ach.getText()+"");
                intent.putExtra("AY",holder.AY.getText()+"");
                intent.putExtra("AR",holder.AR.getText()+"");
                intent.putExtra("Image",path);
                intent.putExtra("func","Update");
                Log.d("Whyred1","after intent");
                context.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AchievementDetails.class);
                intent.putExtra("AN",holder.AN.getText()+"");
                intent.putExtra("Ach",holder.Ach.getText()+"");
                intent.putExtra("AY",holder.AY.getText()+"");
                intent.putExtra("AR",holder.AR.getText()+"");
                intent.putExtra("Image",path);
                intent.putExtra("func","Delete");
                Log.d("Whyred1","after intent");
                context.startActivity(intent);
            }
        });
    }

    public RCAdapter_Ach(List<String> Ach, List<String> Year, List<String> Rank, List<String> Image, Context context) {
        Log.d("Whyred","Constructor");
        Log.d("Whyred",Ach.toString());

        this.Ach = Ach;
        this.Year = Year;
        this.Rank = Rank;
        this.Image = Image;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return Ach.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView AN;
        EditText Ach,AY,AR;
        Button edit,delete;
        ImageView Image;
        ProgressBar progressBar;

        public MyHolder(@NonNull View itemView) {

            super(itemView);
            Log.d("Whyred","MyHolder");
            AN = itemView.findViewById(R.id.AN);
            Ach = itemView.findViewById(R.id.Ach);
            AY = itemView.findViewById(R.id.AY);
            AR = itemView.findViewById(R.id.AR);
            Image = itemView.findViewById(R.id.AchImage);
            progressBar = itemView.findViewById(R.id.imageloading);
            Log.d("Whyred",Image.toString());

            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);




        }
    }
}
