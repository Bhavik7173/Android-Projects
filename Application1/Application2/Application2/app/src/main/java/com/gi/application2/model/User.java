package com.gi.application2.model;


public class User {
    private static User user = null;
    String user_name;
    String email = "Admin@gmail.com";
    String pass = "Admin@123";

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        User.user = user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

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

    private User()
    {}
    public static User getInstance()
    {
        if(user == null)
        {
            user = new User();
        }
        return user;
    }
}
