package com.fawazalrasyid.codelearn.Models;

public class Quiz {
    private String name;
    private int totalQuestion;

    Quiz() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }
}