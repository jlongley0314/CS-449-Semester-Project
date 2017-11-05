package com.example.jaremylongley.planttracker;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by jaremylongley on 9/30/17.
 */

public class Plant {
    private int UID;
    String mainImage;
    ArrayList<String> progressImages;
    String name;
    String age;
    String group;
//    String notes;

    public Plant(int UID, String imagePath, String name, String age, String group) {
        this.UID = UID;
        this.mainImage = imagePath;
        this.name = name;
        this.age = age;
        this.group = group;
//        this.notes = notes;
        this.progressImages = new ArrayList<String>();
    }

    public int getUID() { return this.UID; }

    public String getImagePath() {
        return mainImage;
    }

    public void setImagePath(String imagePath) {
        this.mainImage = imagePath;
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

    public ArrayList<String> getProgressImages() { return progressImages; }

    public void appendProgressImage(String imagePath) {
        this.progressImages.add(imagePath);
    }
}
