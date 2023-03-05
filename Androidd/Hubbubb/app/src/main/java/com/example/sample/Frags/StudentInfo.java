package com.example.sample.Frags;

import com.google.gson.annotations.SerializedName;

public class StudentInfo {
    @SerializedName("Class1")
    String S_Class;
    @SerializedName("Div")
    String Div;
    @SerializedName("Mentor")
    String Mentor;

    public String getS_Class() {
        return S_Class;
    }

    public void setS_Class(String s_Class) {
        S_Class = s_Class;
    }

    public String getDiv() {
        return Div;
    }

    public void setDiv(String div) {
        Div = div;
    }

    public String getMentor() {
        return Mentor;
    }

    public void setMentor(String mentor) {
        Mentor = mentor;
    }

}
