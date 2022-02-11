package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView resultFld;
    EditText editTextNumber;
    EditText editTextNumber2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        editTextNumber2 = (EditText) findViewById(R.id.editTextNumber2);
        resultFld = (TextView) findViewById(R.id.resultFld);

    }

    public void onClick(View v){
        double a = 0, b = 0;
        try {
            a = Double.parseDouble(String.valueOf(editTextNumber.getText()));
            b = Double.parseDouble(String.valueOf(editTextNumber2.getText()));
        }
        catch (NumberFormatException exception){
                resultFld.setText(R.string.error_msg);
        }
        double result;
        switch (v.getId()){
            case R.id.btn_plus:
                result = a + b;
                break;
            case R.id.btn_minus:
                result = a - b;
                break;
            case R.id.btn_del:
                if (b != 0){
                    result = a / b;
                }
                else {
                    resultFld.setText(R.string.error_div_msg);
                    return;
                }
                break;
            case R.id.btn_umn:
                result = a * b;
                break;
            default:
                throw new IllegalStateException(String.valueOf(R.string.error_default));
        }
        resultFld.setText(String.valueOf(result));
    }
}