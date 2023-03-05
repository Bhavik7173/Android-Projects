package com.gi.ginquiry.admin.model;

public class MyCourseData {
    String cname, cfee, cduration, cprerequest, csyllabus, cprogram;
    int cid;

    public MyCourseData(String cname, String cfee, String cduration, String cprerequest) {
        this.cname = cname;
        this.cfee = cfee;
        this.cduration = cduration;
        this.cprerequest = cprerequest;
    }

    public MyCourseData() {

    }

    public MyCourseData(int cid, String cname, String cfee, String cduration, String cprerequest, String csyllabus, String cprogram) {
        this.cname = cname;
        this.cfee = cfee;
        this.cduration = cduration;
        this.cprerequest = cprerequest;
        this.csyllabus = csyllabus;
        this.cprogram = cprogram;
        this.cid = cid;
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
        return cid;
    }


    public void setCid(int cid) {
        this.cid = cid;
    }

    public MyCourseData(String cname, String cfee, String cduration, String cprerequest, int cid) {
        this.cname = cname;
        this.cfee = cfee;
        this.cduration = cduration;
        this.cprerequest = cprerequest;
        this.cid = cid;
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
