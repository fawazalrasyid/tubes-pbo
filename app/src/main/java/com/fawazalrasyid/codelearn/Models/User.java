package com.fawazalrasyid.codelearn.Models;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class User {
    private String id;
    private String name;
    private String username;
    private String password;
    private Course[] enrolledCourses;

    public User(String id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Course[] getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(Course[] enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

}
