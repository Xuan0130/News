package com.example.news.quiz;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Quiz... quizzes);

    @Query("SELECT * FROM Quiz")
    List<Quiz> queryList();

    @Query("DELETE  FROM Quiz where title=:title")
    void remove(String title);
}
