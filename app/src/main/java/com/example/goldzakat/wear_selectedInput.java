package com.example.goldzakat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class wear_selectedInput extends AppCompatActivity {

    int tf1 = 0, tf2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear_selected_input);

        //transparent status bar
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //Display gold type chosen by user
        Intent intent = getIntent();
        String goldType = intent.getStringExtra("goldType");
        TextView myTextView = findViewById(R.id.textView_goldType);
        myTextView.setText("Current selected type: "+goldType);

        TextInputLayout weightTextField = findViewById(R.id.weightTextField);
        weightTextField.setError(null);
        TextInputEditText weightText = findViewById(R.id.weightText);

        TextInputLayout currentValueField = findViewById(R.id.currentValueTextField);
        currentValueField.setError(null);
        TextInputEditText cvEdit = findViewById(R.id.cvEdit);

        cvEdit.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(6,2)});

        Button button1 = findViewById(R.id.btn_proceed);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Error handling for empty value
                if (TextUtils.isEmpty(weightText.getText().toString())){
                    weightTextField.setError("Cannot leave empty!");
                }else {
                    weightTextField.setError(null);
                    tf1 = 1;
                }
                if (TextUtils.isEmpty(cvEdit.getText().toString())){
                    currentValueField.setError("Cannot leave empty!");
                }else {
                    currentValueField.setError(null);
                    tf2 = 1;
                }


                //Error handling for value = 0
                try{
                    float weightF = Float.parseFloat(weightText.getText().toString());
                    // Check if the value is zero
                    if (weightF == 0) {
                        // Show an error message
                        weightTextField.setError("The value cannot be zero");
                        tf1 = 0;
                    }else {
                        weightTextField.setError(null);
                        tf1 = 1;
                    }
                }catch (NumberFormatException e){
                    //
                }

                try{
                    float gold_F = Float.parseFloat(cvEdit.getText().toString());
                    // Check if the value is zero
                    if (gold_F == 0) {
                        // Show an error message
                        currentValueField.setError("The value cannot be zero");
                        tf1 = 0;
                    }else {
                        currentValueField.setError(null);
                        tf2 = 1;
                    }
                }catch (NumberFormatException e){
                    //
                }

                //confirm to next activity
                if (tf1 == 1 && tf2 == 1){
                    // Create an Intent to open the next activity
                    Intent intent = new Intent(wear_selectedInput.this, resultPage.class);
                    //Passing values to next Intent
                    intent.putExtra("goldType", goldType);
                    float weightF = Float.parseFloat(weightText.getText().toString());
                    intent.putExtra("weight", weightF);
                    float gold_F = Float.parseFloat(cvEdit.getText().toString());
                    intent.putExtra("value", gold_F);
                    // Start the next activity
                    startActivity(intent);
                }

            }
        });
    }
}

//filter value text to 2 decimal digits
class DecimalDigitsInputFilter implements InputFilter {

    Pattern mPattern;

    public DecimalDigitsInputFilter(int digitsBeforeZero,int digitsAfterZero) {
        mPattern=Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Matcher matcher=mPattern.matcher(dest);
        if(!matcher.matches())
            return "";
        return null;
    }

}