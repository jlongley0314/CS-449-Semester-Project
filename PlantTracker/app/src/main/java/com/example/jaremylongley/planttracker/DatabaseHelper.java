package com.example.jaremylongley.planttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaremylongley on 10/29/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = "plantsManager2";

    // Plants Table Name
    private static final String TABLE_PLANTS = "plantsdata";

    //Plants Table Columns
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MAIN_IMAGE_PATH = "mainImagePath";
    private static final String KEY_AGE = "age";
    private static final String KEY_GROUP = "plantGroup";
//    private static final String KEY_NOTES = "plantNotes";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create sql in order to create table
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PLANTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
                + KEY_MAIN_IMAGE_PATH + " TEXT, " + KEY_AGE + " TEXT, " + KEY_GROUP
                + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANTS);

        // Create tables again
        onCreate(db);
    }

    // Adding new plant
    public void addPlant(int UID, String imagePath, String name, String age, String group) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, UID);
        values.put(KEY_NAME, name);
        values.put(KEY_AGE, age);
        values.put(KEY_GROUP, group);
        values.put(KEY_MAIN_IMAGE_PATH, imagePath);
//        values.put(KEY_NOTES, notes);

        System.out.println("Plant UID: " + UID);
        // Inserting Row
        db.insert(TABLE_PLANTS, null, values);
        db.close();
    }

    // Getting single plant
    public Plant getPlant(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query
        String[] projection = {
                KEY_ID,
                KEY_MAIN_IMAGE_PATH,
                KEY_NAME,
                KEY_AGE,
                KEY_GROUP,
//                KEY_NOTES
        };

        String selection = KEY_ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(
                TABLE_PLANTS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
        );
        // String imagePath, String name, String age, String group, String notes
        Plant plant = new Plant(
                cursor.getInt(0),
                cursor.getString(2),
                cursor.getString(1),
                cursor.getString(3),
                cursor.getString(4));
//                cursor.getString(5));

        return plant;
    }

    // Getting All Plants
    public List<Plant> getAllPlants() {
        List<Plant> plantsList = new ArrayList<Plant>();

        // Select everything
        String selection = "SELECT * FROM " + TABLE_PLANTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selection, null);

        // Loop through all the rows and add to the list
        if (cursor.moveToFirst()) {
            do {
                Plant plant = new Plant(
                        //int UID, String imagePath, String name, String age, String group
                        //KEY_ID KEY_NAME KEY_MAIN_IMAGE_PATH KEY_AGE KEY_GROUP
                        cursor.getInt(0),
                        cursor.getString(2),
                        cursor.getString(1),
                        cursor.getString(3),
                        cursor.getString(4));
//                        cursor.getString(5));
                plantsList.add(plant);
            } while (cursor.moveToNext());
        }

        return plantsList;
    }

    public int getPlantCount() {
        String query = "SELECT * FROM " + TABLE_PLANTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return  count;
    }

    // Updating single plant
    public int updatePlant(Plant plant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, plant.getName());
        values.put(KEY_AGE, plant.getAge());
        values.put(KEY_GROUP, plant.getGroup());
        values.put(KEY_MAIN_IMAGE_PATH, plant.getImagePath());
//        values.put(KEY_NOTES, plant.getNotes());

        String selection = KEY_ID + "=?";
        String[] selectionArgs = {String.valueOf(plant.getUID())};

        // Update the specific row
        return db.update(
                TABLE_PLANTS,
                values,
                selection,
                selectionArgs
        );
    }

    // Deleting single plant
    public void deletePlant(Plant plant) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = KEY_ID + "=?";
        String[] selectionArgs = {String.valueOf(plant.getUID())};
        db.delete(
                TABLE_PLANTS,
                selection,
                selectionArgs);
        db.close();
    }
}
