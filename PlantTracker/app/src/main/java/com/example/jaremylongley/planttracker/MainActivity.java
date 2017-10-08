package com.example.jaremylongley.planttracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;
    List<Plant> userPlants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userPlants = new ArrayList<Plant>();
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

    // OnClick event for add button
    public void addRowButtonClicked(View view) {
        Intent intent = new Intent(this, UserPlantInformation.class);
        startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
    }

    public void plantTableRowClicked(View view) {
        
    }

    private void createPlant(String imagePath, String nameText, String ageText, String groupText, String notesText) {
        Plant newPlant = new Plant(imagePath, nameText, ageText, groupText, notesText);
        this.userPlants.add(newPlant);
        TextView text = new TextView(this);
        text.setText(nameText);
        TableLayout layout = (TableLayout) findViewById(R.id.mainTable);
        TableRow newRow = new TableRow(this);
        Log.d("2","Adding table row");
        newRow.addView(text);
        layout.addView(newRow);
    }


}
