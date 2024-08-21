package com.example.quiz;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button submitButton,clearButton;
    //question array
    private final String[] questions = {"What is contained in manifest.xml?",
                                    "Which of the following is not a state in the service lifecycle?",
                                    "As an android programmer, which version of Android should we use as a minimum development target?",
            "Which of the following is not a nickname of any android version?",
            "Which of the following is a dialog class in android?",
            "In which state the activity is, if it is not in focus, but still visible on the screen?",
            "Which of the following is the built-in database of Android?",
            "Which of the following android version is named Jelly Bean?",
            "Which of the following features are updated in Android 4.1(Jelly Bean)?",
            "Which of the following is the API level of Android version 5.0?",
            "Is it true that \"There can be only one running activity at a given time\"?",
            "Which of the following android library provides access to the database?",
            "In Android studio, which of the following callback is called when an activity starts interacting with the user?",
            "The sendStickybroadcast(intent) method in android is used to show that the intent is -",
            "Which of the following class in android displays information for a short period of time and disappears after some time?"
    };
        //option array
    private final String[][] answers = {
            {"Source code", "List of strings used in the app" , "Permission that the application requires" , "None of the above"},
            {"Destroyed", "Start" , "Paused","Running"},
            {"Version 1.2 or version 1.3" , "Version 1.0 or version 1.1" , "Version 1.6 or version 2.0" , "Version 2.3 or version 3.0"},
            {"Donut" , "Muffin" , "Honeycomb" , "Cupcake"},
            {"AlertDialog" , "DatePicker Dialog" , "ProgressDialog" , "All of the above"},
            {"Stopped state" , "Destroyed state" , "Paused state" , "Running state"},
            {"SQLite" , "MySQL" , "Oracle" , "None of the above"},
            {"3.1" ,"2.1" , "1.1" ,"4.1"},
            {"User Interface" , "Lock screen improvement" , "New clock application" ,"All of the above"},
            {"21" , "20" , "11" , "41"},
            {"True" , "False" ,"May be" ,"Can't say"},
            {"android.content" , "android.database" ,"android.api" ,"None of the above"},
            {"onDestroy" , "onCreate" , "onResume" , "onStop"},
            {"Optional" , "Prioritize" , "Sticky" ,"None of the above"},
            {"toast class" , "log class" , "maketest class" ,"None of the above" }
    };

    ArrayList<data> arry=new ArrayList<>();
    //correct answar arry
    private final int[] correctAnswers = {2,2,2,1,3,2,0,3,3,0,0,1,2,2,0};
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        submitButton = findViewById(R.id.btn);
        clearButton=findViewById(R.id.btnClear);
        //load question
        loadQuestion();
        //call recyle adepter class
        recylerAdepter rec=new recylerAdepter(MainActivity.this,arry,correctAnswers);

        recyclerView.setAdapter(rec);
        //uncheck if its check
        rec.uncheck();
        rec.notifyDataSetChanged();
        //submit button function
    submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pop alert for submit
               AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this).
                        setTitle("SUBMIT QUIZ").
                        setMessage("Do You Want to Submit?").
                setIcon(R.drawable.baseline_save_24).
                        setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int scores=0;
                                int  score[] =rec.result();
                                for(int i=0;i<score.length;i++){
                                    scores=scores+score[i];
                                }
                                Intent intent=new Intent(MainActivity.this,result.class);
                                intent.putExtra("Score",scores);
                                intent.putExtra("total",score.length);
                                startActivity(intent);
                                finish();

                            }
                      }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

            }
        });
    //clear button function
    clearButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //pop alert for clear
          AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this).
                    setTitle("CLEAR All SELECTED OPTION").
                    setMessage("Do You Want to Clear?").
                    setIcon(R.drawable.baseline_clear_all_24).
                    setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            rec.uncheck();
                            rec.notifyDataSetChanged();

                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            builder.show();
        }
    });
    }
    //load question function
    private void loadQuestion() {
        for(int i = 0; i < correctAnswers.length; i++ ){
           arry.add(new data(questions[i],answers[i][0],answers[i][1],answers[i][2],answers[i][3]));
        }

    }


}
