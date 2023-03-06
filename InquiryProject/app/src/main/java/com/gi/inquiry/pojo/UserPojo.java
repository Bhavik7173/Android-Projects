package com.gi.inquiry.pojo;

import com.google.gson.annotations.SerializedName;

public class UserPojo {
    @SerializedName("user_id")
    String user_id;
    @SerializedName("user_name")
    String user_name;
    @SerializedName("user_password")
    String user_password;
    @SerializedName("user_status")
    String user_status;
    @SerializedName("status")
    String status;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserPojo{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_status='" + user_status + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

