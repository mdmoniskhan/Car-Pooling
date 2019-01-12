package com.khan.monis.carpooling;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SearchRide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ride);
    }

    public void searchRide(View view){

        TextView startLoc = findViewById(R.id.startLoc);
        TextView endLoc = findViewById(R.id.endLoc);
        TextView date = findViewById(R.id.date);
        TextView time = findViewById(R.id.time);
        TextView seats = findViewById(R.id.seat);

        //checking if entries are not empty
        if(check(String.valueOf(startLoc.getText()))){
            Toast.makeText(SearchRide.this, "startLoc cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(endLoc.getText()))){
            Toast.makeText(SearchRide.this, "endLoc cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(date.getText()))){
            Toast.makeText(SearchRide.this, "date cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(time.getText()))){
            Toast.makeText(SearchRide.this, "time cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }if(check(String.valueOf(seats.getText()))){
            Toast.makeText(SearchRide.this, "seats cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            //id of Passenger
            int passengerID = getIntent().getExtras().getInt("id");

            Intent intent = new Intent(getApplicationContext(), RIdes.class);

            intent.putExtra("startLoc", String.valueOf(startLoc.getText()));
            intent.putExtra("endLoc", String.valueOf(endLoc.getText()));
            intent.putExtra("date", String.valueOf(date.getText()));

            Toast.makeText(SearchRide.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

            //sending to welcome screen
            startActivity(intent);

        }catch(SQLException ex){

            Toast.makeText(SearchRide.this, "NO RIDES AVAILABLE", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public boolean check(String s){
        if(s.equals(""))
            return true;
        return false;
    }
}
