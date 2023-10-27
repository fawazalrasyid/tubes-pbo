package com.fawazalrasyid.tbs.Models;

public class Question {
    private String soal,a,b,c,d, e, jawaban;
    private String jumlahsoal, name;

    public Question(String soal, String a, String b, String c, String d, String e, String jawaban, String jumlahsoal, String name) {
        this.soal = soal;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.jawaban = jawaban;
        this.jumlahsoal = jumlahsoal;
        this.name = name;
    }

    public Question(){}

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

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

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getJumlahsoal() {
        return jumlahsoal;
    }

    public void setJumlahsoal(String jumlahsoal) {
        this.jumlahsoal = jumlahsoal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}