package com.example.jaremylongley.planttracker;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jaremylongley on 10/22/17.
 */

public class PlantListAdapter extends ArrayAdapter<Plant> {

    private static final String TAG = "PlantListAdapter";
    private Context mContext;
    int mResource;

    public PlantListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Plant> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get plant info
        String name = getItem(position).getName();
        Plant plant = getItem(position);
        // Create a plant object with the given information
        Plant newPlant = new Plant(plant.getImagePath(), plant.getName(), plant.getAge(), plant.getGroup(), plant.getNotes());

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        // Get the views and set their content
        TextView textName = (TextView) convertView.findViewById(R.id.plantName);
        textName.setText(name);
        return convertView;
    }
}
