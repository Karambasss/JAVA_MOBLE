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

import com.example.sportapplicationproject.Controllers.ApiHelper;
import com.example.sportapplicationproject.Entities.Match;

import java.io.IOException;
import java.util.ArrayList;

public class EnglishActivity extends AppCompatActivity {
    ListView listviewAPL;
    ArrayAdapter<Match> arrayAdapter;
    ArrayList<Match> englishMatches = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);
        listviewAPL = findViewById(R.id.listviewAPL);
        arrayAdapter = new ArrayAdapter<>(EnglishActivity.this, android.R.layout.simple_list_item_1, englishMatches);
        listviewAPL.setAdapter(arrayAdapter);
        registerForContextMenu(listviewAPL);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Match> parsedEnglishMatches = null;
                try {
                    parsedEnglishMatches = ApiHelper.getEnglishPremierLeagueGames();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                updateUi(parsedEnglishMatches);
            }
        });
        thread.start();
    }
    //Вносим данные с Thread на UiThread
    public void updateUi(ArrayList<Match> matches){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                englishMatches.addAll(matches);
                arrayAdapter.notifyDataSetChanged();
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
        getMenuInflater().inflate(R.menu.menu_for_english_premier_league, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    //TODO создание, вызов новых activity посредством нажатия на элемент меню.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.englishMenuItem_english_standings:
                intent = new Intent(EnglishActivity.this, EnglishStandingsActivity.class);
                startActivity(intent);
                break;
            case R.id.englishMenuItem_english_top_scores:
                intent = new Intent(EnglishActivity.this, EnglishTopScoresActivity.class);
                startActivity(intent);
                break;
            case R.id.englishMenuItem_english_players:
                intent = new Intent(EnglishActivity.this, EnglishPlayersActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}