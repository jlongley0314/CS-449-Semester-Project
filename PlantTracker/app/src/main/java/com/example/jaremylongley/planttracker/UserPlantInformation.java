package com.example.jaremylongley.planttracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by jaremylongley on 9/14/17.
 */

public class UserPlantInformation extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView plantImage;
    Bitmap plantImageBmp;
    String plantImageString;
    String nameText;
    String ageText;
    String groupText;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);
        this.db = new DatabaseHelper(this);
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

        // User can only go on once all the fields have been filled
        if (nameField.equals("") || ageField.equals("") || groupField.equals("")) {
            // Alert the user to add text to every field
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle("Please enter information for every field");

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            builder.show();
        } else {
            // put the text in an intent to send back to mainActivity
            this.nameText = nameField.getText().toString();
            this.ageText = ageField.getText().toString();
            this.groupText = groupField.getText().toString();

            int plantID = db.getPlantCount() + 1;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            this.plantImageBmp.compress(Bitmap.CompressFormat.PNG, 100, output);
            byte [] b = output.toByteArray();
            //int UID, Bitmap image, String name, String age, String group
            db.addPlant(plantID, b, this.nameText, this.ageText, this.groupText);

            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    // Called when user has selected picture from gallery
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // UNCOMMENT TO ALLOW INCOMING PHOTOS FROM GALLERY
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            // If request code equals our code, result is good, and there is data
            Uri selectedImageUri = data.getData();
            // Get address of selected image and display
            Bitmap selectedImage = null;
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            plantImage.setImageBitmap(selectedImage);
            this.plantImageBmp = selectedImage;
        }
    }

    private void dispatchTakePictureIntent() {

    }
}
