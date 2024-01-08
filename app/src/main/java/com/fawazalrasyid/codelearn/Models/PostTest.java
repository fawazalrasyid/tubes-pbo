package com.fawazalrasyid.codelearn.Models;

public class PostTest {
    private String name;

    private String type;

    private MCQuestion mcQuestion[];
    private EssayQuestion essayQuestion[];
    private int totalQuestion;

    PostTest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MCQuestion[] getMcQuestion() {
        return mcQuestion;
    }

    public void setMcQuestion(MCQuestion[] mcQuestion) {
        this.mcQuestion = mcQuestion;
    }

    public EssayQuestion[] getEssayQuestion() {
        return essayQuestion;
    }

    public void setEssayQuestion(EssayQuestion[] essayQuestion) {
        this.essayQuestion = essayQuestion;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }
}