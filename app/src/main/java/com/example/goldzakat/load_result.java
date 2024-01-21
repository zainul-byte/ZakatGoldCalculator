package com.example.goldzakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class load_result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_result);
        TextView tv1 = findViewById(R.id.typeTV);
        TextView tv2 = findViewById(R.id.weightTV);
        TextView tv3 = findViewById(R.id.ValueTV);
        TextView tv4 = findViewById(R.id.goldValueText);
        TextView tv5 = findViewById(R.id.zpText);
        TextView tv6 = findViewById(R.id.totalText);

        DecimalFormat df = new DecimalFormat("#.00");

        //load data
        SharedPreferences sp = getApplicationContext().getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE);
        String goldType = sp.getString("typeGold", "");
        tv1.setText("type: " + goldType);
        String weight = sp.getString("weight", "");
        tv2.setText("weight: " + weight + "g");
        String value = sp.getString("value", "");
        float floatValue = Float.parseFloat(value);
        tv3.setText("Gold value:\nRM " + df.format(floatValue));
        String vog = sp.getString("vog", "");
        double doubleVog = Double.parseDouble(vog);
        tv4.setText("RM " + df.format(doubleVog));
        String zakatPayable = sp.getString("zakatPayable", "");
        double doubleZP = Double.parseDouble(zakatPayable);
        if (doubleZP <=0){
            tv5.setText("RM 0.00");
        }else{
            tv5.setText("RM "+df.format(doubleZP));
        }
        String totZakat = sp.getString("totZakat", "");
        double doubleTZ = Double.parseDouble(totZakat);
        if (doubleTZ <=0){
            tv6.setText("RM 0.00");
        }else{
            tv6.setText("RM " + df.format(doubleTZ));
        }

        //button
        Button button = findViewById(R.id.btn_lobby);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //transparent status bar
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}