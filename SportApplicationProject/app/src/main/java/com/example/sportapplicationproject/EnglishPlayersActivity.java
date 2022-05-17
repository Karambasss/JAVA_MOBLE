package com.example.sportapplicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sportapplicationproject.Controllers.ApiHelper;
import com.example.sportapplicationproject.Entities.Players;

import java.io.IOException;
import java.util.ArrayList;

public class EnglishPlayersActivity extends AppCompatActivity {
    ListView listViewEnglishPlayers;
    ArrayAdapter<Players> arrayAdapter;
    ArrayList<Players> playersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_players);
        listViewEnglishPlayers = findViewById(R.id.listViewEnglishPlayers);
        arrayAdapter = new ArrayAdapter<>(EnglishPlayersActivity.this, android.R.layout.simple_list_item_1, playersList);
        listViewEnglishPlayers.setAdapter(arrayAdapter);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Players> players = null;
                try {
                    players = ApiHelper.getEnglishPlayers();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                updateUi(players);
            }
        });
        thread.start();
    }
    public void updateUi(ArrayList<Players> players){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                playersList.addAll(players);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}