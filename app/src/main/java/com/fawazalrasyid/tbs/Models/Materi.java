package com.fawazalrasyid.tbs.Models;

public class Materi {

    String materi, urlvideo, latihan, more;

    public Materi(String materi, String urlvideo, String latihan, String more) {
        this.materi = materi;
        this.urlvideo = urlvideo;
        this.latihan = latihan;
        this.more = more;
    }

    public Materi() {
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getUrlvideo() {
        return urlvideo;
    }

    public void setUrlvideo(String urlvideo) {
        this.urlvideo = urlvideo;
    }

    public String getLatihan() {
        return latihan;
    }

    public void setLatihan(String latihan) {
        this.latihan = latihan;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }
}
