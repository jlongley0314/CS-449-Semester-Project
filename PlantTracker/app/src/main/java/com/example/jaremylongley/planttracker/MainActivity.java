package com.example.jaremylongley.planttracker;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;
    private static final int PROGRESS_ACTIVITY_RESULT_CODE = 1;
    DatabaseHelper db;
    List<Plant> userPlants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.db = new DatabaseHelper(this);
        this.reloadRows();
//        userPlants = new ArrayList<Plant>();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.print("Coming in from seperate intent");
        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {

                // get data from Intent
                String nameText = data.getStringExtra("nameText");
                String ageText = data.getStringExtra("ageText");
                String groupText = data.getStringExtra("groupText");
                String notesText = data.getStringExtra("notesText");
                String imagePath = data.getStringExtra("plantImage");
                Log.d("1", "Got data");

                this.createPlant(imagePath, nameText, ageText, groupText, notesText);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.checklistButton:
                Intent intent = new Intent(this, Checklist.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // OnClick event for add button
    public void addButtonClicked(View view) {
        Intent intent = new Intent(this, UserPlantInformation.class);
        startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
    }

    private void createPlant(String imagePath, String nameText, String ageText, String groupText, String notesText) {
        int plantID = db.getPlantCount() + 1;
        Plant newPlant = new Plant(plantID, imagePath, nameText, ageText, groupText);
        db.addPlant(plantID, imagePath, nameText, ageText, groupText);
        System.out.println("Added new plant, new database length:");
        System.out.println(db.getPlantCount());
//        this.userPlants.add(newPlant);
        this.reloadRows();
    }

    private void addRow(Plant plant) {
        TableLayout layout = (TableLayout) findViewById(R.id.mainTable);
        TextView text = new TextView(this);
        ImageView plantImageView = new ImageView(this);
        final TableRow newRow = new TableRow(this);
        plantImageView.setImageURI(Uri.parse(plant.getImagePath()));

        text.setText(plant.getName());
        System.out.println(plant.getName());
        text.setTextSize(20);
        text.setGravity(Gravity.CENTER);
        text.setPadding(0, 200, 0, 0);
        Log.d("2","Adding table row");
        newRow.setClickable(true);
//        newRow.addView(plantImageView);
        newRow.addView(text);
        newRow.setPadding(0, 20, 0 , 20);
        layout.addView(newRow);
    }

    private void reloadRows() {
        this.userPlants = db.getAllPlants();
        System.out.println("SIZE OF DATABASE:" + this.userPlants.size());
        for (int i = 0; i<this.userPlants.size(); i++) {
            Plant newPlant = this.userPlants.get(i);
            this.addRow(newPlant);
        }
    }

    // Click listener for Progress button
    public void plantProgressButtonClicked(View view) {
        Intent intent = new Intent(this, ProgressTracker.class);
        System.out.println("Got the intent");
//        Plant plantClicked = this.userPlants.get(tag);
//        intent.putExtra("nameText", plantClicked.getName());
//        intent.putExtra("ageText", plantClicked.getAge());
//        intent.putExtra("groupText", plantClicked.getGroup());
//        intent.putExtra("notesText", plantClicked.getNotes());
//        intent.putExtra("plantImage", plantClicked.getImagePath());
        startActivity(intent);
    }

    // OnClick event for plant clicked
//    public void plantTableRowClicked(int tag) {
//        Intent intent = new Intent(this, ProgressTracker.class);
//        System.out.println("Got the intent");
//        Plant plantClicked = this.userPlants.get(tag);
//        intent.putExtra("nameText", plantClicked.getName());
//        intent.putExtra("ageText", plantClicked.getAge());
//        intent.putExtra("groupText", plantClicked.getGroup());
//        intent.putExtra("notesText", plantClicked.getNotes());
//        intent.putExtra("plantImage", plantClicked.getImagePath());
//        startActivity(intent);
//    }

}
