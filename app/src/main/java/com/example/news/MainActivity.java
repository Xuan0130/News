package com.example.news;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.news.fragment.HomeFragment;
import com.example.news.fragment.QuizFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private QuizFragment quizFragment;
    private Fragment[] fragmentArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        homeFragment = new HomeFragment();
        quizFragment = new QuizFragment();
        fragmentArray = new Fragment[]{ homeFragment, quizFragment};
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.navigation_home) {
                   switchFragment(0);
                    return true;
                }else if (menuItem.getItemId()==R.id.navigation_exam) {
                    switchFragment(1);
                    return true;
                }
                return false;
            }
        });
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_slot, fragmentArray[0])
                .add(R.id.fl_slot,fragmentArray[1])
                .hide(fragmentArray[1])
                .show(fragmentArray[0])
                .commit();

    }

    private void switchFragment(int i) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int j = 0; j < fragmentArray.length; j++) {
            transaction.hide(fragmentArray[j]);
        }
        transaction.show(fragmentArray[i]);
        transaction.commit();
    }


}
