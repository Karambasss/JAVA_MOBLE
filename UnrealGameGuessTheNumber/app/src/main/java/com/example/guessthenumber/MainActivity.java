package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView result_fld;
    int randomNumber;
    EditText x_fld;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radioGroup);
        result_fld = findViewById(R.id.result_fld);
        x_fld = findViewById(R.id.x_fld);

    }

    //получаем рандомное число из диапазона
    public int getRandomNumber(int y){
        Random random = new Random();
        int my_randomNumber = random.nextInt(y)+1;
        return my_randomNumber;

    }
    //Метод для отлова айди выбранного radioButton и получения от него рандомного числа
    public void validate(){
        int id  = radioGroup.getCheckedRadioButtonId(); // получаем айди выбранного radioButton
        switch (id){
            case R.id.radioButton:
                        randomNumber = getRandomNumber(5);
                        break;
                    case R.id.radioButton2:
                        randomNumber = getRandomNumber(15);
                        break;
                    case R.id.radioButton3:
                        randomNumber = getRandomNumber(30);
                        break;
                    case R.id.radioButton4:
                        randomNumber = getRandomNumber(50);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + id);
        }
    }

    public void startBtn(View view) {
        boolean flag;
        int userNumber;
        try{
            userNumber = Integer.parseInt(String.valueOf(x_fld.getText()));
        }
        catch (NumberFormatException exception){
            result_fld.setText("Вы ошиблись! Попробуйте ввести число!");
            return;
        }
        validate(); // получаем рандомное число от компьютера в зависимости от выбранной сложности уровня!

        if (userNumber == randomNumber){
            flag = true;
        }
        else {
            flag = false;
        }
        Toast.makeText(this,"Загаданное число: " + randomNumber, Toast.LENGTH_SHORT).show(); //всплывающие сообщения!
        if (flag){
            result_fld.setText(R.string.win);
        }
        else{
            result_fld.setText(R.string.defeat);
        }
    }

    public void exitBtn(View view) {
        this.finish();
    }
}