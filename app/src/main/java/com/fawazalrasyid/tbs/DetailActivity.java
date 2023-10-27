package com.fawazalrasyid.tbs;

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
import com.fawazalrasyid.tbs.Adapter.DetailAdapter;
import com.fawazalrasyid.tbs.Models.App;
import com.fawazalrasyid.tbs.Models.Card;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    DatabaseReference reference;

    private FirebaseRecyclerAdapter<Card, DetailAdapter> mAdapter;
    private RecyclerView mRecycler;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");


        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button latihan = (Button)findViewById(R.id.latihan);
        latihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this, LatihanActivity.class);
                i.putExtra("id", id);
                i.putExtra("idlatihan", "latihan");
                startActivity(i);
            }
        });


        reference = FirebaseDatabase.getInstance().getReference().child("TbS").child(String.valueOf(id));
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final App app = dataSnapshot.getValue(App.class);
                ImageView bg = (ImageView)findViewById(R.id.bg);
                Picasso.get()
                        .load(app.getBg())
                        .into(bg);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRecycler = findViewById(R.id.rv_detail);
        GridLayoutManager gridLayout= new GridLayoutManager(DetailActivity.this, 1 );
        mRecycler.setLayoutManager(gridLayout);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Card>()
                .setQuery(query, Card.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Card, DetailAdapter>(options) {

            @Override
            public DetailAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new DetailAdapter(inflater.inflate(R.layout.item_menu_detail, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull DetailAdapter holder, int position, @NonNull final Card model) {
                holder.bindToCard(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DetailActivity.this, MateriActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("cardid", model.id);
                        intent.putExtra("name", model.cardname);
                        startActivity(intent);
                    }
                });
            }
        };

        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null){
            mAdapter.startListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase){
        Query query = mDatabase.child("TbS").child(String.valueOf(id)).child("materi");
        query.keepSynced(true);
        return query;
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
