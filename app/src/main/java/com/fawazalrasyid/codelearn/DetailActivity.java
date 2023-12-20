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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fawazalrasyid.codelearn.Adapter.ModuleAdapter;
import com.fawazalrasyid.codelearn.Models.Course;
import com.fawazalrasyid.codelearn.Models.Database;
import com.fawazalrasyid.codelearn.Models.Module;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    DatabaseReference moduleRef;
    Query modules;
    private FirebaseRecyclerAdapter<Module, ModuleAdapter> moduleAdapter;
    private RecyclerView moduleRecycler;

    String courseId, courseName, backgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
        courseName = intent.getStringExtra("courseName");
        backgroundImage = intent.getStringExtra("backgroundImage");

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView bg = (ImageView)findViewById(R.id.bg);
        Picasso.get()
                .load(backgroundImage)
                .into(bg);

        TextView tvCourseName = (TextView)findViewById(R.id.course_name);
        tvCourseName.setText(courseName);

        moduleRef = FirebaseDatabase.getInstance().getReference().child("courses").child(courseId).child("modules");
        modules = new Database().query(moduleRef);

        moduleRecycler = findViewById(R.id.rv_module);
        GridLayoutManager gridLayout= new GridLayoutManager(DetailActivity.this, 1 );
        moduleRecycler.setLayoutManager(gridLayout);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Module>()
                .setQuery(modules, Module.class)
                .build();


        moduleAdapter = new FirebaseRecyclerAdapter<Module, ModuleAdapter>(options) {

            @Override
            public ModuleAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new ModuleAdapter(inflater.inflate(R.layout.item_module, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull ModuleAdapter holder, int position, @NonNull final Module model) {
                holder.bindToCard(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DetailActivity.this, MateriActivity.class);
                        intent.putExtra("courseId", courseId);
                        intent.putExtra("moduleId", model.getId());
                        intent.putExtra("moduleName", model.getName());
                        startActivity(intent);
                    }
                });
            }
        };

        moduleAdapter.notifyDataSetChanged();
        moduleRecycler.setAdapter(moduleAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (moduleAdapter != null){
            moduleAdapter.startListening();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
