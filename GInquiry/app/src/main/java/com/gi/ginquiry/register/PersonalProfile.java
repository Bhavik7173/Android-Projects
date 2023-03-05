package com.gi.ginquiry.register;

public class PersonalProfile {
    String uid, DOB, image, Gen, CN1, CN2, Address,Hobby;

    public PersonalProfile(String uid, String DOB, String image, String gen, String CN1, String CN2, String address, String hobby) {
        this.uid = uid;
        this.DOB = DOB;
        this.image = image;
        Gen = gen;
        this.CN1 = CN1;
        this.CN2 = CN2;
        Address = address;
        Hobby = hobby;
    }

    @Override
    public String toString() {
        return "PersonalProfile{" +
                "uid='" + uid + '\'' +
                ", DOB='" + DOB + '\'' +
                ", image='" + image + '\'' +
                ", Gen='" + Gen + '\'' +
                ", CN1='" + CN1 + '\'' +
                ", CN2='" + CN2 + '\'' +
                ", Address='" + Address + '\'' +
                ", Hobby='" + Hobby + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGen() {
        return Gen;
    }

    public void setGen(String gen) {
        Gen = gen;
    }

    public String getCN1() {
        return CN1;
    }

    public void setCN1(String CN1) {
        this.CN1 = CN1;
    }

    public String getCN2() {
        return CN2;
    }

    public void setCN2(String CN2) {
        this.CN2 = CN2;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getHobby() {
        return Hobby;
    }

    public void setHobby(String hobby) {
        Hobby = hobby;
    }
}
