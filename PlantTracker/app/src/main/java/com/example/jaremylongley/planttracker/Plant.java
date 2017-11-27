package com.example.jaremylongley.planttracker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by jaremylongley on 9/30/17.
 */

public class Plant {
    private int UID;
    Bitmap mainImage;
    ArrayList<String> progressImages;
    String name;
    String age;
    String group;
    String lastRepotted;
    String lastWatered;
    String lastPruned;
//    String notes;

    public Plant(int UID, Bitmap image, String name, String age, String group) {
        this.UID = UID;
        this.mainImage = image;
        this.name = name;
        this.age = age;
        this.group = group;
//        this.notes = notes;
        this.progressImages = new ArrayList<String>();
    }

    public int getUID() { return this.UID; }

    public Bitmap getImagePath() {
        return mainImage;
    }

    public void setImagePath(Bitmap image) {
        this.mainImage = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

//    public String getNotes() {
//        return notes;
//    }

//    public void setNotes(String notes) {
//        this.notes = notes;
//    }

    public byte[] getImageBytes() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        this.mainImage.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public void setLastWatered(String date) {
        this.lastWatered = date;
    }
    public void setLastPruned(String date) {
        this.lastPruned = date;
    }
    public void setLastRepotted(String date) {
        this.lastRepotted = date;
    }
}
