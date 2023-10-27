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
import android.widget.ImageView;

import com.fawazalrasyid.tbs.Adapter.CardAdapter;
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

public class MainActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    DatabaseReference reference;

    private FirebaseRecyclerAdapter<Card, CardAdapter> mAdapter;
    private FirebaseRecyclerAdapter<Card, CardAdapter> mAdapterSub;
    private RecyclerView mRecycler, mRecyclerSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        reference = FirebaseDatabase.getInstance().getReference().child("Home");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final App app = dataSnapshot.getValue(App.class);

                ImageView bg = (ImageView)findViewById(R.id.bgHome);
                Picasso.get()
                        .load(app.getBg())
                        .into(bg);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRecycler = findViewById(R.id.rv_home);
        GridLayoutManager gridLayout= new GridLayoutManager(MainActivity.this, 2 );
        mRecycler.setLayoutManager(gridLayout);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Card>()
                .setQuery(query, Card.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Card, CardAdapter>(options) {

            @Override
            public CardAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new CardAdapter(inflater.inflate(R.layout.item_menu, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull CardAdapter holder, int position, @NonNull final Card model) {
                holder.bindToCard(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("id", model.id);
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
        Query query = mDatabase.child("TbS");
        query.keepSynced(true);
        return query;
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
