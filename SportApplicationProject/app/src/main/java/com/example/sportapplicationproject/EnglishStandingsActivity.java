package com.example.sportapplicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sportapplicationproject.Controllers.ApiHelper;
import com.example.sportapplicationproject.Entities.Standings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EnglishStandingsActivity extends AppCompatActivity {
    ListView listViewForEnglishStandings;
    ArrayAdapter<Standings> arrayAdapter;
    ArrayList<Standings> standingsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_standings);
        listViewForEnglishStandings = findViewById(R.id.listViewForEnglishStandings);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, standingsList);
        listViewForEnglishStandings.setAdapter(arrayAdapter);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<Long, String> map;
                ArrayList<Standings> standings = null;
                try {
                    map = ApiHelper.getEnglishTeamForStandings();
                    standings = ApiHelper.getEnglishLeagueStandings(map);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                updateUi(standings);
            }
        });
        thread.start();
    }

    public void updateUi(ArrayList<Standings> resultStandings){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                standingsList.addAll(resultStandings);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}