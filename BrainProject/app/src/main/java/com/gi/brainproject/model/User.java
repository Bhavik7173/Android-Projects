package com.gi.brainproject.model;

public class User {
    String id,name,email,dob,mob,password;

    public String getName() {
        return name;
    }

    public User(String id, String name, String email, String dob, String mob, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.mob = mob;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", mob='" + mob + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public User(String name, String email, String dob, String mob, String password) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.mob = mob;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
