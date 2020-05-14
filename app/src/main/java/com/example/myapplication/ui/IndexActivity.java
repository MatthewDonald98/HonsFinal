package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;


public class IndexActivity extends AppCompatActivity {

    private Button phishButton;
    private Button malwareButton;
    private Button attackerButton;
    private Button quizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        phishButton = findViewById(R.id.phishButton);
        phishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StoryActivity.class);
                startActivity(intent);
                setContentView(R.layout.activity_story);
            }

        });

        malwareButton = findViewById(R.id.malwareButton);
        malwareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondStoryActivity.class);
                startActivity(intent);
                setContentView(R.layout.activity_story);
            }
        });

        attackerButton = findViewById(R.id.attackerButton);
        attackerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ThirdStoryActivity.class);
                startActivity(intent);
                setContentView(R.layout.activity_story);
            }
        });

        quizButton = findViewById(R.id.quizButton);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), StartingScreenActivity.class);
                startActivity(intent);
                setContentView(R.layout.activity_starting_screen);
            }
        });

    }
}