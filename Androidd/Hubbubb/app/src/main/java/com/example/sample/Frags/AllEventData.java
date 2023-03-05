package com.example.sample.Frags;

import com.google.gson.annotations.SerializedName;

public class AllEventData {

    String EID;
    String EN;
    String ED;
    String ESD;
    String EED;
    String EI;
    String EF;
    String EST;
    String EET;
    String EL;
    String like;
    String dislike;
    String interest;
    String ldl;
    String INI;

    public String getEID() {
        return EID;
    }

    public void setEID(String EID) {
        this.EID = EID;
    }

    public String getEN() {
        return EN;
    }

    public void setEN(String EN) {
        this.EN = EN;
    }

    public String getED() {
        return ED;
    }

    public void setED(String ED) {
        this.ED = ED;
    }

    public String getESD() {
        return ESD;
    }

    public void setESD(String ESD) {
        this.ESD = ESD;
    }

    public String getEED() {
        return EED;
    }

    public void setEED(String EED) {
        this.EED = EED;
    }

    public String getEI() {
        return EI;
    }

    public void setEI(String EI) {
        this.EI = EI;
    }

    public String getEF() {
        return EF;
    }

    public void setEF(String EF) {
        this.EF = EF;
    }

    public String getEST() {
        return EST;
    }

    public void setEST(String EST) {
        this.EST = EST;
    }

    public String getEET() {
        return EET;
    }

    public void setEET(String EET) {
        this.EET = EET;
    }

    public String getEL() {
        return EL;
    }

    public void setEL(String EL) {
        this.EL = EL;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getLdl() {
        return ldl;
    }

    public void setLdl(String ldl) {
        this.ldl = ldl;
    }

    public String getINI() {
        return INI;
    }

    public void setINI(String INI) {
        this.INI = INI;
    }

    @Override
    public String toString() {
        return "AllEventData{" +
                "EID='" + EID + '\'' +
                ", EN='" + EN + '\'' +
                ", ED='" + ED + '\'' +
                ", ESD='" + ESD + '\'' +
                ", EED='" + EED + '\'' +
                ", EI='" + EI + '\'' +
                ", EF='" + EF + '\'' +
                ", EST='" + EST + '\'' +
                ", EET='" + EET + '\'' +
                ", EL='" + EL + '\'' +
                ", like='" + like + '\'' +
                ", dislike='" + dislike + '\'' +
                ", interest='" + interest + '\'' +
                ", ldl='" + ldl + '\'' +
                ", INI='" + INI + '\'' +
                '}';
    }
}