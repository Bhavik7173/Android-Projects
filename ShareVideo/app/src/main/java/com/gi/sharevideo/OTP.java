package com.gi.sharevideo;

import androidx.annotation.NonNull;

public class OTP {
    String status;
    String otp;


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return status+otp;
    }
}
