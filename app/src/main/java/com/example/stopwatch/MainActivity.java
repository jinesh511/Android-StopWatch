package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnStart,btnFinish,btnResume,btnPause;
    Chronometer chronometer;
    Animation rotate;
    ImageView imageView;
    long pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart=findViewById(R.id.btnStart);
        btnFinish=findViewById(R.id.btnFinish);
        chronometer=findViewById(R.id.Chronometer);
        imageView=findViewById(R.id.imageView);
        btnResume=findViewById(R.id.btnResume);
        btnPause=findViewById(R.id.btnPause);


        rotate = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotation);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(rotate);
                chronometer.setFormat("%s");
                chronometer.setBase(SystemClock.elapsedRealtime()-pause);
                chronometer.start();
                btnStart.setVisibility(View.INVISIBLE);
                btnPause.setVisibility(View.VISIBLE);
                btnResume.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.VISIBLE);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                pause= 0;
                rotate.cancel();
                imageView.setAnimation(rotate);
                Toast.makeText(MainActivity.this,"Thank You!",Toast.LENGTH_LONG).show();
                btnStart.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.INVISIBLE);
                btnFinish.setVisibility(View.INVISIBLE);

            }
        });

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(rotate);
                chronometer.setFormat("%s");
                chronometer.setBase(SystemClock.elapsedRealtime()-pause);
                chronometer.start();
                btnStart.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.VISIBLE);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                pause= SystemClock.elapsedRealtime()-chronometer.getBase();
                rotate.cancel();
                imageView.setAnimation(rotate);
                btnStart.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.VISIBLE);
            }
        });
    }
}
