package com.example.jaremylongley.planttracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jaremylongley on 10/8/17.
 */

public class ProgressTracker extends AppCompatActivity {
    DatabaseHelper db;
    Plant displayingPlant;
    String date;
    String progressString;
    String repottedString;
    String wateredString;
    String prunedString;
    Boolean fromChecklist = false;
    List<Plant> userPlants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Intent intent = getIntent();
        this.db = new DatabaseHelper(this);
        int plantUID = intent.getIntExtra("plantUID", 1);
        Log.d("1", "Got data" + plantUID);
        this.userPlants = db.getAllPlants();
        this.displayingPlant = this.userPlants.get(plantUID - 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.print("Coming in from seperate intent");
        // check that it is the SecondActivity with an OK result
        if (resultCode == RESULT_OK) {
            Log.d("1", "Got data");
            // get data from Intent
            this.repottedString = data.getStringExtra("repotted");
            this.wateredString = data.getStringExtra("watered");
            this.prunedString = data.getStringExtra("pruned");
            this.fromChecklist = true;
            addRow();
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
                viewChecklistButtonClicked();
                return true;
            case R.id.addProgressButton:
                addProgressButtonClicked();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void updateProgress(String progress) {
        this.progressString = progress;
        addRow();
    }

    void addProgressButtonClicked() {
        // Alert the user to add text to this progress report
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Give a short update to your plant");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String progressText = input.getText().toString();
                updateProgress(progressText);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }

    // OnClick event for checklist to be shown
    public void viewChecklistButtonClicked() {
        Intent intent = new Intent(this, Checklist.class);
        intent.putExtra("plantUID", this.displayingPlant.getUID());
        startActivityForResult(intent, 0);
    }

    void addRow() {
        TableLayout layout = (TableLayout) findViewById(R.id.ProgressTable);
        TableRow newRow = new TableRow(this);
        TextView date = new TextView(this);
        TextView notes = new TextView(this);
        TextView watered = new TextView(this);
        TextView pruned = new TextView(this);
        TextView repotted = new TextView(this);
        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        date.setText(currentDateTimeString);
        notes.setText(this.progressString);
        if (this.fromChecklist) {
            watered.setText(this.wateredString);
            pruned.setText(this.prunedString);
            repotted.setText(this.repottedString);
        } else {
            watered.setText("");
            pruned.setText("");
            repotted.setText("");
        }

        newRow.addView(date);
        newRow.addView(notes);
        newRow.addView(watered);
        newRow.addView(pruned);
        newRow.addView(repotted);
        layout.addView(newRow);

        // reset all variables
        this.progressString = "";
        this.prunedString = "";
        this.wateredString = "";
        this.repottedString = "";
        this.fromChecklist = false;
    }
}
