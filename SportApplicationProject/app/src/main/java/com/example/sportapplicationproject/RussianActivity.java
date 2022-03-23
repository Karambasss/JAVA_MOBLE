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

public class RussianActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<Match> arrayAdapter;
    ArrayList<Match> russianMatches = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_russian);
        listView = findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, russianMatches);
        listView.setAdapter(arrayAdapter);
        registerForContextMenu(listView);

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Match> russianTest = null;
                try {
                    russianTest = ApiHelper.getRussianPremierLeagueGames();
                    //System.out.println(russianTest);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                updateUi(russianTest);
            }
        });
        thread2.start();

    }
    public void updateUi(ArrayList<Match> matches){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                russianMatches.addAll(matches);
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
        getMenuInflater().inflate(R.menu.menu_for_russian_premier_league, menu);
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
            case R.id.russianMenuItem_russian_standings:
                intent = new Intent(RussianActivity.this, RussianStandingsActivity.class);
                startActivity(intent);
                break;
            case R.id.russianMenuItem_russian_top_scores:
                intent = new Intent(RussianActivity.this, RussianTopScoresActivity.class);
                startActivity(intent);
                break;
            case R.id.russianMenuItem_russian_players:
                intent = new Intent(RussianActivity.this, RussianPlayersActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}