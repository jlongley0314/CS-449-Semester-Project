package com.example.jaremylongley.planttracker;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaremylongley on 10/22/17.
 */

public class Checklist extends AppCompatActivity {
    List<Plant> userPlants;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        this.db = new DatabaseHelper(this);
        this.userPlants = db.getAllPlants();
        ListView mlistView = (ListView) findViewById(R.id.listView);

//        Plant plant1 = new Plant("", "Plant1","","","");
//        Plant plant2 = new Plant("", "Plant2","","","");
//        Plant plant3 = new Plant("", "Plant3","","","");
//
//        userPlants.add(plant1);
//        userPlants.add(plant2);
//        userPlants.add(plant3);
        PlantListAdapter adapter = new PlantListAdapter(this, R.layout.four_col_list_view, userPlants);
        mlistView.setAdapter(adapter);
    }
}
