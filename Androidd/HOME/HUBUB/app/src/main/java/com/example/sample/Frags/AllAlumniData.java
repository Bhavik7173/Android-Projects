package com.example.sample.Frags;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AllAlumniData {
    @SerializedName("Name")
    String Name;
    @SerializedName("Age")
    String Age;
    @SerializedName("Gender")
    String Gender;
    @SerializedName("Contact")
    String Contact;
    @SerializedName("Image")
    String Image;
    @SerializedName("Status")
    String Status;
    @SerializedName("Email")
    String Email;
    @SerializedName("Qualification")
    String Qualification;
    @SerializedName("YOP")
    String YOP;
    @SerializedName("Experience")
    String Experience;
    @SerializedName("Link")
    String Link;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getYOP() {
        return YOP;
    }

    public void setYOP(String YOP) {
        this.YOP = YOP;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    @NonNull
    @Override
    public String toString() {
        return Name + " : " + Age + " : " + Gender + " : " + Contact + " : " + Image + " : " + Status + " : " + Email + " : " + Qualification + " : " + YOP + " : " + Experience + " : " + Link;
    }
}
