package com.example.samples;

import com.google.gson.annotations.SerializedName;

public class PostEventsResponse {

    @SerializedName("status")
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PostEventsResponse{" +
                "status='" + status + '\'' +
                '}';
    }
}
