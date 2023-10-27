package com.fawazalrasyid.tbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

public class InfoActivity extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.4F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final LinearLayout author = (LinearLayout) findViewById(R.id.author);
        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                author.startAnimation(buttonClick);
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://fawazalrasyid.cf/")));
            }
        });

        Button authorr = (Button) findViewById(R.id.authorr);
        authorr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://fawazalrasyid.cf/")));
            }
        });


        final LinearLayout pustaka = (LinearLayout) findViewById(R.id.pustaka);
        pustaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pustaka.startAnimation(buttonClick);
                Intent i = new Intent(InfoActivity.this, PustakaActivity.class);
                startActivity(i);
            }
        });

        Button pustakaa = (Button) findViewById(R.id.pustakaa);
        pustakaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InfoActivity.this, PustakaActivity.class);
                startActivity(i);
            }
        });

    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
