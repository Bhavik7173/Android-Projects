package com.example.sample;

import com.google.gson.annotations.SerializedName;

public class GetDetails {

    @SerializedName("id")
    String get_id;
    @SerializedName("status")
    String get_status;

    public String getGet_id() {
        return get_id;
    }

    public void setGet_id(String get_id) {
        this.get_id = get_id;
    }

    public String getGet_status() {
        return get_status;
    }

    public void setGet_status(String get_status) {
        this.get_status = get_status;
    }

    @Override
    public String toString() {
        return "GetDetails{" +
                "get_id='" + get_id + '\'' +
                ", get_status='" + get_status + '\'' +
                '}';
    }
}
