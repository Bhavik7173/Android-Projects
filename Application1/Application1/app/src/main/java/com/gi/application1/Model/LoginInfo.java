package com.gi.application1.Model;

//import com.google.gson.annotations.SerializedName;
public class LoginInfo {
    //    @SerializedName("email")
    String email = "admin@gmail.com";
    //    @SerializedName("pass")
    String pass = "admin@123";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

//    public LoginInfo(String email, String pass) {
//        this.email = email;
//        this.pass = pass;
//    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
