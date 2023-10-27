package com.fawazalrasyid.tbs.Models;

public class App {

    String bg, pustaka;

    public App(String bg, String pustaka) {
        this.bg = bg;
        this.pustaka = pustaka;
    }

    public App() {}

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getPustaka() {
        return pustaka;
    }

    public void setPustaka(String pustaka) {
        this.pustaka = pustaka;
    }
}
