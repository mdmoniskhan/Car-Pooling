package com.khan.monis.carpooling;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
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


            RecyclerView recyclerView = findViewById(R.id.recView);

            Cursor c = db.getData(startLoc, endLoc, date);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            // Initializing a new String Array
            ArrayList<String> arrayList = new ArrayList<String>();

            if(c.getCount() == 0){
                Toast.makeText(RIdes.this, "NO RIDES", Toast.LENGTH_SHORT).show();
            }else{

                Toast.makeText(RIdes.this, "RIDES", Toast.LENGTH_SHORT).show();

                List<RidesInfo> ridesInfoList = new ArrayList<>();

                while (c.moveToNext()){

                    RidesInfo ri = new RidesInfo();
                    ri.carName = "     Car Name : "+c.getString(4);
                    ri.carNum = "     Car Num : "+c.getString(5);
                    ri.totalSeats = "     Available Seats : "+c.getString(7);
                    ri.availSeats = "     Time of Departure : "+c.getString(9);

                    ridesInfoList.add(ri);
                }

                MyAdapter mAdapter = new MyAdapter(ridesInfoList);
                recyclerView.setAdapter(mAdapter);

                db.close();

            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}