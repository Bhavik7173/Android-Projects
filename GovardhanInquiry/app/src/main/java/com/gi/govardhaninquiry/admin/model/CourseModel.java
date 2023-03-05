package com.gi.govardhaninquiry.admin.model;

public class CourseModel {
    String cname, cfee, cduration, cprerequest, csyllabus, cprogram,clogo;
    int id;

    public CourseModel(String cname, String cfee, String cduration, String cprerequest) {
        this.cname = cname;
        this.cfee = cfee;
        this.cduration = cduration;
        this.cprerequest = cprerequest;
    }

    public String getClogo() {
        return clogo;
    }

    public CourseModel(String cname, String cfee, String cduration, String cprerequest, String csyllabus, String cprogram, String clogo, int id) {
        this.cname = cname;
        this.cfee = cfee;
        this.cduration = cduration;
        this.cprerequest = cprerequest;
        this.csyllabus = csyllabus;
        this.cprogram = cprogram;
        this.clogo = clogo;
        this.id = id;
    }

    public void setClogo(String clogo) {
        this.clogo = clogo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CourseModel{" +
                "cname='" + cname + '\'' +
                ", cfee='" + cfee + '\'' +
                ", cduration='" + cduration + '\'' +
                ", cprerequest='" + cprerequest + '\'' +
                ", csyllabus='" + csyllabus + '\'' +
                ", cprogram='" + cprogram + '\'' +
                ", clogo='" + clogo + '\'' +
                ", id=" + id +
                '}';
    }

    public CourseModel() {

    }

    public CourseModel(int cid, String cname, String cfee, String cduration, String cprerequest, String csyllabus, String cprogram) {
        this.cname = cname;
        this.cfee = cfee;
        this.cduration = cduration;
        this.cprerequest = cprerequest;
        this.csyllabus = csyllabus;
        this.cprogram = cprogram;
        this.id = cid;
    }


    public String getCsyllabus() {
        return csyllabus;
    }

    public void setCsyllabus(String csyllabus) {
        this.csyllabus = csyllabus;
    }

    public String getCprogram() {
        return cprogram;
    }

    public void setCprogram(String cprogram) {
        this.cprogram = cprogram;
    }

    public int getCid() {
        return id;
    }


    public void setCid(int cid) {
        this.id = cid;
    }

    public CourseModel(String cname, String cfee, String cduration, String cprerequest, int cid) {
        this.cname = cname;
        this.cfee = cfee;
        this.cduration = cduration;
        this.cprerequest = cprerequest;
        this.id = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCfee() {
        return cfee;
    }

    public void setCfee(String cfee) {
        this.cfee = cfee;
    }

    public String getCduration() {
        return cduration;
    }

    public void setCduration(String cduration) {
        this.cduration = cduration;
    }

    public String getCprerequest() {
        return cprerequest;
    }

    public void setCprerequest(String cprerequest) {
        this.cprerequest = cprerequest;
    }
}
