package com.fawazalrasyid.tbs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fawazalrasyid.tbs.Models.Materi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MateriActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    DatabaseReference reference;
    String id, cardid, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        cardid = intent.getStringExtra("cardid");
        name = intent.getStringExtra("name");

        TextView toolbar = (TextView)findViewById(R.id.name);
        toolbar.setText(name);

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressDialog = ProgressDialog.show(MateriActivity.this,
                null,
                "Memuat...",
                true,
                false);

        reference = FirebaseDatabase.getInstance().getReference().child("TbS").child(String.valueOf(id)).child("materi").child(String.valueOf(cardid));
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Materi materi = dataSnapshot.getValue(Materi.class);

                TextView txtmateri = (TextView)findViewById(R.id.txtmateri);
                txtmateri.setText(materi.getMateri());

                //int jml = Integer.parseInt((materi.getJumlahsoal()));
                Button latihan = (Button)findViewById(R.id.latihan);
                latihan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MateriActivity.this, LatihanActivity.class);
                        i.putExtra("id", id);
                        i.putExtra("idlatihan", materi.getLatihan());
                        startActivity(i);
                    }
                });

                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });

    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
