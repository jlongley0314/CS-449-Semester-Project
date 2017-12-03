package com.example.jaremylongley.planttracker;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaremylongley on 10/22/17.
 */

public class Checklist extends AppCompatActivity {
    List<Plant> userPlants;
    Plant displayingPlant;
    int ID;
    DatabaseHelper db;
    EditText prunedText;
    EditText wateredText;
    EditText repottedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        Intent intent = getIntent();
        this.db = new DatabaseHelper(this);
        this.prunedText = (EditText) findViewById(R.id.prunedText);
        this.wateredText = (EditText) findViewById(R.id.wateredText);
        this.repottedText = (EditText) findViewById(R.id.repottedText);
        this.ID = intent.getIntExtra("plantUID", 0);
        Log.d("1", "Got data" + this.ID);
        this.userPlants = db.getAllPlants();
        this.displayingPlant = this.userPlants.get(this.ID - 1);
        Log.d("1", "Got plant" + this.displayingPlant.getName());
//        this.userPlants = db.getAllPlants();
//        ListView mlistView = (ListView) findViewById(R.id.listView);

//        Plant plant1 = new Plant("", "Plant1","","","");
//        Plant plant2 = new Plant("", "Plant2","","","");
//        Plant plant3 = new Plant("", "Plant3","","","");
//
//        userPlants.add(plant1);
//        userPlants.add(plant2);
//        userPlants.add(plant3);
//        PlantListAdapter adapter = new PlantListAdapter(this, R.layout.four_col_list_view, userPlants);
//        mlistView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.print("Coming in from seperate intent");
        if (resultCode == RESULT_OK) {
            this.ID = data.getIntExtra("plantUID", 0);
            Log.d("1", "Got data" + this.ID);
            this.displayingPlant = db.getAllPlants().get(this.ID - 1);
            Log.d("1", "Got plant" + this.displayingPlant.getName());
        }
    }

    public void savePressed(View view) {
        Intent intent = new Intent();

        if (!this.prunedText.getText().toString().equalsIgnoreCase("")) {
            db.updateLastPruned(this.ID, this.prunedText.getText().toString());
        }
        if (!this.wateredText.getText().toString().equalsIgnoreCase("")) {
            db.updateLastWatered(this.ID, this.wateredText.getText().toString());
        }
        if (!this.repottedText.getText().toString().equalsIgnoreCase("")) {
            db.updateLastRepotted(this.ID, this.repottedText.getText().toString());
        }

        intent.putExtra("watered", this.wateredText.getText().toString());
        intent.putExtra("pruned", this.prunedText.getText().toString());
        intent.putExtra("repotted", this.repottedText.getText().toString());
        /*
        * // get the text from all the data fields in order to send back
        EditText nameField = (EditText) findViewById(R.id.plantNameText);
        EditText ageField = (EditText) findViewById(R.id.plantAgeText);
        EditText groupField = (EditText) findViewById(R.id.plantGroupText);
        EditText notesfield = (EditText) findViewById(R.id.otherNotesText);

        // put the text in an intent to send back to mainActivity
        this.nameText = nameField.getText().toString();
        intent.putExtra("nameText", this.nameText);
        * */


        setResult(RESULT_OK, intent);
        finish();
    }
}
