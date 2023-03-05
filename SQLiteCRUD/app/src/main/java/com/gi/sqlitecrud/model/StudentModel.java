package com.gi.sqlitecrud.model;

public class StudentModel {
    private int id;
    private String sname;
    private String syear;

    public StudentModel(int id, String sname, String syear) {
        this.id = id;
        this.sname = sname;
        this.syear = syear;
    }

    public StudentModel(String sname, String syear) {
        this.sname = sname;
        this.syear = syear;
    }

    public StudentModel() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSyear() {
        return syear;
    }

    public void setSyear(String syear) {
        this.syear = syear;
    }




}

