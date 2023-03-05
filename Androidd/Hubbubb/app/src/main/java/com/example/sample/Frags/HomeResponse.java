package com.example.sample.Frags;

import com.google.gson.annotations.SerializedName;

public class HomeResponse {
    @SerializedName("title")
    String title;
    @SerializedName("icon")
    String icon;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "HomeResponse{" +
                "title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}