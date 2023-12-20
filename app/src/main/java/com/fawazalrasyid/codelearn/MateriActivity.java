package com.fawazalrasyid.codelearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fawazalrasyid.codelearn.Adapter.ModuleAdapter;
import com.fawazalrasyid.codelearn.Models.Database;
import com.fawazalrasyid.codelearn.Models.Module;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MateriActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    DatabaseReference moduleRef;
    Query module;
    String courseId, moduleId, moduleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
        moduleId = intent.getStringExtra("moduleId");
        moduleName = intent.getStringExtra("moduleName");

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView toolbar = (TextView)findViewById(R.id.module_name);
        toolbar.setText(moduleName);

        moduleRef = FirebaseDatabase.getInstance().getReference().child("courses").child(courseId).child("modules").child(moduleId);
        module = new Database().query(moduleRef);
        module.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Module module = dataSnapshot.getValue(Module.class);

                TextView courseContent = (TextView)findViewById(R.id.module_content);
                courseContent.setText(Html.fromHtml(module.getContent()));

                Button btnQuiz = (Button)findViewById(R.id.btn_quiz);
                btnQuiz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MateriActivity.this, LatihanActivity.class);
                        i.putExtra("moduleId", moduleId);
                        i.putExtra("courseId", courseId);
                        startActivity(i);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                finish();
            }

        });

    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
