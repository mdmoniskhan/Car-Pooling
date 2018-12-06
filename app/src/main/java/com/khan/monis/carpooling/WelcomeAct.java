package com.khan.monis.carpooling;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void register(View view){

        Intent intent = new Intent(getApplicationContext(), Register.class);

        startActivity(intent);

    }

    public void login(View view){

        TextView email = findViewById(R.id.email);
        TextView password = findViewById(R.id.password);
        Switch rider = findViewById(R.id.switch1);

        try{
            SQLiteDatabase db = this.openOrCreateDatabase("CarPooling", MODE_PRIVATE, null);

            if(!rider.isChecked()){

                Cursor c = db.rawQuery("SELECT * FROM user WHERE email = '"+String.valueOf(email.getText())+"' AND password = '"+String.valueOf(password.getText())+"' AND rider = 'Driver'", null);

                c.moveToFirst();

                Toast.makeText(WelcomeAct.this, "WELCOME : "+c.getString(1), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), CreateRide.class);
                intent.putExtra("id", c.getInt(0));
                startActivity(intent);

            }else{

                Cursor c = db.rawQuery("SELECT * FROM user WHERE email = '"+String.valueOf(email.getText())+"' AND password = '"+String.valueOf(password.getText())+"' AND rider = 'Passenger'", null);

                c.moveToFirst();

                Toast.makeText(WelcomeAct.this, "WELCOME : "+c.getString(1), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), SearchRide.class);
                intent.putExtra("id", c.getInt(0));
                startActivity(intent);

            }

        }catch(SQLException ex){
            Toast.makeText(WelcomeAct.this, "WRONG ENTRIES", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
