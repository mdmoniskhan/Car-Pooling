package com.khan.monis.carpooling;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.*;


public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void submit(View view){

        //initializing fields
        TextView name = findViewById(R.id.name);
        TextView dob = findViewById(R.id.dob);
        TextView email = findViewById(R.id.email);
        TextView password = findViewById(R.id.passwod);
        TextView verify = findViewById(R.id.verify);
        TextView phone = findViewById(R.id.phone);

        //checking if entries are not empty
        if(check(String.valueOf(name.getText()))){
            Toast.makeText(Register.this, "Name cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(dob.getText()))){
            Toast.makeText(Register.this, "Date cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(email.getText()))){
            Toast.makeText(Register.this, "Email cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(password.getText()))){
            Toast.makeText(Register.this, "Password cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(verify.getText()))){
            Toast.makeText(Register.this, "Verify cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(check(String.valueOf(phone.getText()))){
            Toast.makeText(Register.this, "Contact cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        try{
            radioGroup = findViewById(R.id.radioGroup);

            int selectedItem = radioGroup.getCheckedRadioButtonId();

            RadioButton radioButton = findViewById(selectedItem);

            String rider = String.valueOf(radioButton.getText());

            //connectiong to DB

            DBconnect db = new DBconnect(this);

            boolean st = db.insertUser(String.valueOf(name.getText()),String.valueOf(dob.getText()) ,String.valueOf(email.getText()),String.valueOf(password.getText()),String.valueOf(verify.getText()),String.valueOf(phone.getText()),rider);

            if(st){

                db.close();

                Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                //sending to welcome screen
                Intent intent = new Intent(getApplicationContext(), WelcomeAct.class);

                startActivity(intent);
            }else
                Toast.makeText(Register.this, "Error", Toast.LENGTH_SHORT).show();

        }catch(NullPointerException ex){
            Toast.makeText(Register.this, "BUTTON NOT SELECTED", Toast.LENGTH_SHORT).show();
        }catch(SQLException ex){
            Toast.makeText(Register.this, "DBex", Toast.LENGTH_SHORT).show();
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
