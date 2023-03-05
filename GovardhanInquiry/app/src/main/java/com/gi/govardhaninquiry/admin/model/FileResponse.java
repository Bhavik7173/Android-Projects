package com.gi.govardhaninquiry.admin.model;

public class FileResponse {
    String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "FileResponse{" +
                "success='" + success + '\'' +
                '}';
    }
}
