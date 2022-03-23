package com.example.sportapplicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sportapplicationproject.Controllers.ApiHelper;
import com.example.sportapplicationproject.Entities.Match;

import java.io.IOException;
import java.util.ArrayList;

public class LiveActivity extends AppCompatActivity {
    ListView listViewLiveGames;
    ArrayAdapter<Match> arrayAdapter;
    ArrayList<Match> liveGames = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        listViewLiveGames = findViewById(R.id.listViewLiveGames);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, liveGames);
        listViewLiveGames.setAdapter(arrayAdapter);
        registerForContextMenu(listViewLiveGames);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Match> parsedLiveGames = null;
                try {
                    parsedLiveGames = ApiHelper.getLiveGames();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                updateUi(parsedLiveGames);
            }
        });
        thread.start();
    }

    public void updateUi(ArrayList<Match> match) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (match.size() != 0){
                    liveGames.addAll(match);
                    arrayAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(LiveActivity.this, "Матчи пока-что не проходят!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //TODO создание контекстного меню и вызов новых элeментов контекстного меню посредством нажатия на элемент списка и проработать логику.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //TODO создание, вызов новых activity посредством нажатия на элемент контекстного меню.
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_live_games, menu);
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
            case R.id.liveMenuItemRfplMatches:
                intent = new Intent(LiveActivity.this, RussianActivity.class);
                startActivity(intent);
                break;
            case R.id.liveMenuItem_APL_Matches:
                intent = new Intent(LiveActivity.this, EnglishActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}