package com.example.multiwindowapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class InfoActivity extends AppCompatActivity {
    TextView textView7;
    TextView textView8;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        textView7 = findViewById(R.id.textView7);
        aSwitch = findViewById(R.id.switch3);
        textView8 = findViewById(R.id.textView8);
        String user = getIntent().getStringExtra("userName"); //получаем намерение и извлекаем по ключу слово
        if (user != null){ //если нашлось слово: (когда мы писали в вызове намерения putExtra), соответсвенно, null не было переданно
            textView7.setText(user);
            textView7.setTextColor(Color.GREEN);
        }
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch.isChecked()){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy.mm.dd");
                    SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
                    String date = format.format(new Date(System.currentTimeMillis()));
                    String time = format2.format(new Date(System.currentTimeMillis()));
                    textView8.setText(getResources().getText(R.string.app_info_info1_txt)  + " " + date +  "\n" + "\n" + getResources().getText(R.string.app_info_info2_txt) + " " + time);
                    textView8.setTextColor(Color.BLUE);
                }
                else {
                    textView8.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.menu_add_choice:
                intent = new Intent(this, AddActivity.class);
                startActivity(intent); //запускаем намерение (откроется новое активити)
                break;
            case R.id.menu_time_choice:
                // создаем намерение
                intent = new Intent(this, TimeActivity.class);
                startActivity(intent); //запускаем намерение (откроется новое активити)
                break;
            case R.id.menu_date_choice:
                intent = new Intent(this, DateActivity.class);
                startActivity(intent); //запускаем намерение (откроется новое активити)
                break;
            case R.id.menu_info_choice:
                intent = new Intent(this, InfoActivity.class);
                startActivity(intent); //запускаем намерение (откроется новое активити)
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}