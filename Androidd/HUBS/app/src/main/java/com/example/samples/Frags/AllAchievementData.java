package com.example.samples.Frags;

import androidx.annotation.NonNull;

import com.airbnb.lottie.manager.ImageAssetManager;
import com.google.gson.annotations.SerializedName;

public class AllAchievementData {
    @SerializedName("Achievement")
    String Ach;
    @SerializedName("Year")
    String Year;
    @SerializedName("Rank")
    String Rank;
    @SerializedName("Image")
    String Image;

    public String getAch() {
        return Ach;
    }

    public void setAch(String ach) {
        Ach = ach;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return Ach + " : " + Year + " : " + Rank + " : " + Image;
    }
}
