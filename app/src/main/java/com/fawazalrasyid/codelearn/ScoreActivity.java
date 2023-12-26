package com.fawazalrasyid.codelearn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    int Score = 0;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent intent = getIntent();
        Score = intent.getIntExtra("score", 0);

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });

        TextView valueScore = (TextView)findViewById(R.id.valueScore);
        Spannable text = new SpannableString("Nilai kamu");
        Spannable value = new SpannableString(" " + Score);

        text.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        valueScore.setText(text);

        value.setSpan(new ForegroundColorSpan(Color.parseColor("#1ABC9C")), 0, value.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        valueScore.append(value);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
