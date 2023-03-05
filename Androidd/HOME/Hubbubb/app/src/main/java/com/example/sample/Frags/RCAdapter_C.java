package com.example.sample.Frags;

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

import com.example.sample.R;

import java.util.ArrayList;
import java.util.List;

public class RCAdapter_C extends RecyclerView.Adapter<RCAdapter_C.MyHolder> {

    ArrayList<AllCompanyData> arrayList;
    List<String> C_Name;
    List<String> C_DOJ;
    List<String> C_DOL;
    List<String> C_Post;
    List<String> C_Salary;
    Context context;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("Whyred","onCreateView");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rvcl_companyprofile,null);
        context = view.getContext();
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        Log.d("Whyred","Bind");
        holder.CN.setText(C_Name.get(position));
        Log.d("Whyred1",C_Name.get(position));
        holder.CDOJ.setText(C_DOJ.get(position));
        holder.CDOL.setText(C_DOL.get(position));
        holder.CP.setText(C_Post.get(position));
        holder.CS.setText(C_Salary.get(position));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CompanyDetails.class);
                intent.putExtra("CN",holder.CN.getText()+"");
                intent.putExtra("CDOJ",holder.CDOJ.getText()+"");
                intent.putExtra("CDOL",holder.CDOL.getText()+"");
                intent.putExtra("CP",holder.CP.getText()+"");
                intent.putExtra("CS",holder.CS.getText()+"");
                intent.putExtra("func","Update");
                Log.d("Whyred1","after intent");
                context.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CompanyDetails.class);
                intent.putExtra("CN",holder.CN.getText()+"");
                intent.putExtra("CDOJ",holder.CDOJ.getText()+"");
                intent.putExtra("CDOL",holder.CDOL.getText()+"");
                intent.putExtra("CP",holder.CP.getText()+"");
                intent.putExtra("CS",holder.CS.getText()+"");
                intent.putExtra("func","Delete");
                Log.d("Whyred1","after intent");
                context.startActivity(intent);
            }
        });

    }

    public RCAdapter_C(List<String> CN, List<String> CDOJ, List<String> CDOL, List<String> CP, List<String> CS, Context context) {
        Log.d("Whyred","Constructor");
        Log.d("Whyred",CN.toString());

        this.C_Name = CN;
        this.C_DOJ = CDOJ;
        this.C_DOL = CDOL;
        this.C_Post = CP;
        this.C_Salary = CS;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return C_Name.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView CN;
        EditText CDOJ,CDOL,CP,CS;
        Button edit,delete;
        public MyHolder(@NonNull View itemView) {

            super(itemView);
            Log.d("Whyred","MyHolder");
            CN = itemView.findViewById(R.id.CN);
            CDOJ = itemView.findViewById(R.id.CDOJ);
            CDOL = itemView.findViewById(R.id.CDOL);
            CP = itemView.findViewById(R.id.CP);
            CS = itemView.findViewById(R.id.CS);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);




        }
    }
}
