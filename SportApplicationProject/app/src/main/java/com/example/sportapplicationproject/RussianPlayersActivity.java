package com.example.sportapplicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sportapplicationproject.Controllers.ApiHelper;
import com.example.sportapplicationproject.Entities.Players;

import java.io.IOException;
import java.util.ArrayList;

public class RussianPlayersActivity extends AppCompatActivity {
    ListView listViewRussianPlayers;
    ArrayAdapter<Players> arrayAdapter;
    ArrayList<Players> russianPlayersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_russian_players);
        listViewRussianPlayers = findViewById(R.id.listViewRussianPlayers);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, russianPlayersList);
        listViewRussianPlayers.setAdapter(arrayAdapter);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Players> players = null;
                try {
                    players = ApiHelper.getRussianPlayers();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                updateUi(players);
            }
        });
        thread.start();
    }

    public void updateUi(ArrayList<Players> resultPlayersList){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                russianPlayersList.addAll(resultPlayersList);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}