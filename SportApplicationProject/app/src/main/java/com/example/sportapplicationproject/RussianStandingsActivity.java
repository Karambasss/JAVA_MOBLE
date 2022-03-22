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

public class RussianStandingsActivity extends AppCompatActivity {
    ListView listViewRussianStandings;
    ArrayAdapter<Standings> arrayAdapter;
    ArrayList<Standings> standingsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_russian_standings);
        listViewRussianStandings = findViewById(R.id.listViewRussianStandings);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, standingsList);
        listViewRussianStandings.setAdapter(arrayAdapter);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<Long, String> map;
                ArrayList<Standings> russianLeagueStandings = null;
                try {
                    map = ApiHelper.getRussianTeamForStandings();
                    russianLeagueStandings = ApiHelper.getRussianLeagueStandings(map);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                updateUi(russianLeagueStandings);
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