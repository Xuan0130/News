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


public class QuizActivity extends AppCompatActivity {
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
        if (list.size()>0) {
            title.setText("Q"+(position+1)+"   "+list.get(position).getTitle());
            answerA.setText("A:"+list.get(position).getA());
            answerB.setText("B:"+list.get(position).getB());
            answerC.setText("C:"+list.get(position).getC());
            answerD.setText("D:"+list.get(position).getD());
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position ==list.size()-1){
                    String answer = getAnsAnswer();
                    if (TextUtils.isEmpty(answer)){
                        Toast.makeText(QuizActivity.this,"Choose an answer and submit",Toast.LENGTH_SHORT).show();
                    }else {
                        if (answer.equals(list.get(position).getAnswer())){
                            correctNumber = correctNumber +1;
                        }else{
                            Quiz quiz = list.get(position);
                            QuizDataBase quizDataBase  = Room.databaseBuilder(QuizActivity.this, QuizDataBase.class, "quiz.db")
                                    .allowMainThreadQueries()
                                    .build();
                            quizDataBase.dao().add(quiz);
                        }
                        new AlertDialog.Builder(QuizActivity.this)
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
                        Toast.makeText(QuizActivity.this,"Choose an answer and submit",Toast.LENGTH_SHORT).show();
                    }else {
                        if (answer.equals(list.get(position).getAnswer())){
                            correctNumber = correctNumber +1;
                        }else{
                                Quiz quiz = list.get(position);
                                QuizDataBase quizDataBase  = Room.databaseBuilder(QuizActivity.this, QuizDataBase.class, "quiz.db")
                                        .allowMainThreadQueries()
                                        .build();
                                quizDataBase.dao().add(quiz);
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
        list.add(new Quiz("A large increase in the number of fast-food restaurants in a community is most likely to result in: ",
                "Lower prices and higher quality","Lower prices and lower quality","Higher prices and higher quality."
        ,"Don't Know","A"));

        list.add(new Quiz("A person who starts a business to produce a new product in the marketplace is known as:",
                "A manager.",
                "A bureaucrat.",
                "An entrepreneur.",
                "Don't Know.",
                "C"));
        list.add(new Quiz("Incorrect. The correct answer is C.\n" +
                "An increase from 5% to 8% in the interest rates charged by banks would most likely encourage:",
                "Businesses to invest.",
                "People to purchase housing.",
                "People to save money.",
                "Don't Know.",
                "C"));
        list.add(new Quiz("For most people, the largest portion of their personal income comes from:",
                "Wages and salaries from their jobs.",
                "Interest from stocks and bonds they own.",
                "Rent paid to them from property they own.",
                "Don't Know.",
                "A"));

        list.add(new Quiz(" If the real gross domestic product of the United States has increased, but the production of goods has remained the same, then the production of services has: ",
                "Increased.",
                "Decreased.",
                "Remained the same.",
                "Don't Know.",
                "A"));
        list.add(new Quiz("If the price of beef doubled and the price of poultry stayed the same, people would most likely buy:",
                "More poultry and less beef.",
                "Less poultry and more beef.",
                "The same amount of poultry and beef.",
                "Don't Know.",
                "A"));
        list.add(new Quiz("If the United States stopped importing automobiles from Country X, who would be most likely to benefit? ",
                "Automobile manufacturers in Country X",
                "Consumers in the United States",
                "Automobile manufacturers in the United States",
                "Don't Know.",
                "C"));
        list.add(new Quiz("If your city government sets a maximum amount landlord can charge in rent, what is the most likely result?",
                "There will be more apartments available than people want to rent.",
                "There will be fewer apartments available than people want to rent.",
                "The number of apartments available will be equal to the number of people that want to rent.",
                "Don't Know.",
                "B"));
        list.add(new Quiz("In the United States, who determines what goods and services should be produced? ",
                "Producers and government",
                "Consumers and government",
                "Producers, consumers, and government",
                "Don't Know.",
                "C"));
        list.add(new Quiz("Mexico grows fruits and vegetables and Argentina produces beef. If Mexico voluntarily trades fruits and vegetables in exchange for Argentinean beef,",
                "Both Mexico and Argentina benefit from the trade.",
                "Both Mexico and Argentina lose from the trade.",
                "Mexico benefits and Argentina loses from the trade.",
                "Don't Know.",
                "A"));
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
