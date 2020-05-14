package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Page;
import com.example.myapplication.model.SecondStory;
import com.example.myapplication.model.Story;

import java.util.Stack;

public class SecondStoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private String name;
    private SecondStory story;
    private ImageView imageView;
    private TextView textView;
    private Button choice1Button;
    private Button choice2Button;

    private Stack<Integer> pageStack = new Stack<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        imageView = (ImageView)findViewById(R.id.storyImageView);
        textView = (TextView)findViewById(R.id.storyTextView);
        choice1Button = (Button)findViewById(R.id.choice1Button);
        choice2Button = (Button)findViewById(R.id.choice2Button);

        Intent intent = getIntent();
        name = intent.getStringExtra(getString(R.string.key_name));
        if(name == null || name.isEmpty()){
            name = "Employee";
        }
        Log.d(TAG, name);

        story = new SecondStory();
        loadPage(0);

    }

    private void loadPage(int pageNumber ){

        pageStack.push(pageNumber);
        final Page page = story.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        imageView.setImageDrawable(image);

        String pageText = getString(page.getTextId());
        pageText = String.format(pageText, name);
        textView.setText(pageText);
        if(page.isFinalPage()){
            choice1Button.setVisibility(View.VISIBLE);
            choice1Button.setBackgroundColor(Color.rgb(12,12,12));
            choice1Button.setText(null);
            choice2Button.setText("PLAY AGAIN");
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    //finish();
                    loadPage(0);
                }
            });

        }else {
            loadButtons(page);
        }
    }

    private void loadButtons(final Page page) {
        choice1Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });
        choice2Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        pageStack.pop();
        if(pageStack.isEmpty()){
            super.onBackPressed();
        } else {
            loadPage(pageStack.pop());
        }

    }
}