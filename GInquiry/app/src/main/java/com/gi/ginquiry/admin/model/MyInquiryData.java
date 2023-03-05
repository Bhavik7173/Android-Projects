package com.gi.ginquiry.admin.model;

public class MyInquiryData {
    String iname, idate, icourse, imobile, iemail, ipreferred, inote, icollege;
    int iid;

    public MyInquiryData(String iname, String idate, String icourse, String imobile) {
        this.iname = iname;
        this.idate = idate;
        this.icourse = icourse;
        this.imobile = imobile;
    }

    public MyInquiryData() {
    }

    public MyInquiryData(String iname, String idate, String icourse, String imobile, String iemail, String ipreferred, String inote, String icollege) {
        this.iname = iname;
        this.imobile = imobile;
        this.icourse = icourse;
        this.idate = idate;
        this.iemail = iemail;
        this.inote = inote;
        this.icollege = icollege;
        this.ipreferred = ipreferred;
    }

    @Override
    public String toString() {
        return "MyInquiryData{" +
                "iname='" + iname + '\'' +
                ", idate='" + idate + '\'' +
                ", icourse='" + icourse + '\'' +
                ", imobile='" + imobile + '\'' +
                ", iemail='" + iemail + '\'' +
                ", ipreferred='" + ipreferred + '\'' +
                ", inote='" + inote + '\'' +
                ", icollege='" + icollege + '\'' +
                ", iid=" + iid +
                '}';
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getIemail() {
        return iemail;
    }

    public void setIemail(String iemail) {
        this.iemail = iemail;
    }

    public String getIpreferred() {
        return ipreferred;
    }

    public void setIpreferred(String ipreferred) {
        this.ipreferred = ipreferred;
    }

    public String getInote() {
        return inote;
    }

    public void setInote(String inote) {
        this.inote = inote;
    }

    public String getIcollege() {
        return icollege;
    }

    public void setIcollege(String icollege) {
        this.icollege = icollege;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIdate() {
        return idate;
    }

    public void setIdate(String idate) {
        this.idate = idate;
    }

    public String getIcourse() {
        return icourse;
    }

    public void setIcourse(String icourse) {
        this.icourse = icourse;
    }

    public String getImobile() {
        return imobile;
    }

    public void setImobile(String imobile) {
        this.imobile = imobile;
    }
}
