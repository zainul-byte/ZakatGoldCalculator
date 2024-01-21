package com.example.goldzakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //transparent status bar
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Button button = findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the next activity
                Intent intent = new Intent(MainActivity.this, selectGold.class);

                // Start the next activity
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.btn_load);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the next activity
                Intent intent = new Intent(MainActivity.this, load_result.class);

                // Start the next activity
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.btn_about);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the next activity
                Intent intent = new Intent(MainActivity.this, about.class);

                // Start the next activity
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}