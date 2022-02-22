package com.example.menufrommyxml;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Switch aSwitch;
    Menu myMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = findViewById(R.id.switch1);
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPrepareOptionsMenu(myMainMenu); //вызвать явно метод onPrepareOptionsMenu (при нажатии на свич вызываем prepare для изменения)
                //myMainMenu.setGroupVisible(R.id.groupMain, aSwitch.isChecked());
            }
        });
    }

    //Создаем меню и меняем стандарт меню на свой макет меню
    // первый запуск это и есть изменение состояние меню
    //вызывается при создании только
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); //в меню кладем наш макет (наполняем)
        myMainMenu = menu;
        Log.d("LOGGGGGGG", "CREATED FROM OPTIONS MENu");
        return super.onCreateOptionsMenu(menu);
    }
    //вызывается каждый раз перед отображением перед изменением состояния меню
    // (чтобы что-то убрать и тп) (то есть, после того как мы кликнули на menu)
    //отображение - при старте или при тыке пользователя на троеточие (вызов меню)!, при нажатии на свич!
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d("LOL", "CREATED FROM PREPARED MENu");
        menu.setGroupVisible(R.id.groupMain,aSwitch.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }
    //при нажатии на элемент menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.my_item_exit:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}