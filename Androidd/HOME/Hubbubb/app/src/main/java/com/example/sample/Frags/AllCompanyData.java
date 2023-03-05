package com.example.sample.Frags;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllCompanyData {
    @SerializedName("C_Name")
    String C_Name;
    @SerializedName("C_DOJ")
    String C_DOJ;
    @SerializedName("C_DOL")
    String C_DOL;
    @SerializedName("C_Post")
    String C_Post;
    @SerializedName("C_Salary")
    String C_Salary;

    public String getC_Name() {
        return C_Name;
    }

    public void setC_Name(String c_Name) {
        C_Name = c_Name;
    }

    public String getC_DOJ() {
        return C_DOJ;
    }

    public void setC_DOJ(String c_DOJ) {
        C_DOJ = c_DOJ;
    }

    public String getC_DOL() {
        return C_DOL;
    }

    public void setC_DOL(String c_DOL) {
        C_DOL = c_DOL;
    }

    public String getC_Post() {
        return C_Post;
    }

    public void setC_Post(String c_Post) {
        C_Post = c_Post;
    }

    public String getC_Salary() {
        return C_Salary;
    }

    public void setC_Salary(String c_Salary) {
        C_Salary = c_Salary;
    }

    @NonNull
    @Override
    public String toString() {
        return C_Name + " : " + C_DOJ + " : " + C_DOL + " : " + C_Post + " : " + C_Salary;
    }
}
