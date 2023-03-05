package com.gi.contactapp;

public class Contact {
    private String name;
    private String email;
    String contactno,eid;

    public Contact(String name, String email, String contact, String eid) {
        this.name = name;
        this.email = email;
        this.contactno = contact;
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contactno;
    }

    public void setContact(String contact) {
        this.contactno = contact;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contactno + '\'' +
                ", eid='" + eid + '\'' +
                '}';
    }
}
