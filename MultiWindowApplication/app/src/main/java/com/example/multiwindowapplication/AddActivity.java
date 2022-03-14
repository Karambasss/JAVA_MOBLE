package com.example.multiwindowapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class AddActivity extends AppCompatActivity {
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        textView2 = findViewById(R.id.textView2);
        String name = getIntent().getStringExtra("userName"); //получаем данные из намерения!
        if (name != null){ //если данные не пустые
            textView2.setText(name);
            textView2.setTextSize(25);
            textView2.setTextColor(Color.MAGENTA);
        }
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