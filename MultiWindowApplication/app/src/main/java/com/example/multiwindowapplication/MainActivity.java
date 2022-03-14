package com.example.multiwindowapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextTextPersonName;
    String userNameForInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
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
                intent.putExtra("userName", userNameForInfo);
                startActivity(intent); //запускаем намерение (откроется новое активити)
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void clickBtn(View view) {
        String userName;
        try {
            userName = String.valueOf(editTextTextPersonName.getText());
            userNameForInfo = userName;
        }
        catch (NumberFormatException exception){
            Toast.makeText(MainActivity.this,getResources().getText(R.string.error1_txt),Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
        editTextTextPersonName.getText().clear();
    }
}