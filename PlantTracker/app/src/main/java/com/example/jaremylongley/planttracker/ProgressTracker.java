package com.example.jaremylongley.planttracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jaremylongley on 10/8/17.
 */

public class ProgressTracker extends AppCompatActivity {
    Plant displayingPlant;
    String date;
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Intent intent = getIntent();
        String nameText = intent.getStringExtra("nameText");
        String ageText = intent.getStringExtra("ageText");
        String groupText = intent.getStringExtra("groupText");
        String notesText = intent.getStringExtra("notesText");
        this.imagePath = intent.getStringExtra("plantImage");
        ArrayList<String> progressImages = intent.getStringArrayListExtra("progressImages");
        displayingPlant = new Plant(intent.getStringExtra("plantImage"), nameText, ageText,groupText, notesText);
        initTable();
    }

    void initTable() {
        //This part defines the layout to be used for creating new rows
        TableLayout layout = (TableLayout) findViewById(R.id.ProgressTable);
        TableRow  titleRow = new TableRow(this);
        TableRow layoutRow = new TableRow(this);
        TextView title = new TextView(this);
        TextView imageColumn = new TextView(this);
        TextView dateColumn = new TextView(this);
        TextView notesColumn = new TextView(this);
        title.setPadding(3,3,3,3);
        title.setText("Progress");
        title.setTextSize(50);
        imageColumn.setText("Images");
        imageColumn.setTextSize(25);
        dateColumn.setText("Date");
        dateColumn.setTextSize(25);
        notesColumn.setText("Notes");
        notesColumn.setTextSize(25);
        titleRow.addView(title);
        layoutRow.addView(imageColumn);
        layoutRow.addView(dateColumn);
        layoutRow.addView(notesColumn);
        layout.addView(titleRow,0);
        layout.addView(layoutRow,1);

        TableRow dummyRow = new TableRow(this);
        ImageView image = new ImageView(this);

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(this.imagePath));
            image.setImageBitmap(bitmap);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView date = new TextView(this);
        date.setText("1/1/17");
        TextView notes = new TextView(this);
        notes.setText(this.displayingPlant.getName());
        dummyRow.addView(image);
        dummyRow.addView(date);
        dummyRow.addView(notes);
        layout.addView(dummyRow, 2);
    }
}
