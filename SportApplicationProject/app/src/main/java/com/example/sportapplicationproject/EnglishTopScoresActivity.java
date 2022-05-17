package com.example.sportapplicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sportapplicationproject.Controllers.ApiHelper;
import com.example.sportapplicationproject.Entities.TopScores;

import java.io.IOException;
import java.util.ArrayList;

public class EnglishTopScoresActivity extends AppCompatActivity {
    ListView listViewEnglishTopScores;
    ArrayAdapter<TopScores> arrayAdapter;
    ArrayList<TopScores> playersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_top_scores);
        listViewEnglishTopScores = findViewById(R.id.listViewEnglishTopScores);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playersList);
        listViewEnglishTopScores.setAdapter(arrayAdapter);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<TopScores> players = null;
                try {
                    players = ApiHelper.getEnglishTopScoresPlayers();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
               updateUi(players);
            }
        });
        thread.start();
    }
    public void updateUi(ArrayList<TopScores> players){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                playersList.addAll(players);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}