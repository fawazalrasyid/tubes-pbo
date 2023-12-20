package com.fawazalrasyid.codelearn;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class CodeLearn extends Application {

    @Override
    public  void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }

}
