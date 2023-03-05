package com.gi.brainproject.model;

import android.graphics.drawable.Drawable;

public class ReportData {
    String name;
    Drawable image;

    public String getName() {
        return name;
    }

    public ReportData(String name, Drawable image) {
        this.name = name;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ReportData{" +
                "name='" + name + '\'' +
                ", image=" + image +
                '}';
    }
}
