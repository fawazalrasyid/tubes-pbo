package com.fawazalrasyid.codelearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fawazalrasyid.codelearn.Adapter.CourseAdapter;
import com.fawazalrasyid.codelearn.Models.Course;
import com.fawazalrasyid.codelearn.Models.Database;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    DatabaseReference courseRef;
    Query courses;
    private FirebaseRecyclerAdapter<Course, CourseAdapter> courseAdapter;
    private RecyclerView courseRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        courseRef = FirebaseDatabase.getInstance().getReference().child("courses");
        courses = new Database().query(courseRef);

        courseRecycler = findViewById(R.id.rv_course);
        GridLayoutManager gridLayout = new GridLayoutManager(MainActivity.this, 2 );
        courseRecycler.setLayoutManager(gridLayout);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Course>()
                .setQuery(courses, Course.class)
                .build();

        courseAdapter = new FirebaseRecyclerAdapter<Course, CourseAdapter>(options) {
            @Override
            public CourseAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new CourseAdapter(inflater.inflate(R.layout.item_course, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull CourseAdapter holder, int position, @NonNull final Course model) {
                holder.bindToCard(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("courseId", model.getId());
                        intent.putExtra("courseName", model.getName());
                        intent.putExtra("backgroundImage", model.getBackgroundImage());
                        startActivity(intent);
                    }
                });
            }
        };

        courseAdapter.notifyDataSetChanged();
        courseRecycler.setAdapter(courseAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (courseAdapter!= null){
            courseAdapter.startListening();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
