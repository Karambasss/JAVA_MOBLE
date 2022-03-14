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

public class DateActivity extends AppCompatActivity {
    TextView textView5;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView5 = findViewById(R.id.textView5);
        aSwitch = findViewById(R.id.switch2);
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch.isChecked()){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy.mm.dd");
                    textView5.setText(getResources().getText(R.string.app_date_info3_txt) + " " + format.format(new Date(System.currentTimeMillis())));
                    textView5.setTextColor(Color.BLUE);
                }
                else {
                    textView5.setText("");
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