package com.example.news.quiz;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = { Quiz.class}, version=1,exportSchema = false)
public abstract class QuizDataBase extends RoomDatabase {


    public abstract QuizDao dao();
}