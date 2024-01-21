package com.example.goldzakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class selectGold extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gold);

        //transparent status bar
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Button button1 = findViewById(R.id.btn_wear);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the next activity
                Intent intent = new Intent(selectGold.this, wear_selectedInput.class);
                String goldType = "Wear";
                intent.putExtra("goldType", goldType);
                // Start the next activity
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.btn_keep);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the next activity
                Intent intent = new Intent(selectGold.this, wear_selectedInput.class);
                String goldType = "Keep";
                intent.putExtra("goldType", goldType);
                // Start the next activity
                startActivity(intent);
            }
        });
    }
}