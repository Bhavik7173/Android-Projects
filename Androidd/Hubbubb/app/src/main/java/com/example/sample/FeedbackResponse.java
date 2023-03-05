package com.example.sample;

import com.google.gson.annotations.SerializedName;

public class FeedbackResponse {
    @SerializedName("status")
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
