package com.khan.monis.carpooling;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CreateRide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ride);
    }

    public void createRide(View view){

        TextView pick = findViewById(R.id.pick);
        TextView drop = findViewById(R.id.drop);
        TextView carName = findViewById(R.id.carName);
        TextView carNum = findViewById(R.id.carNum);
        TextView totalSeat = findViewById(R.id.totalSeat);
        TextView availSeat = findViewById(R.id.availSeat);
        TextView cost = findViewById(R.id.cost);
        TextView date = findViewById(R.id.date);
        TextView time = findViewById(R.id.datetime);

        //checking if entries are not empty
        if(check(String.valueOf(pick.getText()))){
            Toast.makeText(CreateRide.this, "pick cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(drop.getText()))){
            Toast.makeText(CreateRide.this, "drop cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(carName.getText()))){
            Toast.makeText(CreateRide.this, "carName cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(carNum.getText()))){
            Toast.makeText(CreateRide.this, "carNum cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(totalSeat.getText()))){
            Toast.makeText(CreateRide.this, "totalSeat cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(availSeat.getText()))){
            Toast.makeText(CreateRide.this, "availSeat cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(date.getText()))){
            Toast.makeText(CreateRide.this, "date cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(time.getText()))){
            Toast.makeText(CreateRide.this, "time cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }if(check(String.valueOf(cost.getText()))){
            Toast.makeText(CreateRide.this, "Cost cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            //id of Driver
            int driverID = getIntent().getExtras().getInt("id");

            //connectiong to DB

            DBconnect db = new DBconnect(this);

            boolean st = db.insertRide(driverID, String.valueOf(pick.getText()),String.valueOf(drop.getText()) ,String.valueOf(carName.getText()),String.valueOf(carNum.getText()),Integer.parseInt(String.valueOf(totalSeat.getText())),Integer.parseInt(String.valueOf(availSeat.getText())),String.valueOf(date.getText()), String.valueOf(time.getText()), String.valueOf(cost.getText()));

            if(st){

                db.close();

                Toast.makeText(CreateRide.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                //sending to welcome screen
                startActivity(new Intent(getApplicationContext(), WelcomeAct.class));

            }else
                Toast.makeText(CreateRide.this, "Error", Toast.LENGTH_SHORT).show();

        }catch(SQLException ex){

            Toast.makeText(CreateRide.this, "WRONG ENTRIES", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();

        }

    }

    public boolean check(String s){
        if(s.equals(""))
            return true;
        return false;
    }
}
