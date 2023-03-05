package com.example.samples.Frags;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllSkillData {
    @SerializedName("Skill")
    String Skill;
    @SerializedName("Time")
    String Time;

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @NonNull
    @Override
    public String toString() {
        return Skill + " : " + Time;
    }
}
