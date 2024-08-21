package com.example.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class recylerAdepter extends RecyclerView.Adapter<recylerAdepter.ViewHolder> {
    Context context;
    ArrayList<data> arry=new ArrayList<>();

    private final int[] correctAnswers;
    int[] scores={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int[] check={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    recylerAdepter(Context context,ArrayList<data> arry,int[] correctAnswers){
        this.context=context;
        this.arry=arry;
        this.correctAnswers=correctAnswers;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View vi= LayoutInflater.from(context).inflate(R.layout.question,parent,false);
            ViewHolder v= new ViewHolder(vi);
            return v;

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //set question
            holder.question.setText(" " + (position + 1) + ". " + arry.get(position).question);
            holder.radioGroup.removeAllViews();
            //set all radio button text
            RadioButton radioButton1 = new RadioButton(context);
            radioButton1.setText(arry.get(position).answer1);
            holder.radioGroup.addView(radioButton1);

            RadioButton radioButton2 = new RadioButton(context);
            radioButton2.setText(arry.get(position).answer2);
            holder.radioGroup.addView(radioButton2);

            RadioButton radioButton3 = new RadioButton(context);
            radioButton3.setText(arry.get(position).answer3);
            holder.radioGroup.addView(radioButton3);

            RadioButton radioButton4 = new RadioButton(context);
            radioButton4.setText(arry.get(position).answer4);
            holder.radioGroup.addView(radioButton4);
            //it will set check in radio button when recycler is scroll
            if (check[position] == 1) {
                radioButton1.setChecked(true);
            } else if (check[position] == 2) {
                radioButton2.setChecked(true);
            } else if (check[position] == 3) {
                radioButton3.setChecked(true);
            } else if (check[position] == 4) {
                radioButton4.setChecked(true);
            }

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            int selectedId= group.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRadioButton = holder.radioGroup.findViewById(selectedId);
                int answerIndex = holder.radioGroup.indexOfChild(selectedRadioButton);
                int p= holder.getAdapterPosition();
                check[p]=answerIndex+1;
                if (answerIndex == correctAnswers[p]){
                    scores[p]=1;
                }
                else{
                   scores[p]=0;

                }
            }
        }
    });


    }

    @Override
    public int getItemCount() {
        return arry.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        RadioGroup radioGroup;

        public ViewHolder(View v){
            super(v);
            question=v.findViewById(R.id.txt1);
            radioGroup=v.findViewById(R.id.answersGroup);
        }
    }
    public  int[] result (){
        return scores;
    }

public void uncheck(){
        for(int i=0;i<check.length;i++){
            check[i]=0;
            scores[i]=0;
        }
}
}