package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button button2;
    Button button3;
    Button button4;
    TextView resultFld;
    EditText editTextNumber;
    EditText editTextNumber2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        editTextNumber2 = (EditText) findViewById(R.id.editTextNumber2);
        resultFld = (TextView) findViewById(R.id.resultFld);

    }

    public void clickUmnozh(View view) {
        try{
            int a = Integer.parseInt(String.valueOf(editTextNumber.getText()));
            int b = Integer.parseInt(String.valueOf(editTextNumber2.getText()));
            resultFld.setText(String.valueOf(a*b));
        }
        catch (NumberFormatException exception){
            resultFld.setText("Вы ошиблись!");
        }
    }

    public void clickPlus(View view) {
        try{
            int a = Integer.parseInt(String.valueOf(editTextNumber.getText()));
            int b = Integer.parseInt(String.valueOf(editTextNumber2.getText()));
            resultFld.setText(String.valueOf(a+b));
        }
        catch (NumberFormatException exception){
            resultFld.setText("Вы ошиблись!");
        }
    }

    public void clickMinus(View view) {
        try{
            int a = Integer.parseInt(String.valueOf(editTextNumber.getText()));
            int b = Integer.parseInt(String.valueOf(editTextNumber2.getText()));
            resultFld.setText(String.valueOf(a-b));
        }
        catch (NumberFormatException exception){
            resultFld.setText("Вы ошиблись!");
        }
    }

    public void clickDelim(View view) {
        try{
            int a = Integer.parseInt(String.valueOf(editTextNumber.getText()));
            int b = Integer.parseInt(String.valueOf(editTextNumber2.getText()));
            resultFld.setText(String.valueOf(a/b));
        }
        catch (NumberFormatException exception){
            resultFld.setText("Вы ошиблись!");
        }
    }
}