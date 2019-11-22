package com.example.news;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.news.quiz.Quiz;
import com.example.news.quiz.QuizDataBase;

import java.util.ArrayList;
import java.util.List;


public class ReviewActivity extends AppCompatActivity {
    private List<Quiz> list = new ArrayList<>();
    private TextView title;
    private  int position;
    private RadioButton answerA;
    private RadioButton answerB;
    private RadioButton answerC;
    private RadioButton answerD;
    private int correctNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().setTitle("Test");
        title  = findViewById(R.id.title);
        answerA = findViewById(R.id.answera);
        answerB = findViewById(R.id.answerb);
        answerC = findViewById(R.id.answerc);
        answerD = findViewById(R.id.answerd);
        Button submit =  findViewById(R.id.submit);
        setList();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position ==list.size()-1){
                    String answer = getAnsAnswer();
                    if (TextUtils.isEmpty(answer)){
                        Toast.makeText(ReviewActivity.this,"Choose an answer and submit",Toast.LENGTH_SHORT).show();
                    }else {
                        if (answer.equals(list.get(position).getAnswer())){
                            correctNumber = correctNumber +1;
                            Quiz quiz = list.get(position);
                            QuizDataBase quizDataBase  = Room.databaseBuilder(ReviewActivity.this, QuizDataBase.class, "quiz.db")
                                    .allowMainThreadQueries()
                                    .build();
                            quizDataBase.dao().remove(quiz.getTitle());
                        }
                        new AlertDialog.Builder(ReviewActivity.this)
                                .setTitle("Score")
                                .setMessage("You correctNumber "+ correctNumber +" correctNumber")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        finish();
                                    }
                                })
                                .create().show();
                    }

                }else{
                    String answer = getAnsAnswer();
                    if (TextUtils.isEmpty(answer)){
                        Toast.makeText(ReviewActivity.this,"Choose an answer and submit",Toast.LENGTH_SHORT).show();
                    }else {
                        if (answer.equals(list.get(position).getAnswer())){
                            correctNumber = correctNumber +1;
                            Quiz quiz = list.get(position);
                            QuizDataBase quizDataBase  = Room.databaseBuilder(ReviewActivity.this, QuizDataBase.class, "quiz.db")
                                    .allowMainThreadQueries()
                                    .build();
                            quizDataBase.dao().remove(quiz.getTitle());
                        }
                        position=position+1;
                        if (list.size()>0) {
                            title.setText("Q"+(position+1)+"   "+list.get(position).getTitle());
                            answerA.setText("A:"+list.get(position).getA());
                            answerB.setText("B:"+list.get(position).getB());
                            answerC.setText("C:"+list.get(position).getC());
                            answerD.setText("D:"+list.get(position).getD());
                        }
                    }
                }
            }


        });
    }

    private void setList() {

        QuizDataBase quizDataBase  = Room.databaseBuilder(ReviewActivity.this, QuizDataBase.class, "quiz.db")
                .allowMainThreadQueries()
                .build();
        list = quizDataBase.dao().queryList();
        if (list.size()>0) {
            title.setText("Q"+(position+1)+"   "+list.get(position).getTitle());
            answerA.setText("A:"+list.get(position).getA());
            answerB.setText("B:"+list.get(position).getB());
            answerC.setText("C:"+list.get(position).getC());
            answerD.setText("D:"+list.get(position).getD());
        }
    }



    private String getAnsAnswer(){
        if (answerA.isChecked()){
            return "A";
        }else if (answerB.isChecked()){
            return "B";
        }else if (answerC.isChecked()){
            return "C";
        }else if (answerD.isChecked()){
            return "D";
        }
        return "";
    }





}
