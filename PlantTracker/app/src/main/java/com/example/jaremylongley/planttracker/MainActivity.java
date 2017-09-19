package com.example.jaremylongley.planttracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // OnClick event for add button
    public void addRowButtonClicked(View view) {
        Intent intent = new Intent(this, UserPlantInformation.class);
        TableLayout layout = (TableLayout) findViewById(R.id.mainTable);
        startActivity(intent);
//        TableRow newRow = new TableRow(this);
//        layout.addView(newRow);
    }
}
