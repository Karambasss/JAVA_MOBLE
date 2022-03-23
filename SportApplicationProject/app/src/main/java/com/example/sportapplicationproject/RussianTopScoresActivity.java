package com.example.sportapplicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sportapplicationproject.Controllers.ApiHelper;
import com.example.sportapplicationproject.Entities.TopScores;

import java.io.IOException;
import java.util.ArrayList;

public class RussianTopScoresActivity extends AppCompatActivity {
    ListView listViewRussianTopScores;
    ArrayAdapter<TopScores> arrayAdapter;
    ArrayList<TopScores> russianTopScoresList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_russian_top_scores);
        listViewRussianTopScores = findViewById(R.id.listViewRussianTopScores);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, russianTopScoresList);
        listViewRussianTopScores.setAdapter(arrayAdapter);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<TopScores> topScores = null;
                try {
                    topScores = ApiHelper.getRussianTopScoresPlayers();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                updateUi(topScores);
            }
        });
        thread.start();
    }

    public void updateUi(ArrayList<TopScores> resultList){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                russianTopScoresList.addAll(resultList);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}