package com.gi.projectgroup;

public class User {
    String name,email,dob,mob,password;

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", mob='" + mob + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
