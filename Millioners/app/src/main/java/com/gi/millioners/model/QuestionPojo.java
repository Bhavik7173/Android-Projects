package com.gi.millioners.model;


import android.os.Parcel;
import android.os.Parcelable;

public class QuestionPojo implements Parcelable {

    String question_id;
    String q_question;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String correct_answer;
    String price;

    String user_answer = "Z";

    String nonAttempt = "NON";

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNonAttempt() {
        return nonAttempt;
    }

    public void setNonAttempt(String nonAttempt) {
        this.nonAttempt = nonAttempt;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getUser_answer() {
        return user_answer;
    }

    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
    }

    public String getQ_question() {
        return q_question;
    }

    public void setQ_question(String q_question) {
        this.q_question = q_question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public QuestionPojo(String question_id, String q_question, String optionA, String optionB, String optionC, String optionD, String correct_answer, String price) {
        this.question_id = question_id;
        this.q_question = q_question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correct_answer = correct_answer;
        this.price = price;
    }

    public QuestionPojo(Parcel in) {
        // TODO Auto-generated constructor stub

        String[] data = new String[8];

        in.readStringArray(data);
        this.question_id = data[0];
        this.q_question = data[1];
        this.optionA = data[2];
        this.optionB = data[3];
        this.optionC = data[4];
        this.optionD = data[5];
        this.correct_answer = data[6];
        this.price = data[7];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeStringArray(new String[]{
                this.question_id,
                this.q_question,
                this.optionA,
                this.optionB,
                this.optionC,
                this.optionD,
                this.correct_answer,
                this.price
        });
    }

    public static Creator<QuestionPojo> CREATOR = new Creator<QuestionPojo>() {
        @Override
        public QuestionPojo createFromParcel(Parcel source) {

            return new QuestionPojo(source);
        }

        @Override
        public QuestionPojo[] newArray(int size) {
            return new QuestionPojo[size];
        }
    };
}
