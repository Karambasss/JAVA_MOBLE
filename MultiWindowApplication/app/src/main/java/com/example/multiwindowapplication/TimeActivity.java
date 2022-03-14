package com.example.multiwindowapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeActivity extends AppCompatActivity {
    Switch aSwitch;
    TextView time_label;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        aSwitch = findViewById(R.id.switch1);
        time_label = findViewById(R.id.time_label);
        //setTitle(R.string.app_name2_txt);
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch.isChecked()){
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                    time_label.setText(getResources().getText(R.string.app2_info_txt) + " " + format.format(new Date(System.currentTimeMillis())));
                }
                else {
                    time_label.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
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