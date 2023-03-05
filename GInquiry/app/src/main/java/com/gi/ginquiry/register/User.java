package com.gi.ginquiry.register;

public class User {
    String uname,uemail,upassword,ustatus;

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

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", uemail='" + uemail + '\'' +
                ", upassword='" + upassword + '\'' +
                ", ustatus='" + ustatus + '\'' +
                '}';
    }
}
