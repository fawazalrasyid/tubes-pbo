package com.fawazalrasyid.tbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fawazalrasyid.tbs.Models.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LatihanActivity extends AppCompatActivity {

    private TextView QuestionView;
    private Button ChoiceA;
    private Button ChoiceB;
    private Button ChoiceC;
    private Button ChoiceD;
    private Button ChoiceE;

    DatabaseReference reference;
    ProgressDialog progressDialog;

    int soal = 0;
    int Benar = 0;
    int Salah = 0;
    int slh = 0;
    int Score = 0;
    int jumlah = 1;

    String id;
    String idlatihan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_latihan);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        idlatihan = intent.getStringExtra("idlatihan");

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        QuestionView = (TextView)findViewById(R.id.questiontext);
        ChoiceA = (Button)findViewById(R.id.choiceA);
        ChoiceB = (Button)findViewById(R.id.choiceB);
        ChoiceC = (Button)findViewById(R.id.choiceC);
        ChoiceD = (Button)findViewById(R.id.choiceD);
        ChoiceE = (Button)findViewById(R.id.choiceE);

        progressDialog = ProgressDialog.show(LatihanActivity.this,
                null,
                "Memuat...",
                true,
                false);

        reference = FirebaseDatabase.getInstance().getReference().child("TbS").child(String.valueOf(id)).child("latihan").child(String.valueOf(idlatihan));
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Question question = dataSnapshot.getValue(Question.class);

                TextView name = (TextView)findViewById(R.id.name);
                name.setText(question.getName());
                int jml = Integer.parseInt((question.getJumlahsoal()));
                jumlah = jml;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        updateQuestions();

    }

    private void updateQuestions(){

        soal++;
        if (soal > jumlah){
            Intent i = new Intent(LatihanActivity.this, ScoreActivity.class);
            slh = Salah * 2;
            Score = 100/jumlah*Benar-slh;
            i.putExtra("score", Score);
            startActivity(i);
            finish();
        }

        else {
            reference = FirebaseDatabase.getInstance().getReference().child("TbS").child(String.valueOf(id)).child("latihan").child(String.valueOf(idlatihan)).child(String.valueOf(soal));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    final Question question = dataSnapshot.getValue(Question.class);

                    QuestionView.setText(question.getSoal());
                    ChoiceA.setText(question.getA());
                    ChoiceB.setText(question.getB());
                    ChoiceC.setText(question.getC());
                    ChoiceD.setText(question.getD());
                    ChoiceE.setText(question.getE());


                    ChoiceA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(ChoiceA.getText().toString().equals(question.getJawaban())) {
                                Benar++;
                                Toast.makeText(LatihanActivity.this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
                                ChoiceA.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btncorrect));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ChoiceA.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
                                        updateQuestions();
                                    }
                                },2000);
                            }

                            else {
                                Salah++;
                                Toast.makeText(LatihanActivity.this, "Jawaban Kurang Tepat", Toast.LENGTH_SHORT).show();
                                ChoiceA.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnwrong));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ChoiceA.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
                                    }
                                },2000);
                            }
                        }
                    });


                    ChoiceB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(ChoiceB.getText().toString().equals(question.getJawaban())) {
                                Benar++;
                                Toast.makeText(LatihanActivity.this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
                                ChoiceB.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btncorrect));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ChoiceB.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
                                        updateQuestions();
                                    }
                                },2000);
                            }

                            else {
                                Salah++;
                                Toast.makeText(LatihanActivity.this, "Jawaban Kurang Tepat", Toast.LENGTH_SHORT).show();
                                ChoiceB.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnwrong));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ChoiceB.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
                                    }
                                },2000);
                            }
                        }
                    });


                    ChoiceC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(ChoiceC.getText().toString().equals(question.getJawaban())) {
                                Benar++;
                                Toast.makeText(LatihanActivity.this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
                                ChoiceC.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btncorrect));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ChoiceC.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
                                        updateQuestions();
                                    }
                                },2000);
                            }

                            else {
                                Salah++;
                                Toast.makeText(LatihanActivity.this, "Jawaban Kurang Tepat", Toast.LENGTH_SHORT).show();
                                ChoiceC.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnwrong));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ChoiceC.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
                                    }
                                },2000);
                            }
                        }
                    });


                    ChoiceD.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(ChoiceD.getText().toString().equals(question.getJawaban())) {
                                Benar++;
                                Toast.makeText(LatihanActivity.this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
                                ChoiceD.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btncorrect));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ChoiceD.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
                                        updateQuestions();
                                    }
                                },2000);
                            }

                            else {
                                Salah++;
                                Toast.makeText(LatihanActivity.this, "Jawaban Kurang Tepat", Toast.LENGTH_SHORT).show();
                                ChoiceD.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnwrong));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ChoiceD.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
                                    }
                                },2000);
                            }
                        }
                    });

                    ChoiceE.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(ChoiceE.getText().toString().equals(question.getJawaban())) {
                                Benar++;
                                Toast.makeText(LatihanActivity.this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
                                ChoiceE.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btncorrect));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ChoiceE.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
                                        updateQuestions();
                                    }
                                },2000);
                            }

                            else {
                                Salah++;
                                Toast.makeText(LatihanActivity.this, "Jawaban Kurang Tepat", Toast.LENGTH_SHORT).show();
                                ChoiceE.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnwrong));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ChoiceE.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
                                    }
                                },2000);
                            }
                        }
                    });

                    progressDialog.dismiss();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    progressDialog.dismiss();

                }

            });
        }

    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}