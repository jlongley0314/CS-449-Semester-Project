package com.example.jaremylongley.planttracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Created by jaremylongley on 9/14/17.
 */

public class UserPlantInformation extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView plantImage;
    String plantImageString;
    String nameText;
    String ageText;
    String groupText;
    String notesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);
        plantImage = (ImageView) findViewById(R.id.plantImage);
    }

    public void addImagePressed(View view) {
        // Display the Android gallery in order to upload images to app
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void savePressed(View view) {

        Intent intent = new Intent();

        // get the text from all the data fields in order to send back
        EditText nameField = (EditText) findViewById(R.id.plantNameText);
        EditText ageField = (EditText) findViewById(R.id.plantAgeText);
        EditText groupField = (EditText) findViewById(R.id.plantGroupText);
        EditText notesfield = (EditText) findViewById(R.id.otherNotesText);

        // put the text in an intent to send back to mainActivity
        this.nameText = nameField.getText().toString();
        intent.putExtra("nameText", this.nameText);
        this.ageText = ageField.getText().toString();
        intent.putExtra("ageText", this.ageText);
        this.groupText = groupField.getText().toString();
        intent.putExtra("groupText", this.groupText);
        this.notesText = notesfield.getText().toString();
        intent.putExtra("notesText", this.notesText);
        intent.putExtra("plantImage", this.plantImageString);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    // Called when user has selected picture from gallery
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // UNCOMMENT TO ALLOW INCOMING PHOTOS FROM GALLERY
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            // If request code equals our code, result is good, and there is data
            Uri selectedImage = data.getData();
            // Get address of selected image and display
            plantImage.setImageURI(selectedImage);
            plantImageString = selectedImage.toString();
        }
    }

    private void dispatchTakePictureIntent() {

    }
}
