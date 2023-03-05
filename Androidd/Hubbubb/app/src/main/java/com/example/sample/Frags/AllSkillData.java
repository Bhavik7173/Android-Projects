package com.example.sample.Frags;

import com.google.gson.annotations.SerializedName;

public class AllSkillData {
    @SerializedName("Skill")
    String Skill;
    @SerializedName("Time")
    String Time1;

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public String getTime1() {
        return Time1;
    }

    public void setTime1(String time1) {
        Time1 = time1;
    }

    @Override
    public String toString() {
        return "AllSkillData{" +
                "Skill='" + Skill + '\'' +
                ", Time1='" + Time1 + '\'' +
                '}';
    }
}
