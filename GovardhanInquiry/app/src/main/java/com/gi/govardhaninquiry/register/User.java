package com.gi.govardhaninquiry.register;

public class User {
    String id,uname,uemail,upassword,ustatus;

    public String getUid() {
        return id;
    }

    public void setUid(String uid) {
        this.id = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public User(String uname, String uemail, String upassword, String ustatus) {
        this.uname = uname;
        this.uemail = uemail;
        this.upassword = upassword;
        this.ustatus = ustatus;
    }

    public User(String uid, String uname, String uemail, String upassword, String ustatus) {
        this.id = uid;
        this.uname = uname;
        this.uemail = uemail;
        this.upassword = upassword;
        this.ustatus = ustatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", uname='" + uname + '\'' +
                ", uemail='" + uemail + '\'' +
                ", upassword='" + upassword + '\'' +
                ", ustatus='" + ustatus + '\'' +
                '}';
    }
}
