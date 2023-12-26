package com.fawazalrasyid.codelearn.Models;

public class EssayQuestion extends Question {
    private String correctAnswer;

    EssayQuestion() {}

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}