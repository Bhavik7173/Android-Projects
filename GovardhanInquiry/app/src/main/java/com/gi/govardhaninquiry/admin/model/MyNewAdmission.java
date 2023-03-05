package com.gi.govardhaninquiry.admin.model;

public class MyNewAdmission {
    String sname, smobile, semail, scourse, sfaculty, naid;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSmobile() {
        return smobile;
    }

    public void setSmobile(String smobile) {
        this.smobile = smobile;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getScourse() {
        return scourse;
    }

    public void setScourse(String scourse) {
        this.scourse = scourse;
    }

    public String getSfaculty() {
        return sfaculty;
    }

    public void setSfaculty(String sfaculty) {
        this.sfaculty = sfaculty;
    }

    public String getNaid() {
        return naid;
    }

    public void setNaid(String naid) {
        this.naid = naid;
    }

    public MyNewAdmission(String sname, String smobile, String semail, String scourse, String sfaculty) {
        this.sname = sname;
        this.smobile = smobile;
        this.semail = semail;
        this.scourse = scourse;
        this.sfaculty = sfaculty;
    }

    public MyNewAdmission(String sname, String smobile, String semail, String scourse, String sfaculty, String naid) {
        this.sname = sname;
        this.smobile = smobile;
        this.semail = semail;
        this.scourse = scourse;
        this.sfaculty = sfaculty;
        this.naid = naid;
    }
}
