package com.gi.progrudrawer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {


    private final Context context;
    private final int resources;
    private final DataModel[] objects;

    public DrawerItemCustomAdapter(@NonNull Context context, int resource, @NonNull DataModel[] objects) {
        super(context, resource, objects);
        this.context  = context;
        this.resources = resource;
        this.objects = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        listItem = inflater.inflate(resources, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        DataModel folder = objects[position];


        imageViewIcon.setImageResource(folder.image);
        textViewName.setText(folder.name);

        return listItem;
    }


}
