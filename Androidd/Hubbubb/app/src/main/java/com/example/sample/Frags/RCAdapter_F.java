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
import com.bumptech.glide.request.RequestOptions;
import com.example.sample.R;

import java.util.ArrayList;
import java.util.List;

public class RCAdapter_F extends RecyclerView.Adapter<RCAdapter_F.MyHolder> {

    ArrayList<AllFacultyData> arrayList;
    List<String> Name;
    List<String> Age;
    List<String> Gender;
    List<String> Contact;
    List<String> Image;
    List<String> Status;
    List<String> Email;
    List<String> Qualification;
    List<String> YOP;
    List<String> Experience;
    List<String> Link;
    Context context;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("Whyred","onCreateView");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rvcl_faculty,null);
        context = view.getContext();
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        Log.d("WhyredE","Bind");
        holder.Name.setText(Name.get(position));
        final String ImagePath=Image.get(position);
        Log.d("Whyred1",ImagePath);
        holder.Image.setBackground(context.getDrawable(R.drawable.circle_outline));
        Glide.with(holder.Image.getContext()).load(context.getString(R.string.IP1)+Image.get(position)).error(R.drawable.ic_person_black_24dp).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).into(holder.Image);
        holder.Age.setText(Age.get(position));
        holder.gender = (Gender.get(position));
        holder.Qualification.setText(Qualification.get(position));
        holder.Status.setText(Status.get(position));
        holder.YOP.setText(YOP.get(position));
        holder.contact = Contact.get(position);
        holder.email = Email.get(position);
        holder.experience = Experience.get(position);
        holder.link = Link.get(position);
        Log.d("Whyred1","binded data"+" "+Image);
        holder.MD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Whyred22",v.getContext().toString());
                Intent intent = new Intent(v.getContext(),AlumniDetails.class);
                intent.putExtra("Name",holder.Name.getText()+"");
                intent.putExtra("Age",holder.Age.getText()+"");
                intent.putExtra("Gender",holder.gender);
                Log.d("Whyred1",holder.Name.getText()+"");

                intent.putExtra("Image",ImagePath);
                Log.d("Whyred1",holder.Image+"");
                intent.putExtra("Email",holder.email);
                intent.putExtra("Status",holder.Status.getText()+"");
                intent.putExtra("YOP",holder.YOP.getText()+"");
                intent.putExtra("Qualification",holder.Qualification.getText()+"");
                intent.putExtra("Experience",holder.experience);
                intent.putExtra("Link",holder.link);
                intent.putExtra("Contact",holder.contact);
                Log.d("Whyred1","after intent");
                context.startActivity(intent);

            }
        });

    }

    public RCAdapter_F(List<String> Name, List<String> Age, List<String> Gender, List<String> Contact, List<String> Image, List<String> Status, List<String> Email, List<String> Qualification, List<String> YOP, List<String> Experience, List<String> Link, Context context) {
        Log.d("Whyred","Constructor");
        Log.d("Whyred",Image.toString());

        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
        this.Contact = Contact;
        this.Image = Image;
        this.Status = Status;
        this.Email = Email;
        this.Qualification = Qualification;
        this.YOP = YOP;
        this.Experience = Experience;
        this.Link = Link;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView Name;
        EditText Status,Qualification,YOP,Age;
        ImageView Image;
        String contact,email,experience,link,gender;
        Button MD;
        public MyHolder(@NonNull View itemView) {

            super(itemView);
            Log.d("Whyred","MyHolder");
            Name = itemView.findViewById(R.id.Name);
            Image = itemView.findViewById(R.id.Image);
            Status = itemView.findViewById(R.id.Status);
            Qualification = itemView.findViewById(R.id.Qualification);
            YOP = itemView.findViewById(R.id.YOP);
            Age = itemView.findViewById(R.id.Age);
            MD = itemView.findViewById(R.id.MD);




        }
    }
}
