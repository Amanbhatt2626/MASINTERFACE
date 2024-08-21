package com.example.stopwatch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;

    private Button startButton;

    private Button stopButton;
    private long stop;
    private Button resetButton;
  int flag=1,temp=1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

      //call all xml item
        chronometer = findViewById(R.id.Chronometer);

        startButton = findViewById(R.id.start);

        stopButton = findViewById(R.id.stop);

        resetButton = findViewById(R.id.reset);


        startButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                    //it will work when temp is 1 otherwise it will do nothing for start from 0
                if(temp==1) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    temp=0;
                }
            }
        });


        stopButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //it will work when temp is 0 because after start we can stop it
                if(temp==0){
                    //flag is 1 when we stop and make flag 0 and change text resume
                if(flag==1){
                    stop= chronometer.getBase()-SystemClock.elapsedRealtime();
                chronometer.stop();
                flag=0;
                    stopButton.setText("RESUME");
                }
                //when flag is 0 then we strat chronometer and start from where we leave
                else{

                    stopButton.setText("STOP");
                    chronometer.setBase(SystemClock.elapsedRealtime()+stop);
                    chronometer.start();
                    flag=1;
                }

            }}
        });


        resetButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //in reset we stop chronometer and make text 0
                temp=1;
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                stop=SystemClock.elapsedRealtime();
                stopButton.setText("STOP");
                flag=1;

            }
        });

    }



    }
