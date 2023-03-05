package com.gi.govardhaninquiry.admin.model;

import android.os.Parcel;
import android.os.Parcelable;

public class InquiryData implements Parcelable {
    String name, date, course, mobile, email, preferred, note, college;
    int id;

    public InquiryData(String iname, String idate, String icourse, String imobile, String iemail, String ipreferred, String inote, String icollege) {
        this.name = iname;
        this.mobile = imobile;
        this.course = icourse;
        this.date = idate;
        this.email = iemail;
        this.note = inote;
        this.college = icollege;
        this.preferred = ipreferred;
    }

    public InquiryData(Parcel parcel) {

        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.mobile = parcel.readString();
        this.course = parcel.readString();
        this.date = parcel.readString();
        this.email = parcel.readString();
        this.note = parcel.readString();
        this.college = parcel.readString();
        this.preferred = parcel.readString();

    }

    public InquiryData(int id, String name, String mobile, String course, String date, String email, String note, String college, String preferred) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.course = course;
        this.date = date;
        this.email = email;
        this.note = note;
        this.college = college;
        this.preferred = preferred;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(mobile);
        parcel.writeString(course);
        parcel.writeString(date);
        parcel.writeString(email);
        parcel.writeString(note);
        parcel.writeString(college);
        parcel.writeString(preferred);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<InquiryData>() {
        @Override
        public InquiryData createFromParcel(Parcel parcel) {
            return new InquiryData(parcel);
        }

        @Override
        public InquiryData[] newArray(int i) {
            return new InquiryData[i];
        }
    };
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreferred() {
        return preferred;
    }

    public void setPreferred(String preferred) {
        this.preferred = preferred;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", course='" + course + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", preferred='" + preferred + '\'' +
                ", note='" + note + '\'' +
                ", college='" + college + '\'' +
                ", id=" + id +
                '}';
    }
}
