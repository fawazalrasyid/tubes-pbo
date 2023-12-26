package com.fawazalrasyid.codelearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fawazalrasyid.codelearn.Models.Database;
import com.fawazalrasyid.codelearn.Models.MCQuestion;
import com.fawazalrasyid.codelearn.Models.PostTest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PostTestActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private Button choiceA, choiceB, choiceC, choiceD;
    DatabaseReference quizRef, questionRef;
    Query quiz, question;
    ProgressDialog progressDialog;

    int questionIndex = 0;
    int correctCount = 0;
    int totalScore = 0;
    int totalQuestion = 1;

    String courseId, moduleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_post_test);

        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
        moduleId = intent.getStringExtra("moduleId");

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvQuestion = (TextView) findViewById(R.id.question);
        choiceA = (Button) findViewById(R.id.choiceA);
        choiceB = (Button) findViewById(R.id.choiceB);
        choiceC = (Button) findViewById(R.id.choiceC);
        choiceD = (Button) findViewById(R.id.choiceD);

        progressDialog = ProgressDialog.show(PostTestActivity.this,
                null,
                "Memuat...",
                true,
                false);

        quizRef = FirebaseDatabase.getInstance().getReference().child("courses").child(courseId).child("modules").child(moduleId).child("quiz");
        quiz = new Database().query(quizRef);
        quiz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final PostTest postTest = dataSnapshot.getValue(PostTest.class);

                TextView tvName = (TextView) findViewById(R.id.quiz_name);
                tvName.setText(postTest.getName());
                totalQuestion = postTest.getTotalQuestion();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        updateQuestions();
    }

    private void updateQuestions() {
        choiceA.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
        choiceB.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
        choiceC.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));
        choiceD.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnanswer));

        questionIndex++;

        if (questionIndex > totalQuestion) {
            Intent i = new Intent(PostTestActivity.this, ScoreActivity.class);
            totalScore = 100 / totalQuestion * correctCount;
            i.putExtra("score", totalScore);
            startActivity(i);
            finish();
        } else {
            questionRef = FirebaseDatabase.getInstance().getReference().child("courses").child(courseId).child("modules").child(moduleId).child("quiz").child(String.valueOf(questionIndex));
            question = new Database().query(questionRef);
            question.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    final MCQuestion question = dataSnapshot.getValue(MCQuestion.class);

                    tvQuestion.setText(Html.fromHtml(question.getQuestion()));
                    choiceA.setText(question.getA());
                    choiceB.setText(question.getB());
                    choiceC.setText(question.getC());
                    choiceD.setText(question.getD());

                    choiceA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkAnswer(question, choiceA);
                        }
                    });

                    choiceB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkAnswer(question, choiceB);
                        }
                    });

                    choiceC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkAnswer(question, choiceC);
                        }
                    });

                    choiceD.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkAnswer(question, choiceD);
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

    public void checkAnswer(MCQuestion question, Button selectedChoice) {
        if (question.isCorrectAnswer(selectedChoice.getText().toString())) {
            correctCount++;
            Toast.makeText(PostTestActivity.this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
            selectedChoice.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btncorrect));
        } else {
            Toast.makeText(PostTestActivity.this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
            selectedChoice.setBackgroundDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.btnwrong));
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateQuestions();
            }
        }, 500);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}