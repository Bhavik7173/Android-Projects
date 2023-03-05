package com.gi.brainproject.model;

import okhttp3.MultipartBody;

public class PatientData {
    String id,name, gender, age, contact;

    MultipartBody.Part image;


    public PatientData(String uid, String name, String age, String contact, String gen, MultipartBody.Part partImage) {
        this.id = uid;
        this.name = name;
        this.gender = gen;
        this.age = age;
        this.contact = contact;
        this.image = partImage;
    }

    public PatientData(String userid, String name, String age, String contact, String gen) {
        this.id = userid;
        this.name = name;
        this.gender = gen;
        this.age = age;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public MultipartBody.Part getImage() {
        return image;
    }

    public void setImage(MultipartBody.Part image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "PatientData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
