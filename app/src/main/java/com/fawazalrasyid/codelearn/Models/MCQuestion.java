package com.fawazalrasyid.codelearn.Models;

public class MCQuestion extends Question implements CheckAnswer {
    private String a, b, c, d, correctAnswer;

    public MCQuestion() {}

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


    @Override
    public boolean isCorrectAnswer(String answer) {
        if (answer.equals(getCorrectAnswer())) {
            return true;
        } else {
            return false;
        }
    }
}