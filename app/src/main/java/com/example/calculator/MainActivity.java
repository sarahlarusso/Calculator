package com.example.calculator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

//This activity creates the View People screen
//Displays the image of the person, their name, and their relationship to the user
/*
Credits and Help:
Mrs. Taricco - Process, software, planning and execution, text file, basic use of Android Studio
https://www.youtube.com/watch?v=ondCeqlAwEI - Camera use
https://www.youtube.com/watch?v=r_87U6oHLFc - Dialog box use
https://www.youtube.com/watch?v=EcfUkjlL9RI&t=478s - Text file use
https://www.youtube.com/watch?v=MDuGwI6P-X8&t=675s - Timer
StackOverflow - text file, gallery access, timer
 */

public class MainActivity extends AppCompatActivity implements CameraDialog.CameraDialogListener {

    private final String TAG = "MainActivity";
    Controller controller;

    private String imgData;
    private String name;
    private String relationship;

    ImageView imageView;
    TextView nameDisplay;
    TextView relationDisplay;
    Uri imageUri;
    int index = 0;

    ImageButton rightButton;
    ImageButton leftButton;

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = (Controller)getApplicationContext();

        //Creates UI Displays
        imageView = findViewById(R.id.personImg);
        nameDisplay = findViewById(R.id.nameText);
        relationDisplay = findViewById(R.id.relationText);
        rightButton = findViewById(R.id.rightButton);
        leftButton = findViewById(R.id.leftButton);

        Intent intent = getIntent();

        //Toasts the number of contacts the user currently has
        index = controller.getPersonArrayList().size() - 1;
        Toast toast = Toast.makeText(getApplicationContext(),
                "Amount of People: " + controller.getPersonArrayList().size(),
                Toast.LENGTH_LONG);
        toast.show();

        //Displays the Information of the most recently added Person
        imgData = controller.getPersonArrayList().get(index).getImageData();
        name = controller.getPersonArrayList().get(index).getName();
        relationship = controller.getPersonArrayList().get(index).getRelationship();

        nameDisplay.setText(name);
        relationDisplay.setText(relationship);

        imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);

        //Initiates Image sequence for choosing a picture
        ImageButton addPerson = findViewById(R.id.AddPersonButton);
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCameraDialog();
            }
        });

        startTimer();
    }

    //Starts the 10 second timer
    public void startTimer(){
        timer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                View v = new View(getApplicationContext());
                rightButton(v);
            }
        }.start();
    }

    public void stopTimer(){
        timer.cancel();
    }

    public void openCameraDialog(){
        CameraDialog dialog = new CameraDialog();
        dialog.show(getSupportFragmentManager(),"Dialog");
    }

    @Override
    public void onCameraClicked(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onGalleryClicked(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }

    //Saves image data
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imgData = data.getDataString();

        Intent intent = new Intent(this, AddPersonActivity.class);
        intent.putExtra("Image Data String", imgData);
        startActivity(intent);
    }

    //Displays the Person at the current index - 1
    public void leftButton(View v) {
        stopTimer();

        index--;
        if (index == -1) {
            index = controller.getPersonArrayList().size() - 1;
        }

        imgData = controller.getPersonArrayList().get(index).getImageData();
        name = controller.getPersonArrayList().get(index).getName();
        relationship = controller.getPersonArrayList().get(index).getRelationship();

        nameDisplay.setText(name);
        relationDisplay.setText(relationship);

        imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);

        startTimer();
    }

    //Displays the Person at the current index + 1
    public void rightButton(View v) {
        stopTimer();

        index++;
        if (index == controller.getPersonArrayList().size()) {
            index = 0;
        }

        imgData = controller.getPersonArrayList().get(index).getImageData();
        name = controller.getPersonArrayList().get(index).getName();
        relationship = controller.getPersonArrayList().get(index).getRelationship();

        nameDisplay.setText(name);
        relationDisplay.setText(relationship);

        imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);

        startTimer();
    }

    //Delete currently viewed person
    public void deleteButton(View v) {
        if (controller.getPersonArrayList().size() == 1) {
            controller.deletePerson(index);
            stopTimer();

            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        }
        if (controller.getPersonArrayList().size() > 1) {
            controller.deletePerson(index);
            leftButton(v);
        }
    }

    //Converts Array List to a single string and writes it to a text file
    //If the text file does not exists, method creates the text file
    protected void onStop() {
        String saveText = "";
        for (Person p : controller.getPersonArrayList()) {
            saveText += p.getName() + "," + p.getRelationship() + "," + p.getImageData();
            saveText += "\n";
        }

        FileOutputStream fileOutput = null;
        String outputFilename = "memoryTextFile.txt";

        try {
            fileOutput = openFileOutput(outputFilename, MODE_PRIVATE);
            fileOutput.write(saveText.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "File not found",
                    Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Error",
                    Toast.LENGTH_LONG);
            toast.show();
        } finally {
            if (fileOutput != null) {
                try {
                    fileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onStop();
    }
}