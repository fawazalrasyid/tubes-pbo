package com.fawazalrasyid.tbs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fawazalrasyid.tbs.Models.App;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PustakaActivity extends AppCompatActivity {

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pustaka);

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("Home");
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final App app = dataSnapshot.getValue(App.class);

                TextView pustaka = (TextView)findViewById(R.id.pustaka);
                pustaka.setText(app.getPustaka());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
