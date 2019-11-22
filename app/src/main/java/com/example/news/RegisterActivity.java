package com.example.news;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void login(String name, String password) {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String user = sp.getString(name,"");
        if (user.equals("")){
            SharedPreferences.Editor edit = sp.edit();
            edit.putString(name,password);
            edit.commit();
            Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"User already exists",Toast.LENGTH_SHORT).show();
        }
    }


}
