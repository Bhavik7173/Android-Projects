package com.gi.brainproject.model;

import android.util.Log;

public class PatientResponse {
    String uid,name, gender, age, contact,status;

    String image;

    public String getStatus() {
        return status;
    }

    public PatientResponse(String uid, String name, String gender, String age, String contact, String status, String image) {
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.contact = contact;
        this.status = status;
        this.image = image;
    }

    @Override
    public String toString() {
        return "PatientResponse{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", contact='" + contact + '\'' +
                ", status='" + status + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PatientResponse() {
        Log.d("gilog","from constructor");
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Log.d("gilog from name method",name);
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PatientResponse(String uid, String name, String gender, String age, String contact, String image) {
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.contact = contact;
        this.image = image;
    }

}
