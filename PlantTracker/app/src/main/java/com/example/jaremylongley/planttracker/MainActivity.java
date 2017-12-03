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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.print("Coming in from seperate intent");
        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d("1", "Got data");
                this.reloadRows();
            }
        }
    }

    // OnClick event for add button
    public void addButtonClicked(View view) {
        Intent intent = new Intent(this, UserPlantInformation.class);
        startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
    }

    private void addRow(Plant plant) {
        TableLayout layout = (TableLayout) findViewById(R.id.mainTable);
        TextView text = new TextView(this);
        ImageView plantImageView = new ImageView(this);
        final TableRow newRow = new TableRow(this);
        text.setText(plant.getName());
        System.out.println(plant.getName());
        text.setTextSize(20);
        text.setGravity(Gravity.CENTER);
        text.setPadding(0, 200, 0, 0);
        Log.d("2","Adding table row");
        newRow.setClickable(true);
        newRow.addView(plantImageView);
        plantImageView.setImageBitmap(plant.getImagePath());
        plantImageView.getLayoutParams().height = 500;
        plantImageView.getLayoutParams().width = 500;
        plantImageView.requestLayout();
        newRow.addView(text);
        newRow.setTag(plant.getUID());
        Log.d("1", "Plant UID" + plant.getUID());
        newRow.setPadding(0, 20, 0 , 20);
        newRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plantTableRowClicked((Integer) newRow.getTag());
            }
        });
        layout.addView(newRow);
    }

    private void reloadRows() {
        this.userPlants = db.getAllPlants();
        System.out.println("SIZE OF DATABASE:" + this.userPlants.size());
        for (int i = 0; i<this.userPlants.size(); i++) {
            Plant newPlant = this.userPlants.get(i);
            Log.d("1", "Adding row for plant UID: " + newPlant.getUID());
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
    public void plantTableRowClicked(int tag) {
        Intent intent = new Intent(this, ProgressTracker.class);
        System.out.println("Got the intent");
        Plant plantClicked = this.userPlants.get(tag-1);
        intent.putExtra("plantUID", plantClicked.getUID());
        startActivity(intent);
    }

}
