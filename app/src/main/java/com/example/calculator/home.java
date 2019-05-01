package com.example.calculator;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//This class is the home page that opens when the user opens the app
//The page initializes/reads the ArrayList of People from the test file
//From this page the user can go to the camera, or view people if there are people
public class home extends AppCompatActivity {

    ArrayList<Person> personArrayList;
    String fileName = "memoryTextFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        personArrayList = new ArrayList<Person>();

//      ORIGINAL CODE FOR READING A FILE
        FileInputStream inputStream = null;
        try {
            inputStream = openFileInput(fileName);
            InputStreamReader streamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                String[] fields = line.split(",");
                personArrayList.add(new Person(fields[0],fields[1],fields[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(),
                "File not found",
                Toast.LENGTH_LONG);
        toast.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button btnCamera = findViewById(R.id.camerabutton);
        btnCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imageData = data.getDataString();
        Log.d("IMGDATA",imageData);

        Intent intent = new Intent(this, AddPersonActivity.class);
        intent.putExtra("Image Data String", imageData);
        intent.putExtra("Person Array List", personArrayList);
        startActivity(intent);
    }

    public void viewPeople(View v) {
        if (personArrayList.size() != 0){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Person Array List", personArrayList);
            startActivity(intent);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You Have No People Yet",
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
