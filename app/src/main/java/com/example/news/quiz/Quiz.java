package com.example.news.quiz;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Quiz {

    @NonNull
    @PrimaryKey
    private String title;
    private String A;
    private String B;
    private String C;
    private String D;
    private String Answer;

    public Quiz(String title, String a, String b, String c, String d, String Answer) {
        this.title = title;
        this.A = a;
        this.B = b;
        this.C = c;
        this.D = d;
        this.Answer = Answer;
    }



    public Quiz() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        this.A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        this.B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        this.C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        this.D = d;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        this.Answer = answer;
    }
}
