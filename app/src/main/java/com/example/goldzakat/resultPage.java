package com.example.goldzakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class resultPage extends AppCompatActivity {
    //build variable for each data to save in shared preferences
    SharedPreferences sp;

    DecimalFormat df = new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        //load data from shared preferences
        sp = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        //transparent status bar
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //Retrieve value from previous activity
        Intent intent = getIntent();
        String goldType = intent.getStringExtra("goldType");
        TextView tv1 = findViewById(R.id.typeTV);
        editor.putString("typeGold", goldType);
        tv1.setText("Type: " + goldType);

        float weight = intent.getFloatExtra("weight",0);
        TextView tv2 = findViewById(R.id.weightTV);
        editor.putString("weight", String.valueOf(weight));
        tv2.setText("Weight: " + weight + "g");

        float value = intent.getFloatExtra("value",0);
        TextView tv3 = findViewById(R.id.ValueTV);
        editor.putString("value", String.valueOf(value));
        tv3.setText("Gold value:\nRM " + df.format(value));

        //calculate and display total gold value
        double vog = weight*value;
        TextView tv4 = findViewById(R.id.goldValueText);
        editor.putString("vog", String.valueOf(vog));
        tv4.setText("RM " + df.format(vog));

        //calculate and display zakat payable gold
        int x = 0;
        if (goldType.equalsIgnoreCase("wear")){
            x = 200;
        }else if (goldType.equalsIgnoreCase("keep")){
            x = 85;
        }else{}
        double zakatPayable = (weight - x)*value;
        TextView tv5 = findViewById(R.id.zpText);
        if (zakatPayable <=0){
            zakatPayable=0;
            tv5.setText("RM 0.00");
        }else{
            tv5.setText("RM "+df.format(zakatPayable));
        }
        editor.putString("zakatPayable", String.valueOf(zakatPayable));

        //the total zakat
        double totZakat = 0.025*zakatPayable;
        TextView tv6 = findViewById(R.id.totalText);
        if (totZakat <=0){
            tv6.setText("RM 0.00");
        }else{
            tv6.setText("RM " + df.format(totZakat));
        }
        editor.putString("totZakat", String.valueOf(totZakat));
        editor.commit();

        Button button = findViewById(R.id.btn_lobby);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the next activity
                Intent intent = new Intent(resultPage.this, MainActivity.class);

                // Start the next activity
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
    }
}