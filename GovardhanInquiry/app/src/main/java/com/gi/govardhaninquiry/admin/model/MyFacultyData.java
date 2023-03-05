package com.gi.govardhaninquiry.admin.model;

public class MyFacultyData {
    String name, dob, doj, mobile, whatapp, email, yoe, address, refernecename, referenceno, technology, note;
    String id;
    String qualification, resume, aadhar_card, photo;

    public MyFacultyData(String name, String dob, String doj, String mobile, String whatapp, String email, String yoe, String address, String refernecename, String referenceno, String technology, String note, String id, String qualification, String resume, String aadhar_card, String photo) {
        this.name = name;
        this.dob = dob;
        this.doj = doj;
        this.mobile = mobile;
        this.whatapp = whatapp;
        this.email = email;
        this.yoe = yoe;
        this.address = address;
        this.refernecename = refernecename;
        this.referenceno = referenceno;
        this.technology = technology;
        this.note = note;
        this.id = id;
        this.qualification = qualification;
        this.resume = resume;
        this.aadhar_card = aadhar_card;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "MyFacultyData{" +
                "name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", doj='" + doj + '\'' +
                ", mobile='" + mobile + '\'' +
                ", whatapp='" + whatapp + '\'' +
                ", email='" + email + '\'' +
                ", yoe='" + yoe + '\'' +
                ", address='" + address + '\'' +
                ", refernecename='" + refernecename + '\'' +
                ", referenceno='" + referenceno + '\'' +
                ", technology='" + technology + '\'' +
                ", note='" + note + '\'' +
                ", id=" + id +
                ", qualification='" + qualification + '\'' +
                ", resume='" + resume + '\'' +
                ", aadhar_card='" + aadhar_card + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getAadhar_card() {
        return aadhar_card;
    }

    public void setAadhar_card(String aadhar_card) {
        this.aadhar_card = aadhar_card;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public MyFacultyData(String name, String dob, String doj, String mobile, String whatapp, String email, String yoe, String address, String refernecename, String referenceno, String technology, String note) {
        this.name = name;
        this.dob = dob;
        this.doj = doj;
        this.mobile = mobile;
        this.whatapp = whatapp;
        this.email = email;
        this.yoe = yoe;
        this.address = address;
        this.refernecename = refernecename;
        this.referenceno = referenceno;
        this.technology = technology;
        this.note = note;
    }

    public MyFacultyData(String name, String dob, String doj, String mobile, String whatapp, String email, String yoe, String address, String refernecename, String referenceno, String technology, String note, String id) {
        this.name = name;
        this.dob = dob;
        this.doj = doj;
        this.mobile = mobile;
        this.whatapp = whatapp;
        this.email = email;
        this.yoe = yoe;
        this.address = address;
        this.refernecename = refernecename;
        this.referenceno = referenceno;
        this.technology = technology;
        this.note = note;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWhatapp() {
        return whatapp;
    }

    public void setWhatapp(String whatapp) {
        this.whatapp = whatapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYoe() {
        return yoe;
    }

    public void setYoe(String yoe) {
        this.yoe = yoe;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRefernecename() {
        return refernecename;
    }

    public void setRefernecename(String refernecename) {
        this.refernecename = refernecename;
    }

    public String getReferenceno() {
        return referenceno;
    }

    public void setReferenceno(String referenceno) {
        this.referenceno = referenceno;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
