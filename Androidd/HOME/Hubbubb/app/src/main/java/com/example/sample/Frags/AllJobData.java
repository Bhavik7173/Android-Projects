package com.example.sample.Frags;

import com.google.gson.annotations.SerializedName;

public class AllJobData {
    @SerializedName("JID")
    String JID;
    @SerializedName("JT")
    String JT;
    @SerializedName("JD")
    String JD;
    @SerializedName("JRE")
    String JRE;
    @SerializedName("JF")
    String JF;
    @SerializedName("JS")
    String JS;
    @SerializedName("JSD")
    String JSD;
    @SerializedName("JED")
    String JED;
    @SerializedName("JI")
    String JI;
    @SerializedName("JL")
    String JL;
    @SerializedName("JTA")
    String JTA;
    @SerializedName("apply")
    String apply;

    public String getJID() {
        return JID;
    }

    public void setJID(String JID) {
        this.JID = JID;
    }

    public String getJT() {
        return JT;
    }

    public void setJT(String JT) {
        this.JT = JT;
    }

    public String getJD() {
        return JD;
    }

    public void setJD(String JD) {
        this.JD = JD;
    }

    public String getJRE() {
        return JRE;
    }

    public void setJRE(String JRE) {
        this.JRE = JRE;
    }

    public String getJF() {
        return JF;
    }

    public void setJF(String JF) {
        this.JF = JF;
    }

    public String getJS() {
        return JS;
    }

    public void setJS(String JS) {
        this.JS = JS;
    }

    public String getJSD() {
        return JSD;
    }

    public void setJSD(String JSD) {
        this.JSD = JSD;
    }

    public String getJED() {
        return JED;
    }

    public void setJED(String JED) {
        this.JED = JED;
    }

    public String getJI() {
        return JI;
    }

    public void setJI(String JI) {
        this.JI = JI;
    }

    public String getJL() {
        return JL;
    }

    public void setJL(String JL) {
        this.JL = JL;
    }

    public String getJTA() {
        return JTA;
    }

    public void setJTA(String JTA) {
        this.JTA = JTA;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    @Override
    public String toString() {
        return "AllJobData{" +
                "JID='" + JID + '\'' +
                ", JT='" + JT + '\'' +
                ", JD='" + JD + '\'' +
                ", JRE='" + JRE + '\'' +
                ", JF='" + JF + '\'' +
                ", JS='" + JS + '\'' +
                ", JSD='" + JSD + '\'' +
                ", JED='" + JED + '\'' +
                ", JI='" + JI + '\'' +
                ", JL='" + JL + '\'' +
                ", JTA='" + JTA + '\'' +
                ", apply='" + apply + '\'' +
                '}';
    }
}