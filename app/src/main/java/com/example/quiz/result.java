package com.example.quiz;

import static com.example.quiz.R.drawable.*;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class result extends AppCompatActivity {
TextView result;
int score,total,per;
LottieAnimationView laView;
    LinearLayout linearLayout;
    @SuppressLint({"MissingInflatedId", "ResourceAsColor", "UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        laView=findViewById(R.id.lottie);

        Intent intent=getIntent();
      score=(intent.getIntExtra("Score",0));
       total=intent.getIntExtra("total",0);
        linearLayout =findViewById(R.id.back);
        result=findViewById(R.id.result);
         per=(int)(((float)score/(float)total)*100);


       if(per<=33){
            laView.setAnimation(R.raw.fail);
           linearLayout.setBackground(getDrawable(R.drawable.red));
       } else if (per< 60&& per>33) {
           laView.setAnimation(R.raw.congo);
           linearLayout.setBackground(getDrawable(R.drawable.yellow));
       }
       else{
           laView.setAnimation(R.raw.pass);
           linearLayout.setBackground(getDrawable(R.drawable.green));
       }
        result.setText("You Got ''"+score+"''\n\nOut of ''"+ total+"''");
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder=new AlertDialog.Builder(result.this).
                        setTitle("EXIT OR RE-TEST").setMessage("Do You Want To Exit Or Re-test")
                        .setIcon(baseline_exit_to_app_24).setPositiveButton("Re-Test",
                                new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(result.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                builder.show();
            }
        },5000);
    }
}