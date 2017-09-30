package com.example.jaremylongley.planttracker;

import android.net.Uri;

/**
 * Created by jaremylongley on 9/30/17.
 */

public class Plant {
    String imagePath;
    String name;
    String age;
    String group;
    String notes;

    public Plant(String imagePath, String name, String age, String group, String notes) {
        this.imagePath = imagePath;
        this.name = name;
        this.age = age;
        this.group = group;
        this.notes = notes;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
