package com.gi.restapiapp;

public class DataModel {

    // string variables for our name and job
    private String name;
    private String job;

    public DataModel(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}