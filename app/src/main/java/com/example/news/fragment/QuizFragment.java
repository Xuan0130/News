package com.example.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.news.QuizActivity;
import com.example.news.R;
import com.example.news.ReviewActivity;
import com.example.news.SearchNews;

public class QuizFragment extends Fragment {


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_quiz, container, false);
        LinearLayout llTest = root.findViewById(R.id.ll_test);
        LinearLayout llSearch = root.findViewById(R.id.ll_search);
        LinearLayout llReview = root.findViewById(R.id.ll_review);
        llTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                startActivity(intent);
            }
        });
        llSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchNews.class);
                startActivity(intent);
            }
        });
        llReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReviewActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}