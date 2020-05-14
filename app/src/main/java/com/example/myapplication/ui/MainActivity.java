package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;


public class MainActivity extends AppCompatActivity {

    //private EditText nameField;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // nameField = findViewById(R.id.personName);
        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               // String name = nameField.getText().toString();
                //Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
                startStory();

            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  nameField.setText("");
    }

    private void startStory() {
       Intent intent = new Intent(this, IndexActivity.class);
      //  Resources resources = getResources();
        //String key = resources.getString(R.string.key_name);
        //intent.putExtra(key, name);
        startActivity(intent);
    }
}
