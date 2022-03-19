package com.example.sportapplicationproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

}