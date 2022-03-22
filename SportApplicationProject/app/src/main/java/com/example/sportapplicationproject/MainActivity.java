package com.example.sportapplicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportapplicationproject.Controllers.ApiHelper;
import com.example.sportapplicationproject.Entities.Country;
import com.example.sportapplicationproject.Entities.Match;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Switch aSwitch;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = findViewById(R.id.switch1);
        textView = findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch.isChecked()){
                    textView.setText("\tЗдравствуйте, меня зовут Кузнецов Михаил, я из группы Пи19-4. \n\n \tЯ подготовил проект по дисциплине: Мобильная разработка на тему \"спортивных мероприятий\". \n\n \tЯ использую футбол и футбольные матчи в качестве спортивных мероприятий. \n\n\tВ данном приложении вы можете увидеть систему футбольных событий, а именно: футбольные матчи разных команд, турнирные таблицы, списки бомбардиров, и многое многое другое!");
                    textView.setTextColor(Color.BLACK);
                    textView.setTextSize(25);
                }
                else {
                    textView.setText("");
                }
            }
        });

        if (isNetwork(getApplicationContext())) {

            Toast.makeText(getApplicationContext(), "Интернет подключен", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Интернет не подключен", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isNetwork(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.menuItem_russian_matches:
                intent = new Intent(MainActivity.this, RussianActivity.class);
                startActivity(intent);
                break;
            case R.id.menuItem_english_matches:
                intent = new Intent(MainActivity.this, EnglishActivity.class);
                startActivity(intent);
                break;
            case R.id.menuItem_live_matches:
                intent = new Intent(MainActivity.this, LiveActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}