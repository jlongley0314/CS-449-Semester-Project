package com.example.jaremylongley.planttracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by jaremylongley on 9/14/17.
 */

public class UserPlantInformation extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView plantImage;

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
        this.onBackPressed();
    }

    @Override
    // Called when user has selected picture from gallery
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            // If request code equals our code, result is good, and there is data
            Uri selectedImage = data.getData();
            // Get address of selected image and display
            plantImage.setImageURI(selectedImage);
        }
    }
}
