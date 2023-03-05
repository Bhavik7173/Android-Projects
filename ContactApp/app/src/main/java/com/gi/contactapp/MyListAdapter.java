package com.gi.contactapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyListAdapter extends  RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private List<Contact> listdata;
    public MyListAdapter(List<Contact> listdata) {
        this.listdata =  listdata;
    }
    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {
        final Contact myListData = (Contact) listdata.get(position);
        holder.name.setText(((Contact) listdata.get(position)).getName());
        holder.contactno.setText(((Contact) listdata.get(position)).getContact());
        holder.eid.setText(((Contact) listdata.get(position)).getEid());
        holder.email.setText(((Contact) listdata.get(position)).getEmail());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,contactno,eid,email;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.cname);
            this.eid = (TextView) itemView.findViewById(R.id.ceid);
            this.email = (TextView) itemView.findViewById(R.id.cemail);
            this.contactno = (TextView) itemView.findViewById(R.id.cmobile);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.ll_cdesign);
        }
    }
}
