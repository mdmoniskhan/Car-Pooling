package com.khan.monis.carpooling;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RIdes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rides);

        showRides();
    }

    public void showRides(){

        try{

            //connectiong to DB
            DBconnect db = new DBconnect(this);

            String startLoc = getIntent().getExtras().getString("startLoc");
            String endLoc = getIntent().getExtras().getString("endLoc");
            String date = getIntent().getExtras().getString("date");


            Cursor c = db.getData(startLoc, endLoc, date);

            ListView listView = findViewById(R.id.list);

            // Initializing a new String Array
            ArrayList arrayList = new ArrayList();

            if(c.getCount() == 0){
                Toast.makeText(RIdes.this, "NO RIDES", Toast.LENGTH_SHORT).show();
            }else{

                while (c.moveToNext()){
                    arrayList.add("Car Name : "+c.getString(4));
                    arrayList.add("Car Num : "+c.getString(5));
                    arrayList.add("Available Seats : "+c.getString(7));
                    arrayList.add("Time of Departure : "+c.getString(9));
                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,arrayList);
                    listView.setAdapter(listAdapter);
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}