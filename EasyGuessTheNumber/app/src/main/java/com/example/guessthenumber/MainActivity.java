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
    EditText x_fld;
    RadioGroup radioGroup;
    int randomNumber;
    int kolvo = 0;
    boolean start_game = false;

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
    public int validate(){
        int randomNumber;
        int id  = radioGroup.getCheckedRadioButtonId(); // получаем айди выбранного radioButton
        switch (id){
            case R.id.radioButton:
                        randomNumber = getRandomNumber(10);
                        break;
                    case R.id.radioButton2:
                        randomNumber = getRandomNumber(25);
                        break;
                    case R.id.radioButton3:
                        randomNumber = getRandomNumber(50);
                        break;
                    case R.id.radioButton4:
                        randomNumber = getRandomNumber(100);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + id);
        }
        return randomNumber;
    }

    public void startBtn(View view) {
        result_fld.setText("");
        randomNumber = validate(); // получаем рандомное число от компьютера в зависимости от выбранной сложности уровня!
        Toast.makeText(this, "Число загадано,\nигра началась...", Toast.LENGTH_SHORT).show();
        start_game = true;
    }

    public void exitBtn(View view) {
        finish();
    }

    public void btn_ok(View view) {
        if (start_game) {
            result_fld.setText("");
            int userNumber;
            try {
                userNumber = Integer.parseInt(String.valueOf(x_fld.getText()));
            }
            catch (NumberFormatException exception) {
                result_fld.setText("Вы ошиблись! Попробуйте ввести число!");
                return;
            }
            if (userNumber != randomNumber) {
                if (userNumber > randomNumber) {
                    Toast.makeText(this, "Ваше число больше!", Toast.LENGTH_SHORT).show();
                }
                if (userNumber < randomNumber) {
                    Toast.makeText(this, "Ваше число меньше!", Toast.LENGTH_SHORT).show();
                }
                kolvo++;
            }
            else {
                String tries;
                if (kolvo == 1){
                    tries = " попытку";
                }
                else if (kolvo == 2 || kolvo == 3 || kolvo == 4){
                    tries = " попытки";
                }
                else{
                    tries = " попыток";
                }

                String s = getResources().getString(R.string.win) + "\nЗа " + kolvo + tries;
                result_fld.setText(s);
                start_game = false;
                kolvo = 0;
                Toast.makeText(this, "Для того, чтобы \nсыграть еще раз, \nнажмите НАЧАТЬ ИГРУ!", Toast.LENGTH_LONG).show();
            }
            x_fld.setText("");
        }
        else {
            Toast.makeText(this, "Игра не началась, \nнажмите НАЧАТЬ ИГРУ!", Toast.LENGTH_SHORT).show();
        }
    }
}