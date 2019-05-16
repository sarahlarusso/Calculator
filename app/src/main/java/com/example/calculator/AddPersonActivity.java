package com.example.calculator;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

//This class has methods for the Add Person Page, which allows a user to
// input information about a Person (their name and relationship), then
//choose to cancel or add the person to their Collection of People
public class AddPersonActivity extends AppCompatActivity {

    private String imgData;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        controller = (Controller) getApplicationContext();

        //Takes in the Image Data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        imgData = bundle.getString("Image Data String");
    }

   //Makes a new Person Object, adds it to the ArrayList if it does not already exists,
   //and goes to the View People Page
    public void addPerson (View v) {
        EditText name = findViewById(R.id.apNametxt);
        EditText relation = findViewById(R.id.apRelationtxt);

        String nameStr = name.getText().toString();
        String relationStr = relation.getText().toString();

        if (duplicate(imgData, nameStr)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            controller.addPerson(new Person(nameStr,relationStr,imgData));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    //Checks if the person already exists in the list
    public boolean duplicate (String data, String name) {
        for (Person p: controller.getPersonArrayList()) {
            if (p.getImageData().equals(data)) {
                if (p.getName().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }

    //Cancel brings to home if no people, and back to view otherwise
    public void cancel (View v) {
        if (controller.getPersonArrayList().size() != 0){
            //brings to view people
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            //brings to home screen
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        }
    }
}
